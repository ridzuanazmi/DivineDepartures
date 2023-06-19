import {
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpXsrfTokenExtractor
} from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class AppRequestInterceptor implements HttpInterceptor {

  constructor(private tokenExtractor: HttpXsrfTokenExtractor) { }

  intercept(request: HttpRequest<any>, next: HttpHandler) {
    let token = sessionStorage.getItem('token');
    const cookieHeaderName = 'X-XSRF-TOKEN';
    let csrfToken = this.tokenExtractor.getToken();
    // let xsrf = sessionStorage.getItem('XSRF-TOKEN');

    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
          // 'X-XSRF-TOKEN': csrfToken,
          'X-Requested-With': 'XMLHttpRequest'
        }
      })
    }
    if (csrfToken) {
      request = request.clone({
        setHeaders: {
          // Authorization: `Bearer ${token}`,
          'X-XSRF-TOKEN': csrfToken,
          'X-Requested-With': 'XMLHttpRequest'
        }
      })
    }

    return next.handle(request);

  }
}
