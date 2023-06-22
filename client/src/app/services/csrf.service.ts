import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CsrfService {

  constructor(private http: HttpClient) { }

  csrf(): Promise<any> {
    return firstValueFrom(
      this.http.get('/csrf',
      { withCredentials: true })
    )
  }
}
