import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { RegisterRequest, RegisterResponse } from '../models/models';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }

  register(registerReq: RegisterRequest): Promise<RegisterResponse> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', 'application/json');

    return firstValueFrom(
      this.http.post<RegisterResponse>('http://localhost:8080/auth/register', registerReq, { headers })
    );
  }
}
