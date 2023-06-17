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
    const xsrf = sessionStorage.getItem('XSRF-TOKEN');

    if (token && xsrf) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
          'X-XSRF-TOKEN': xsrf
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
