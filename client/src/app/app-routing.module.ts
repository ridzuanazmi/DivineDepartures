import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { ContactComponent } from './components/contact/contact.component';
import { DemoComponent } from './components/demo/demo.component';
import { ServicesComponent } from './components/services/services.component';
import { ShopComponent } from './components/shop/shop.component';
import { CartComponent } from './components/cart/cart.component';
import { MaintenanceComponent } from './components/services/maintenance/maintenance.component';
import { RepairComponent } from './components/services/repair/repair.component';
import { ReplacementComponent } from './components/services/replacement/replacement.component';
import { AuthGuard } from './services/permission.guard';
import { UserComponent } from './components/user/user.component';
import { SuccessComponent } from './components/success/success.component';
import { CancelledComponent } from './components/cancelled/cancelled.component';
import { CsrfComponent } from './components/csrf/csrf.component';
import { MaintenancePackageComponent } from './components/services/package/maintenance-package.component';
import { DashboardComponent } from './components/dashboard/dashboard/dashboard.component';
import { DeleteAccountComponent } from './components/dashboard/deleteAccount/delete-account.component';
import { EditAccountComponent } from './components/dashboard/editAccount/edit-account.component';
import { ErrorPageComponent } from './components/errorPage/error-page.component';


const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: "csrf", component: CsrfComponent },
  { path: "home", component: HomeComponent },
  { path: "login", component: LoginComponent },
  { path: "register", component: RegisterComponent },
  { path: "dashboard", component: DashboardComponent, canActivate: [ AuthGuard ], data: { isAdmin: true } },
  { path: "dashboard/delete-account/:id", component: DeleteAccountComponent, canActivate: [ AuthGuard ], data: { isAdmin: true } },
  { path: "dashboard/edit-account/:id", component: EditAccountComponent, canActivate: [ AuthGuard ], data: { isAdmin: true } },
  { path: "demo", component: DemoComponent },
  { path: "services", component: ServicesComponent },
  { path: "maintenance", component: MaintenanceComponent },
  { path: "maintenance/package", component: MaintenancePackageComponent, canActivate: [ AuthGuard ] },
  { path: "repair", component: RepairComponent },
  { path: "replacement", component: ReplacementComponent },
  { path: "success", component: SuccessComponent },
  { path: "cancelled", component: CancelledComponent },
  { path: "user", component: UserComponent },
  { path: "cart", component: CartComponent },
  { path: "shop", component: ShopComponent, canActivate: [ AuthGuard ] },
  { path: "contact-us", component: ContactComponent },
  { path: "error/no-access", component: ErrorPageComponent },
  { path: "**", redirectTo: "/", pathMatch: "full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
