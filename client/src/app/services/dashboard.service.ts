import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Account } from '../models/models';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(
    private http: HttpClient) { }

  // HTTP GET request to server to get an account in DB
  getAccountById(id: number): Promise<Account> {
    return firstValueFrom(
      this.http.get<Account>(`/dashboard/accounts/${id}`,
      { withCredentials: true })
    );
  }

  // HTTP Post request to server to get all accounts in DB
  public getAllAccounts(): Promise<Account[]> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', 'application/json');

    return firstValueFrom<Account[]>(
      this.http.get<Account[]>('/dashboard/accounts',
        { headers: headers, withCredentials: true })
    );
  }

  // HTTP DELETE request to server to delete an account in DB
  public deleteAccount(id: number): Promise<any> {

    return firstValueFrom(
      this.http.delete(`/dashboard/accounts/${id}`,
      { withCredentials: true, responseType: 'text'})
    );
  }

  // HTTP PUT request to server to update an account in DB
  updateAccount(id: number, user: Account): Promise<any> {
    return firstValueFrom(
      this.http.put(`/dashboard/accounts/${id}`, user,
      { withCredentials: true, responseType: 'text'})
    );
  }
}
