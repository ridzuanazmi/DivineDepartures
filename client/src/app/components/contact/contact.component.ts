import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { Contact, User } from 'src/app/models/models';
import { AuthService } from 'src/app/services/auth.service';
import { ContactService } from 'src/app/services/contact.service';
import { CsrfService } from 'src/app/services/csrf.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  user!: User;
  contactForm!: FormGroup;
  submitted = false;
  contact: Contact = {
    contactId: '',
    contactName: '',
    contactEmail: '',
    phoneNumber: '',
    subject: '',
    message: ''
  };
  center: google.maps.LatLngLiteral = { lat: 1.39410, lng: 103.74276 };
  markerPositions: google.maps.LatLngLiteral[] = [{ lat: 1.39410, lng: 103.74276 }];

  constructor(
    private fb: FormBuilder,
    private contactSrvc: ContactService,
    private csrfSrvc: CsrfService,
    private authSrvc: AuthService) { }

  ngOnInit(): void {
    if (this.authSrvc.isLoggedIn()) {
      this.user = this.authSrvc.getInfoFromJwt();
    }
    this.contactForm = this.createForm();
  }

  onSubmit(formDirective: FormGroupDirective) {
    console.log("Contact form - onSubmit(): ", this.contactForm.value);
    this.contactSrvc.contactUs(this.contactForm.value)
      .then((response) => {
        console.info("contact response: ", response);
        // save response(contactId) to display successfully sent message
        this.contact.contactId = response.contactId;
        // Get new CSRF token
        // this.csrfSrvc.csrf()
        //   .then(response => {
        //     console.info("csrf: ", response)
        //     // Get CSRF cookie from Spring
        //     // let xsrf = getCookie('XSRF-TOKEN')!;
        //     // console.info("XSRF-TOKEN = ", xsrf);
        //     // window.sessionStorage.setItem('XSRF-TOKEN', xsrf);
        //     // Delay for 2 seconds and then navigate to another route
        //     // setTimeout(() => {
        //     //   this.router.navigate(['/shop']);
        //     // }, 2000);
        //   })
        //   .catch(err => {
        //     console.info("csrf error: ", err)
        //   });
      })
      .catch(error => {
        console.error('contact form error: ', error);
      })
    // reset form
    formDirective.resetForm();
    this.contactForm.reset();
    this.contactForm = this.createForm();
  }

  private createForm(): FormGroup {
    return this.fb.group({
      contactName: [this.user ? this.user.fullName : '',
        [Validators.required,
        Validators.minLength(2),
        Validators.maxLength(128)]],
      contactEmail: [this.user ? this.user.email : '',
        [Validators.required,
        Validators.email]],
      subject: ['',
        [Validators.required,
        Validators.minLength(2),
        Validators.maxLength(50)]],
      message: ['',
        [Validators.required,
        Validators.maxLength(500)]],
      phoneNumber: [this.user ? this.user.phoneNumber : '',
        [Validators.required,
        Validators.minLength(8),
        Validators.maxLength(8),
        Validators.pattern(/^[89]\d{7,}$/)]]
    })
  }

}
