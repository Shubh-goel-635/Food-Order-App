import { HttpClient } from '@angular/common/http';
import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { StaffServicesService } from 'src/app/Services/staff-services.service';

@Component({
  selector: 'app-list-orders',
  templateUrl: './list-orders.component.html',
  styleUrls: ['./list-orders.component.css']
})
export class ListOrdersComponent implements OnInit{
  orderList:any;
  error:any;
  // dropdown:any;
  constructor(private http:HttpClient, private staff:StaffServicesService) { }

  ngOnInit(): void {
    this.staff.getOrders().subscribe((data)=>{
      this.orderList = data
      console.log(this.orderList);
    }, (err)=>{
      this.error = err;
      console.log(this.error);
    })
  }

  // dropdownchange(){
  //   console.log("change function", this.dropdown);
    
  // }

}
