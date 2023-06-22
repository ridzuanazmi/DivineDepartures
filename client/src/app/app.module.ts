import { NgModule, isDevMode } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { GoogleMapsModule } from '@angular/google-maps';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { JWT_OPTIONS, JwtHelperService } from '@auth0/angular-jwt';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CookieService } from 'ngx-cookie-service';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CancelledComponent } from './components/cancelled/cancelled.component';
import { CartComponent } from './components/cart/cart.component';
import { ContactComponent } from './components/contact/contact.component';
import { CsrfComponent } from './components/csrf/csrf.component';
import { DemoComponent } from './components/demo/demo.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { MaintenanceComponent } from './components/services/maintenance/maintenance.component';
import { RepairComponent } from './components/services/repair/repair.component';
import { ReplacementComponent } from './components/services/replacement/replacement.component';
import { ServicesComponent } from './components/services/services.component';
import { ShopComponent } from './components/shop/shop.component';
import { SuccessComponent } from './components/success/success.component';
import { UserComponent } from './components/user/user.component';
import { AppRequestInterceptor } from './interceptors/app-request.interceptor';
import { MaterialModule } from './material.module';
import { MaintenancePackageComponent } from './components/services/package/maintenance-package.component';
import { ServiceWorkerModule } from '@angular/service-worker';
import { DashboardComponent } from './components/dashboard/dashboard/dashboard.component';
import { DeleteAccountComponent } from './components/dashboard/deleteAccount/delete-account.component';
import { EditAccountComponent } from './components/dashboard/editAccount/edit-account.component';
import { ErrorPageComponent } from './components/errorPage/error-page.component';

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
    FooterComponent,
    UserComponent,
    SuccessComponent,
    CancelledComponent,
    CsrfComponent,
    MaintenancePackageComponent,
    DashboardComponent,
    DeleteAccountComponent,
    EditAccountComponent,
    ErrorPageComponent
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
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: !isDevMode(),
      // Register the ServiceWorker as soon as the application is stable
      // or after 30 seconds (whichever comes first).
      registrationStrategy: 'registerWhenStable:30000'
    }),
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
