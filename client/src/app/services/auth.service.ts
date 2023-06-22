import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { User } from '../models/models';

/*
  1. Helps to authenticate JWT
  2. Decode JWT to extract user information from MySQL
  3. Check if user is logged in or not
*/

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private jwtHelper: JwtHelperService,
    private router: Router) { }

  addJwt(token: string) {
    window.sessionStorage.setItem('token', token); // store JWT in session storage
  }

  addEmail(email: string) {
    window.sessionStorage.setItem('email', email);
  }

  getJwt() {
    return sessionStorage.getItem('token');
  }

  isLoggedIn() {
    return !!this.getJwt() && !this.isJwtExpired();
  }

  logout() {
    window.sessionStorage.removeItem("XSRF-TOKEN");
    window.sessionStorage.removeItem("token");
    this.router.navigate(['/']);
  }

  isJwtExpired() {
    const token: string=this.getJwt()??"";
        if (!token) {
            return false;
        }
        const tokenSplit:string = token.split('.')[1];
        const decodedString:string = atob(tokenSplit);
        const jsonString = JSON.parse(decodedString);
        const expiry = (jsonString).exp;
        return (Math.floor((new Date).getTime() / 1000)) >= expiry;
  }

  getEmailFromJwt() {
    const decodedToken = this.jwtHelper.decodeToken(this.getJwt()??""); // get the decoded JWT
    console.info(">> authSrvc decoded token = ", decodedToken); // comment out for security
    // Get email from JWT
    const sub = decodedToken.sub;
    console.log(">>> sub from JWT = ", sub); // comment out for security
    return sub;
  }

  getNameFromJwt() {
    const decodedToken = this.jwtHelper.decodeToken(this.getJwt()??""); // get the decoded JWT
    console.info(">> authSrvc decoded token = ", decodedToken); // comment out for security
    // Get name from JWT
    const name = decodedToken.fullName;
    console.log(">>> fullName from JWT = ", name);
    return name;
  }

  getPhoneNumberFromJWT() {
    const decodedToken = this.jwtHelper.decodeToken(this.getJwt()??""); // get the decoded JWT
    console.info(">> authSrvc decoded token = ", decodedToken); // comment out for security
    // Get name from JWT
    const phoneNumber = decodedToken.phoneNumber;
    console.log(">>> phoneNumber from JWT = ", phoneNumber);
    return phoneNumber;
  }

  getRoleFromJwt() {
    const helper = new JwtHelperService();
    const decodedToken = helper.decodeToken(this.getJwt() ?? "");
    console.info('>>> AuthSvc: getRole() decodedToken: ', decodedToken)
    if (decodedToken) {
      const roleStr = decodedToken['role']
      const role = roleStr.substring(1, roleStr.length - 1)
      console.info('>>> AuthSvc: getRole() role: ', role)
      return role;
    }
    return "";
  }

  getInfoFromJwt(): User {
    const decodedToken = this.jwtHelper.decodeToken(this.getJwt()??""); // get the decoded JWT

    let user: User = {
      fullName: decodedToken.fullName,
      phoneNumber: decodedToken.phoneNumber,
      email: decodedToken.sub
    };

    return user;
  }
}
