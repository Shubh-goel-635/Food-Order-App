import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FoodProductService {

  constructor(private http:HttpClient) { }
  getData(){
    return this.http.get("http://localhost:8080/foodProduct");
  }
  editData(id:any,product:any){
    return this.http.put(`http://localhost:8080/foodProduct/${id}`,product)
  }
  deleteData(id:any){
    return  this.http.delete(`http://localhost:8080/foodProduct/${id}`)
  }
  addData(product:any){
    return this.http.post("http://localhost:8080/foodProduct",product)
  }
}
