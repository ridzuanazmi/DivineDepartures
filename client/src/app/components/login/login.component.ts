import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  loginForm!: FormGroup;
  submitted = false;
  hide = true;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private loginSrvc: LoginService,
    private authSrvc: AuthService) { }

  ngOnInit(): void {
    this.loginForm = this.createForm();
  }

  onSubmit() {
    this.submitted = true; // set submitted to true
    if (this.loginForm.invalid) {
      return;
    }

    console.log("Login form - onSubmit(): ", this.loginForm.value); // Need to disable before production
    this.loginSrvc.login(this.loginForm.value)
      .then((response) => {
        console.info("response: ", response);
        // save token into session storage using authSvc
        this.authSrvc.addJwt(response.token);
        // get email from sub of JWT using JwtHelperService
        console.log('email from JWT = ', this.authSrvc.getEmailFromJwt());
        // Get CSRF cookie from Spring
        // let xsrf = getCookie('XSRF-TOKEN')!;
        // console.info("XSRF-TOKEN = ", xsrf);
        // window.sessionStorage.setItem('XSRF-TOKEN', xsrf);
        this.loginForm.reset();
        this.router.navigate(['/home']);
      })
      .catch(error => {
        console.error('>>> loginForm: error: ', error)
      })
  }

  private createForm(): FormGroup {
    return this.fb.group({
      email: ['', [
        Validators.required,
        Validators.email]],
      password: ['', [
        Validators.required]],
      hidePassword: ['']
    });
  }
}
