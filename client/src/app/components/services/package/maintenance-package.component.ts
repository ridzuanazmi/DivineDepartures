import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { loadStripe } from '@stripe/stripe-js';
import { StripeId, User } from 'src/app/models/models';
import { AuthService } from 'src/app/services/auth.service';
import { MaintenanceService } from 'src/app/services/maintenance.service';

@Component({
  selector: 'app-maintenance-package',
  templateUrl: './maintenance-package.component.html',
  styleUrls: ['./maintenance-package.component.css']
})
export class MaintenancePackageComponent implements OnInit {

  user!: User
  // maintenance package form
  mpForm!: FormGroup;

  // get the ids of prices
  monthlyPriceId = 'price_1NKCkIA6ZeborKVNqIWcveSE';
  yearlyPriceId = 'price_1NKDDAA6ZeborKVNeMWXa49m';

  // load the stripeJs
  stripePromise = loadStripe('pk_test_51NKBUaA6ZeborKVNiTZVrtuKi0apZX79n7OviqCpBhCYbkMj70Xg7Qfe2YqgsimykwKMtruTGGua60HX0iN1Hz26003RG0UIZr');

  constructor(
    private mainSrvc: MaintenanceService,
    private fb: FormBuilder,
    private authSrvc: AuthService) { }

  ngOnInit(): void {
    this.user = this.authSrvc.getInfoFromJwt();
    this.mpForm = this.createForm();
  }

  private createForm(): FormGroup<any> {
    return this.fb.group({
      plotNumber: ['', [Validators.required]],
      blockNumber: ['', [Validators.required]]
    })
  }

  async checkoutMonthly(plotNumber: string, blockNumber: string): Promise<void> {
    this.checkout(this.monthlyPriceId, plotNumber, blockNumber);
  }

  async checkoutYearly(plotNumber: string, blockNumber: string): Promise<void> {
    this.checkout(this.yearlyPriceId, plotNumber, blockNumber);
  }

  /**
   * this method do the checkout for a priceId and it is async because it awaiting the Promise object
   */
  private async checkout(
    priceId: string,
    plotNumber: string,
    blockNumber: string): Promise<void> {

    const checkout = {
      priceId: priceId,
      successUrl: 'http://localhost:8080/#/success',
      cancelUrl: 'http://localhost:8080/#/cancelled',
      blockNumber: blockNumber,
      plotNumber: plotNumber,
      // Get info if user is logged in
      email: this.user.email,
      fullName: this.user.fullName
    };
    console.info("checkout data: ", checkout);
    const stripe = await this.stripePromise;
    // this is a normal http calls for a backend api
    this.mainSrvc.subscribeMaintenance(checkout)
      .then((response: StripeId) => {
        console.info("Maintenance subscription response: ", response);
        // Use stripe to redirect To Checkout page of Stripe platform
        stripe?.redirectToCheckout({
          sessionId: response.sessionId
        });
      })
      .catch(err => {
        console.info("Maintenance subscription error: ", err);
      })
      .finally(() => {
        window.sessionStorage.setItem('payment', 'successful');
      })
  }
}
