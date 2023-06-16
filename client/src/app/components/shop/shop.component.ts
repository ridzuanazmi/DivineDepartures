import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Shop } from 'src/app/models/models';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css'],
  providers: [DatePipe]
})
export class ShopComponent implements OnInit {

  shopForm!: FormGroup;
  showTooltip = false;

  constructor(
    private fb: FormBuilder,
    private datePipe: DatePipe,
    private authSrvc: AuthService) { }

  ngOnInit(): void {
    this.shopForm = this.createForm();
  }

  onSubmit() {
    if (this.shopForm.get(['sections', 1, 'plant'])?.value === 'other') {
      this.shopForm.get(['sections', 1, 'plant'])?.setValue(this.shopForm.get(['sections', 1, 'otherPlant'])?.value);
    }
    let formattedDate = this.datePipe.transform(this.shopForm.get(['sections', 0, 'dateOfDeath'])!.value, 'yyyy-MM-dd');

    const formValue = this.shopForm.value;
    const shop: Shop = {
      deceasedName: formValue.sections[0].deceasedName,
      block: formValue.sections[0].block,
      plotNumber: formValue.sections[0].plotNumber,
      dateOfDeath: formattedDate || "",
      tombstoneHeight: formValue.sections[1].tombstoneHeight,
      tombstoneMaterial: formValue.sections[1].tombstoneMaterial,
      tiles: formValue.sections[1].tiles,
      curvedMosaicTile: formValue.sections[1].curvedMosaicTile,
      topCover: formValue.sections[1].topCover,
      plant: formValue.sections[1].plant,
      email: this.authSrvc.getEmailFromJwt()
    };
    console.log('shop info:', shop);
    this.shopForm.reset();
  }

  private createForm(): FormGroup {
    return this.fb.group({
      sections: this.fb.array([
        this.fb.group({
          deceasedName: ['', Validators.required],
          block: ['', Validators.required],
          plotNumber: ['', [Validators.required, Validators.pattern("^[0-9]*$")]],
          dateOfDeath: ['', Validators.required]
        }),
        this.fb.group({
          tombstoneHeight: ['10cm', Validators.required],
          tombstoneMaterial: ['', Validators.required],
          tiles: ['', Validators.required],
          curvedMosaicTile: ['', Validators.required],
          topCover: ['', Validators.required],
          plant: ['', Validators.required],
          otherPlant: ['']
        })
      ])
    });
  }

  get sections(): FormArray {
    return this.shopForm.get('sections') as FormArray;
  }
}
