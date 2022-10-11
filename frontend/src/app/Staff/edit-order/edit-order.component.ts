import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { StaffServicesService } from 'src/app/Services/staff-services.service';

@Component({
  selector: 'app-edit-order',
  templateUrl: './edit-order.component.html',
  styleUrls: ['./edit-order.component.css']
})
export class EditOrderComponent implements OnInit {
  order:any;
  error:any;
  editorder:any;
  isSubmitted:boolean = false;
  isUpdated:boolean = true;
  isOrderUpdated:boolean = false;
  constructor(private staff:StaffServicesService, private route:ActivatedRoute, private router:Router) { }

  ngOnInit(): void {
    let id=Number(this.route.snapshot.params['id']);
    this.staff.getOrderById(id).subscribe((data)=>{
      this.order = data;
      console.log(this.order);
      
    }, (err)=>{
      this.error = err;
      console.log(this.error);      
    })
  }
  dropdownchange(){
    console.log(this.order.status);
    
  }
  editOrder(orderDetails:NgForm){
    this.isSubmitted = true;
    this.isUpdated = false;
    orderDetails.value['id'] = this.order.id;
    this.staff.editOrder(orderDetails.value).subscribe((data)=>{  
      this.isOrderUpdated=true;
      this.isSubmitted = false;   
      console.log(data);
      setTimeout(()=>{
        this.router.navigate(['/listorders']);
      },3000);
    }, (err)=>{
      console.log(err);
    })
  }

}
