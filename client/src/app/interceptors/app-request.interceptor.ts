import {
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class AppRequestInterceptor implements HttpInterceptor {

  constructor() { }

  intercept(request: HttpRequest<any>, next: HttpHandler) {
    const token = sessionStorage.getItem('token');

    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      })
    }

    return next.handle(request);

    // let xsrf = sessionStorage.getItem('XSRF-TOKEN');
    // if (xsrf) {
    //   httpHeaders = httpHeaders.append('X-XSRF-TOKEN', xsrf);
    //   request = request.clone({
    //     headers: httpHeaders
    //   });
    // }
    // httpHeaders = httpHeaders.append('X-Requested-With', 'XMLHttpRequest');
  }
}
