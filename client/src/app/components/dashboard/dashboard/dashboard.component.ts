import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Account } from 'src/app/models/models';
import { DashboardService } from 'src/app/services/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  accounts: Account[] = [];
  displayedColumns: string[] = ['userId', 'fullName', 'email', 'phoneNumber', 'createdDate', 'role', 'edit', 'delete'];
  dataSource!: MatTableDataSource<Account>;

  constructor(
    private dashboardSrvc: DashboardService,
    private router: Router) { }

  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;

  ngOnInit(): void {
    this.dashboardSrvc.getAllAccounts()
      .then((response) => {
        console.info("Dashboard accounts: ", response)
        this.accounts = response;
        this.dataSource = new MatTableDataSource(this.accounts);
        this.dataSource.paginator = this.paginator;
      })
      .catch((err) => {
        console.error("Dashboard accounts error: ", err);
      })
  }

  editAccount(id: number) {
    this.router.navigate(["/dashboard/edit-account", id]);
  }

  deleteAccount(id: number) {
    this.router.navigate(['/dashboard/delete-account', id]);
  }
}
