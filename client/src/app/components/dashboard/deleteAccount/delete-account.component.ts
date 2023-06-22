import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { CsrfService } from 'src/app/services/csrf.service';
import { DashboardService } from 'src/app/services/dashboard.service';

@Component({
  selector: 'app-delete-account',
  templateUrl: './delete-account.component.html',
  styleUrls: ['./delete-account.component.css']
})
export class DeleteAccountComponent implements OnInit {

  id!: number;

  constructor(
    private csrfSrvc: CsrfService,
    private dashboardSrvc: DashboardService,
    private router: Router,
    private snackBar: MatSnackBar,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    const accountId = this.activatedRoute.snapshot.paramMap.get('id');
    this.id = accountId ? parseInt(accountId) : 0;
    console.log("Delete account id: ", this.id);
  }

  yesDelete(id: number) {
    this.dashboardSrvc.deleteAccount(id)
      .then((response) => {
        console.log(response);
        // this.csrfSrvc.csrf()
        //   .then(response => {
        //     console.info("csrf: ", response)
        //   })
        //   .catch(err => {
        //     console.info("redirect error: ", err)
        //   });
        this.router.navigate(['/dashboard']);
        this.snackBar.open('Account successfully deleted', 'Close', {
          duration: 3000,
          verticalPosition: 'top', // Positioning it at the top
        });
      })
      .catch((err) => {
        console.log(err);
        this.snackBar.open('Account could not be deleted', 'Close', {
          duration: 3000,
          verticalPosition: 'top', // Positioning it at the top
        });
      })
  }

  noDelete() {
    this.router.navigate(['/dashboard']);
  }
}
