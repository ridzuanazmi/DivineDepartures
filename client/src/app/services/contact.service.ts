import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Contact } from '../models/models';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  constructor(
    private http: HttpClient) { }

  contactUs(contact: Contact): Promise<Contact> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', 'application/json');

    return firstValueFrom(
      this.http.post<Contact>(
        'http://localhost:8080/contact-us',
        contact,
        { headers: headers})
    );
  }
}
