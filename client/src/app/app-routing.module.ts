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


const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: "home", component: HomeComponent },
  { path: "login", component: LoginComponent },
  { path: "demo", component: DemoComponent },
  { path: "services", component: ServicesComponent },
  { path: "maintenance", component: MaintenanceComponent },
  { path: "replacement", component: ReplacementComponent },
  { path: "user", component: UserComponent },
  { path: "repair", component: RepairComponent },
  { path: "cart", component: CartComponent },
  { path: "shop", component: ShopComponent, canActivate: [ AuthGuard ] },
  { path: "contact-us", component: ContactComponent },
  { path: "register", component: RegisterComponent },
  { path: "**", redirectTo: "/", pathMatch: "full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
