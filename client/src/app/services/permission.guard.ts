import { Injectable, inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
class PermissionGuard {

  constructor(
    private router: Router,
    private authSrvc: AuthService) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {

    // Check if user is logged in
    if (!this.authSrvc.isLoggedIn()) {
      this.router.navigate(['/login']);
      return false;
    }

    // Check if user is an admin
    if (route.data['isAdmin'] && this.authSrvc.getRoleFromJwt() != 'ADMIN') {
      this.router.navigate(['error/no-access']);
      return false;
    }

    return true;
  }
}

export const AuthGuard: CanActivateFn = (
  next: ActivatedRouteSnapshot,
  state: RouterStateSnapshot): boolean => {
    return inject(PermissionGuard).canActivate(next, state);
}
