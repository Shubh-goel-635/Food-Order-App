import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { FoodProductService } from 'src/app/ManagerServices/food-product.service';


@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {
  result:any;
  selectedProduct:any;
  constructor(private route: ActivatedRoute, private products:FoodProductService, private router:Router) { }

  ngOnInit(): void {
    let id=this.route.snapshot.params['id']
    let _id=Number(id)
    console.log(typeof(_id));
    this.products.getData().subscribe((data)=>{
      this.result=data;
      console.log(this.result);
      for(let r of this.result.data){
        console.log(typeof(r.id));
        if(r.id===_id){
          this.selectedProduct=r;
          console.log(r);
          
        }
      }
    })
    
  }

  editProduct(form:NgForm){
    this.products.editData(this.selectedProduct.id,form.value).subscribe((res)=>{
      console.log(res);
      this.router.navigate(['/listproducts'])
      
    },(err)=>{
      console.log(err);
      
    })
    
  }

}
