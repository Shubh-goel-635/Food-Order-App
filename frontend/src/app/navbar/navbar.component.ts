import { Component, DoCheck, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../LoginServices/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, DoCheck {
  displayMenu=false;
  displayManager=false;
  displayStaff=false;
  currentRole:any;
  name:any;

  constructor(private router:Router,private loginService:LoginService){
  }
  ngDoCheck(): void {
    this.name = localStorage.getItem("name");
    if(this.router.url=="/login"){
      this.displayMenu=false;
    }
    else{
      this.displayMenu=true;
    }
  }

  ngOnInit(): void {
    this.loginService.updateMenu.subscribe((res)=>{
      this.menuDisplay();
    })
    this.menuDisplay();
  }
  menuDisplay(){
    if(this.loginService.getrole()!=null){
      this.currentRole=this.loginService.getrole();
      this.displayManager=this.currentRole == 'manager';
      this.displayStaff=this.currentRole=='staff'
      console.log(this.currentRole);
    }
  }
  logout(){
    localStorage.removeItem('id');
    localStorage.removeItem('role');
    localStorage.removeItem("name");
    window.alert("you are successfully logged out");
    this.router.navigate(['/login'])
  }

}
