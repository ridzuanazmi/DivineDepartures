import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-demo',
  templateUrl: './demo.component.html',
  styleUrls: ['./demo.component.css']
})
export class DemoComponent implements OnInit{

  message!: string;
  private demoUrl = 'http://localhost:8080/demo';  // URL to web api

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getHello().subscribe(data => {
      this.message = data.message;
    });
  }

  getHello(): Observable<any> {
    return this.http.get<any>(this.demoUrl, { withCredentials: true });
  }

}
