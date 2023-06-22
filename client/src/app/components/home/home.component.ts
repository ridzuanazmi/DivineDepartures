import { Component, OnInit } from '@angular/core';
import { CsrfService } from 'src/app/services/csrf.service';

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
      })
      .catch(err => {
        console.info("redirect error: ", err)
      });
  }


}
