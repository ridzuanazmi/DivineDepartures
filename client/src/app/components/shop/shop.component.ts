import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Shop } from 'src/app/models/models';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  shopForm!: FormGroup;

  constructor(
    private fb: FormBuilder) { }

  ngOnInit(): void {
    this.shopForm = this.createForm();
  }

  onSubmit() {
    console.log('shop info:', this.shopForm.value);
    this.shopForm.reset();
  }

  private createForm(): FormGroup {
    return this.fb.group({
      deceasedName: ['', Validators.required],
      block: ['', Validators.required],
      plotNumber: ['', [Validators.required, Validators.pattern("^[0-9]*$")]],
      dateOfDeath: ['', Validators.required],
      tombstoneHeight: ['', Validators.required],
      tombstoneMaterial: ['', Validators.required],
      tiles: ['', Validators.required],
      curvedMosaicTile: ['', Validators.required],
      topCover: ['', Validators.required],
      plant: ['', Validators.required]
    })
  }
}
