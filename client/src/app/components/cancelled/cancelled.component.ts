import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CsrfService } from 'src/app/services/csrf.service';

@Component({
  selector: 'app-cancelled',
  templateUrl: './cancelled.component.html',
  styleUrls: ['./cancelled.component.css']
})
export class CancelledComponent implements OnInit {

  constructor(
    private csrfSrvc: CsrfService,
    private router: Router) { }

  ngOnInit(): void {
    // Get new CSRF token
    this.csrfSrvc.csrf()
    .then(response => {
      console.info("csrf: ", response)
      setTimeout(() => {
        this.router.navigate(['/home']);
      }, 5000); // Redirect after 3 seconds
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
