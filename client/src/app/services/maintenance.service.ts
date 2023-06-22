import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StripeId } from '../models/models';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MaintenanceService {

  constructor(
    private http: HttpClient) { }

  public subscribeMaintenance(checkout: any): Promise<StripeId> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', 'application/json');

    return firstValueFrom(
      this.http.post<StripeId>(
        '/payment/maintenance',
        checkout,
        { headers: headers, withCredentials: true })
    );
  }
}
