import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CsrfComponent } from '../csrf/csrf.component';
import { CsrfService } from 'src/app/services/csrf.service';
import { getCookie } from 'typescript-cookie';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private csrfSrvc: CsrfService) { }

  ngOnInit(): void {
    this.csrfSrvc.csrf()
      .then(response => {
        console.info("csrf: ", response)
        // Get CSRF cookie from Spring
        // let xsrf = getCookie('XSRF-TOKEN')!;
        // console.info("XSRF-TOKEN = ", xsrf);
        // window.sessionStorage.setItem('XSRF-TOKEN', xsrf);
        // Delay for 2 seconds and then navigate to another route
        // setTimeout(() => {
        //   this.router.navigate(['/shop']);
        // }, 2000);
      })
      .catch(err => {
        console.info("redirect error: ", err)
      });
  }


}
