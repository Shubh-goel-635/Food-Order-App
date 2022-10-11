  import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FoodProductService } from 'src/app/ManagerServices/food-product.service';


@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css']
})
export class ListProductComponent implements OnInit {
  result:any;
  error:any;
  errorMsg:any;
  constructor(private products:FoodProductService, private router : Router) { }

  ngOnInit(): void {
  
    this.products.getData().subscribe((data)=>{
      this.result=data
      console.log(this.result);
      
    },(err)=>{
      console.log(err);
      this.error=err;
      this.errorMsg=err.message
      console.log(this.errorMsg);
      
      
    })
  }
  addProduct(){
    this.router.navigate(['/addproduct'])
  }
  deleteProduct(id:any){
    this.products.deleteData(id).subscribe((res)=>{
      console.log(res);
      this.router.navigate(['/listproducts'])
      this.products.getData().subscribe((res)=>{
        this.result=res;
      })
      
      
    },(err)=>{
      console.log(err);
      
    })
  }
}
