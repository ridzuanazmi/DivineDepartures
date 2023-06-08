import { Component } from '@angular/core';

@Component({
  selector: 'app-repair',
  templateUrl: './repair.component.html',
  styleUrls: ['./repair.component.css']
})
export class RepairComponent {

  images = [1, 2, 3, 4, 5, 6, 7].map((n) => `/assets/images/repair/repair${n}.JPG`)

}
