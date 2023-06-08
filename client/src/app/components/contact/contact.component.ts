import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { Contact } from 'src/app/models/models';
import { ContactService } from 'src/app/services/contact.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  contactForm!: FormGroup;
  submitted = false;
  contact: Contact = {
    contactId: '',
    contactName: '',
    contactEmail: '',
    subject: '',
    message: ''
  };
  center: google.maps.LatLngLiteral = {lat: 1.39410, lng: 103.74276};
  markerPositions: google.maps.LatLngLiteral[] = [{ lat: 1.39410, lng: 103.74276 }];

  constructor(
    private fb: FormBuilder,
    private contactSrvc: ContactService) { }

  ngOnInit(): void {
    this.contactForm = this.createForm();
  }

  onSubmit(formDirective: FormGroupDirective) {
    console.log("Contact form - onSubmit(): ", this.contactForm.value);
    this.contactSrvc.contactUs(this.contactForm.value)
      .then((response) => {
        console.info("contact response: ", response);
        // save response(contactId) to display successfully sent message
        this.contact.contactId = response.contactId;
      })
      .catch(error => {
        console.error('contact form error: ', error);
      })
      // reset form
      formDirective.resetForm();
      this.contactForm.reset();
  }

  private createForm(): FormGroup {
    return this.fb.group({
      contactName: ['',
        [
          Validators.required,
          Validators.minLength(2)
        ]
      ],
      contactEmail: ['',
        [
          Validators.required,
          Validators.email
        ]
      ],
      subject: ['',
        [
          Validators.required,
          Validators.minLength(2),
        ]
      ],
      message: ['',
        [
          Validators.required
        ]
      ]
    })
  }

}
