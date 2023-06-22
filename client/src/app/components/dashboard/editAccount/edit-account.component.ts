import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, ActivatedRoute } from '@angular/router';
import { Account } from 'src/app/models/models';
import { AuthService } from 'src/app/services/auth.service';
import { CsrfService } from 'src/app/services/csrf.service';
import { DashboardService } from 'src/app/services/dashboard.service';

@Component({
  selector: 'app-edit-account',
  templateUrl: './edit-account.component.html',
  styleUrls: ['./edit-account.component.css']
})
export class EditAccountComponent implements OnInit {

  id!: number;
  editForm: FormGroup = this.fb.group({});
  account!: Account;

  constructor(
    private csrfSrvc: CsrfService,
    private dashboardSrvc: DashboardService,
    private router: Router,
    private snackBar: MatSnackBar,
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder) {

    // Initialize the form group and controls
    this.editForm = this.fb.group({
      id: ['', Validators.required],
      fullName: ['', Validators.required],
      email: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      role: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    const accountId = this.activatedRoute.snapshot.paramMap.get('id');
    this.id = accountId ? parseInt(accountId) : 0;
    console.log("Delete account id: ", this.id);

    this.dashboardSrvc.getAccountById(this.id)
      .then((response) => {
        console.log(response);
        this.account = response
        console.log("account: ", this.account)

        // Set the values of the form controls
        this.editForm.setValue({
          id: this.account.userId,
          fullName: this.account.fullName,
          email: this.account.email,
          phoneNumber: this.account.phoneNumber,
          role: this.account.role
        });
      })
      .catch((err) => {
        console.error(err);
      });
  }

  onSubmit() {
    const updatedAccount: Account = this.editForm.value as Account;
    console.info("edit form submit: ", updatedAccount);
    console.info("id number: ", this.id);

    this.dashboardSrvc.updateAccount(this.id, updatedAccount)
      .then((response) => {
        console.info(response);
        this.csrfSrvc.csrf()
          .then(response => {
            console.info("csrf: ", response)
          })
          .catch(err => {
            console.info("redirect error: ", err)
          });
        this.router.navigate(['/dashboard']);
        this.snackBar.open('Account successfully updated', 'Close', {
          duration: 3000,
          verticalPosition: 'top', // Positioning it at the top
        });
      })
      .catch((error) => {
        console.info(error);
        this.snackBar.open('Account could not be updated', 'Close', {
          duration: 3000,
          verticalPosition: 'top', // Positioning it at the top
        });
      })
  }

  onCancel() {
    this.router.navigate(["/dashboard"]);
  }

}
