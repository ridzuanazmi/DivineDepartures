import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { Shop } from '../models/models';

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  constructor(private http: HttpClient) { }

  sendShop(shop: Shop): Promise<any> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', 'application/json');

    return firstValueFrom(
      this.http.post<any>(
        'http://localhost:8080/email/shop', shop,
        { headers: headers, withCredentials: true })
    );
  }
}
