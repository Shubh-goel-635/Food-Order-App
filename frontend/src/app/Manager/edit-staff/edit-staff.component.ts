import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StaffServicesService } from 'src/app/Services/staff-services.service';
import { map } from'rxjs/operators';

@Component({
  selector: 'app-edit-staff',
  templateUrl: './edit-staff.component.html',
  styleUrls: ['./edit-staff.component.css']
})
export class EditStaffComponent implements OnInit {
  result:any;
  staffid = 0;
  constructor(private staff:StaffServicesService, private route:ActivatedRoute, private router:Router) { }

  ngOnInit(): void {
    let id = Number(this.route.snapshot.params['id']);
    this.staffid = id;
    console.log(this.staffid);
    
    this.staff.getStaffById(id).subscribe(staffData=>{
      this.result = staffData;
      console.log(this.result);
      
    })
  }
  editStaff(staffDetails:any){
    console.log(staffDetails.value);
    
    this.staff.editStaff(this.staffid, staffDetails.value).subscribe((data)=>{
      console.log(data);
      this.router.navigate(['listStaff'])
    },(err)=>{
      console.log(err);      
    });
  }
}
