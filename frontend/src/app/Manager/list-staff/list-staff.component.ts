import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StaffServicesService } from 'src/app/Services/staff-services.service';

@Component({
  selector: 'app-list-staff',
  templateUrl: './list-staff.component.html',
  styleUrls: ['./list-staff.component.css']
})
export class ListStaffComponent implements OnInit {
  staffList:any;
  error:any;
  errormsg:any;
  constructor(private staff:StaffServicesService, private router:Router) {}

  ngOnInit(): void {
    this.staff.getStaffList().subscribe(res=>{
      console.log(res);
      this.staffList=res;
    },err=>{
      console.log("error")
      console.log(err);
      this.error = err;
      // console.log(err.message);
      // this.errormsg = err.message;
    })
  }

  addStaff(){
    this.router.navigate(['/addStaff']);
  }

  deleteStaff(id:number){
    console.log(id);
    this.staff.deleteStaff(id).subscribe((res)=>{
      console.log(res);
      // window.location.reload();
      this.router.navigate(['/listStaff']);
      this.ngOnInit();
    },(err)=>{
      console.log(err);
    })
    
    
  }
}
