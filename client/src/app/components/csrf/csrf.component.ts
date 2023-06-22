import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CsrfService } from 'src/app/services/csrf.service';

@Component({
  selector: 'app-csrf',
  templateUrl: './csrf.component.html',
  styleUrls: ['./csrf.component.css']
})
export class CsrfComponent implements OnInit {

  message!: string;

  constructor(
    private csrfSrvc: CsrfService,
    private router: Router) { }

  ngOnInit(): void {
    this.csrfSrvc.csrf()
      .then(response => {
        console.info("redirect response: ", response)
        this.message = response;
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
