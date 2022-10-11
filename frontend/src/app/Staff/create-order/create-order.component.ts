import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/LoginServices/login.service';
import { StaffServicesService } from 'src/app/Services/staff-services.service';

@Component({
  selector: 'app-create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent implements OnInit{
  _staffid:any;
  _order:any;
  orderid:number =0; 
  menu:any;
  totalprice:number=0;
  error = false;
  errormsh:any;
  clicked:boolean = true;
  items: any[] = [];
  // qty: number[] = [];
  isSubmitted:boolean = false;
  isOrdered:boolean = true;
  isOrderCreated:boolean = false;
  time:number = 7;

  constructor(private staffService: StaffServicesService,private loginServices:LoginService, private router:Router) { }

  ngOnInit(): void {
    this._staffid=this.loginServices.getId();
    this.staffService.getMenuByStaffId(this._staffid).subscribe((data)=>{
      this.menu = data;
      this.items = this.menu.data
      // const qty: number[] = new Array(this.menu.data.length).fill(0);
      // this.qty = qty;
      this.items = this.items.map(prod=>{
        return {
          "productId": prod.id,
          "name": prod.name,
          "price": prod.price,
          "quantity":0,
          "type":prod.type
        };        
      });    
      console.log(this.items);
      console.log(this.menu);       
    }, (err)=>{
      this.error = err;
      this.error = err.message;
    })
    
  }
  
  decrease(id:number, index:number){
    if(this.items[index].quantity > 0){
      // decrease quantity and dec total price 
      const menuindex = this.items.findIndex((obj:any)=>{
        return obj.productId == id;
      });
      this.items[menuindex].quantity -= 1;
      this.totalprice -= this.items[menuindex].price;
      console.log(this.items[menuindex]);
    }
    //else do nothing
  }

  increase(id:number, index:number){
    // increase qty by 1 and increase price
    const menuindex = this.items.findIndex((obj:any)=>{
      return obj.productId == id;
    });
    this.items[menuindex].quantity += 1;
    this.totalprice += this.items[menuindex].price;
    console.log(this.items[menuindex]);
  }
  
  createOrder(orderDetails:NgForm){
    this.isSubmitted = true;
    this.isOrdered = false;
    orderDetails.value["totalPrice"] = this.totalprice;
    console.log(orderDetails.value);
    //remove item with quantity zero
    this.items = this.items.filter(function(item){
      return item.quantity !== 0;
    });
    console.log(this.items.length);
    if(this.items.length !== 0){
      this.staffService.createOrder(this._staffid, orderDetails.value).subscribe((data)=>{
        this._order = data;
        console.log(this._order.data); 
        this.orderid = this._order.data.id;
        this.staffService.addItems(Number(this._order.data.id), this.items).subscribe((data)=>{
          console.log(data);
          this.isOrderCreated = true;
          this.isSubmitted = false;
          setTimeout(()=>{
            this.router.navigate(['/listorders']);
          },7000);
          },(err)=>{
          console.log(err);
        });
      }, (err)=>{
        console.log(err);
        console.log(err.message);
      }); 
    }
  }
}
