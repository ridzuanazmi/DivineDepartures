import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { LoginRequest, LoginResponse } from '../models/models';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  login(loginReq: LoginRequest): Promise<LoginResponse> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', 'application/json');

    return firstValueFrom(
      this.http.post<LoginResponse>(
        'http://localhost:8080/auth/authenticate',
        loginReq,
        { headers: headers, withCredentials: true })
    );
  }
}
