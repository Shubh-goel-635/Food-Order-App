import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/LoginServices/login.service';
import { FoodProductService } from 'src/app/ManagerServices/food-product.service';
import { MenuService } from 'src/app/ManagerServices/menu.service';

@Component({
  selector: 'app-list-menu',
  templateUrl: './list-menu.component.html',
  styleUrls: ['./list-menu.component.css']
})
export class ListMenuComponent implements OnInit {
  _managerid:any;
  menu:any;
  food:any;
  foodProducts: any[] = [];
  constructor(private menuService:MenuService, private foodService:FoodProductService, private loginService:LoginService, private router:Router) { }

  ngOnInit(): void {
    this._managerid = Number(this.loginService.getId());
    this.menuService.getMenuByManagerId(this._managerid).subscribe((data)=>{
      this.menu = data;
      console.log(this.menu.data);
      this.foodService.getData().subscribe((data)=>{
        this.food = data;
        console.log(this.food);
        console.log(this.menu.data.length);
        if (this.menu.data.length != 0){
          console.log("if");
          for(let fooditem of this.food.data){
            // console.log(fooditem);
            
            const index = this.menu.data.findIndex((obj: any)=>{
              return obj.id === fooditem.id;
            })
            if (index === -1){
              this.foodProducts.push(fooditem);
            }
          }
        }
        else{
          this.foodProducts = this.food.data;
        }
      },(err)=>{
        console.log(err);
      });
    }); 
  }
  
  remove(productid:any){
    console.log(productid);
    this.menuService.removeProductFromMenu(this._managerid, productid).subscribe((data: any)=>{
      console.log(data);
      // window.location.reload();
      this.router.navigate(['listmenu']);
      this.foodProducts = [];
      this.ngOnInit();
    });
  }
  add(productid:any){
    console.log(productid);
    console.log(this._managerid)
    this.menuService.addProductToMenu(this._managerid, productid).subscribe((data:any)=>{
      console.log(data);
      // window.location.reload();
      this.router.navigate(['listmenu']);
      this.foodProducts = [];
      this.ngOnInit();
    });
  }
}
