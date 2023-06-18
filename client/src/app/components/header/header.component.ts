import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedIn!: boolean;

  constructor(
    private authSrvc: AuthService,
    private router: Router) { }

    ngOnInit(): void {
      this.isLoggedIn = this.authSrvc.isLoggedIn();
    }

    logout() {
      this.authSrvc.logout();
    }

    goToUserPage() {
      this.router.navigate(['/user']); // assuming '/user' is the route for your user component
    }

}
