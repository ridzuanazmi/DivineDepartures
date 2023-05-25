import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { matchPassword } from 'src/app/validator/match-password.validator';
import { PasswordStrengthValidator } from 'src/app/validator/password.validator';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm!: FormGroup;
  submitted = false;

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private router: Router) { }

  ngOnInit(): void {
    this.registerForm = this.createForm();
  }

  onSubmit() {
    this.submitted = true;

    if (this.registerForm.invalid) {
      return;
    }

    console.log("Register - onSubmit(): ", this.registerForm.value);
    this.registerForm.reset();
    this.router.navigate(['/login']) // After registration, do login
  }

  private createForm(): FormGroup {
    return this.fb.group({
      firstName: ['', [
        Validators.required,
        Validators.minLength(2)]],
      lastName: ['', [
        Validators.required,
        Validators.minLength(2)]],
      email: ['', [
        Validators.required,
        Validators.email]],
      password: ['', [
        Validators.required,
        PasswordStrengthValidator()
        ]],
      confirmPassword: ['',
        Validators.required,],
      hidePassword: ['']
    },
    {
      validator: matchPassword
    })
  }

}
