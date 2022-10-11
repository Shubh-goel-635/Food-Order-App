import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private _updatemenu=new Subject<void>();
  constructor(private http:HttpClient) { }
  get updateMenu(){
    return this._updatemenu;
  }
  login(user:any){
    return this.http.post("http://localhost:8080/login",user)
  }
  getId(){
    return localStorage.getItem('id');
  }
  getrole(){
    return localStorage.getItem('role');
  }
  isLoggedIn(){
    if(localStorage.getItem("id")){
      return true;
    }
    else{
      return false;
    }
  }
}
