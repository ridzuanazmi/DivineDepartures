import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/models';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user!: User

  constructor(
    private authSrvc: AuthService) { }

  ngOnInit(): void {
    this.user = this.authSrvc.getInfoFromJwt();
  }
}
