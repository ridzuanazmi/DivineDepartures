import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { JWT_OPTIONS, JwtHelperService } from '@auth0/angular-jwt';
import { CookieService } from 'ngx-cookie-service';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { RegisterComponent } from './components/register/register.component';
import { MaterialModule } from './material.module';
import { ContactComponent } from './components/contact/contact.component';
import { DemoComponent } from './components/demo/demo.component';
import { AppRequestInterceptor } from './interceptors/app-request.interceptor';
import { ServicesComponent } from './components/services/services.component';
import { ShopComponent } from './components/shop/shop.component';
import { CartComponent } from './components/cart/cart.component';
import { MaintenanceComponent } from './components/services/maintenance/maintenance.component';
import { RepairComponent } from './components/services/repair/repair.component';
import { ReplacementComponent } from './components/services/replacement/replacement.component';
import { GoogleMapsModule } from '@angular/google-maps';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    HeaderComponent,
    RegisterComponent,
    ContactComponent,
    DemoComponent,
    ServicesComponent,
    ShopComponent,
    CartComponent,
    MaintenanceComponent,
    RepairComponent,
    ReplacementComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    ReactiveFormsModule,
    GoogleMapsModule,
    NgbModule,
    HttpClientModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'XSRF-TOKEN', // this is the default
      headerName: 'X-XSRF-TOKEN' // this is the default
    }),
    NgbModule,
  ],
  providers: [
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    { provide: HTTP_INTERCEPTORS, useClass: AppRequestInterceptor, multi: true },
    JwtHelperService,
    CookieService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
