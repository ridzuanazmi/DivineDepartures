import { Component } from '@angular/core';

@Component({
  selector: 'app-maintenance',
  templateUrl: './maintenance.component.html',
  styleUrls: ['./maintenance.component.css']
})
export class MaintenanceComponent {

  images = [1, 2, 3].map((n) => `/assets/images/maintenance/maintenance${n}.JPG`)

}
