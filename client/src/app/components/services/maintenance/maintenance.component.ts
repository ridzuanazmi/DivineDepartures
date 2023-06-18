import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { loadStripe } from '@stripe/stripe-js';
import { StripeId } from 'src/app/models/models';
import { MaintenanceService } from 'src/app/services/maintenance.service';
import { environment } from 'src/environments/environment.development';

@Component({
  selector: 'app-maintenance',
  templateUrl: './maintenance.component.html',
  styleUrls: ['./maintenance.component.css']
})
export class MaintenanceComponent {

  images = [1, 2, 3].map((n) => `/assets/images/maintenance/maintenance${n}.JPG`)

  // get the ids of prices
  monthlyPriceId = 'price_1NKCkIA6ZeborKVNqIWcveSE';
  yearlyPriceId = 'price_1NKDDAA6ZeborKVNeMWXa49m';

  // load the stripeJs
  stripePromise = loadStripe('pk_test_51NKBUaA6ZeborKVNiTZVrtuKi0apZX79n7OviqCpBhCYbkMj70Xg7Qfe2YqgsimykwKMtruTGGua60HX0iN1Hz26003RG0UIZr');

  constructor(private mainSrvc: MaintenanceService) { }

  async checkoutMonthly(): Promise<void> {
    this.checkout(this.monthlyPriceId);
  }

  async checkoutYearly(): Promise<void> {
    this.checkout(this.yearlyPriceId);
  }

  /**
   * this method do the checkout for a priceId and it is async because it awaiting the Promise object
   */
  private async checkout(priceId: string): Promise<void> {
    const checkout = {
      priceId: priceId,
      cancelUrl: 'http://localhost:4200/cancelled',
      successUrl: 'http://localhost:4200/success',
    };
    const stripe = await this.stripePromise;
    // this is a normal http calls for a backend api
    this.mainSrvc.subscribeMaintenance(checkout)
      .then((response: StripeId) => {
        console.info("Maintanence subscrption response: ", response);
        // Use stripe to redirect To Checkout page of Stripe platform
        stripe?.redirectToCheckout({
          sessionId: response.sessionId
        });
      })
      .catch(err => {
        console.info("Maintanence subscrption error: ", err);
      })
      .finally(() => {
        window.sessionStorage.setItem('payment', 'successful');
      })
  }
}
