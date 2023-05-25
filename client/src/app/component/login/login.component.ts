import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  submitted = false;

  constructor(private http: HttpClient, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.loginForm = this.createForm();
  }

  onSubmit() {
    this.submitted = true; // set submitted to true

    if (this.loginForm.invalid) {
      return;
    }

    console.log("Login - onSubmit(): ", this.loginForm.value);
    this.loginForm.reset();
  }

  private createForm(): FormGroup {
    return this.fb.group({
      email: ['', [
        Validators.required,
        Validators.email]],
      password: ['', [
        Validators.required]]
    });
  }

}
