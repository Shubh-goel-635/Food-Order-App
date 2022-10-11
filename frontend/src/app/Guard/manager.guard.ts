import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from '../LoginServices/login.service';

@Injectable({
  providedIn: 'root'
})
export class ManagerGuard implements CanActivate {
  constructor(private service:LoginService, private router:Router){

  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if(this.service.isLoggedIn()){
        if(localStorage.getItem("role")==="manager"){
          return true;
        }
        else{
          alert("You don't have access to this page");
          this.router.navigate(['/home'])
          return false;
        }
      }
      else{
        window.alert("please login first");
        this.router.navigate(['/'])
        return false;
      }
    return true;
  }
  
}
