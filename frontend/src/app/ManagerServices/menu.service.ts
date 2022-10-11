import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  constructor(private http:HttpClient) { }

  getMenuByManagerId(_managerid:any){
    return this.http.get(`http://localhost:8080/menuProductByManagerId/${_managerid}`);
  }

  addProductToMenu(_managerid:any, _productid:any){
    return this.http.get(`http://localhost:8080/addProductToMenu/${_managerid}/${_productid}`);
  }
  removeProductFromMenu(_managerid:any, _productid:any){
    return this.http.get(`http://localhost:8080/removeProductFromMenu/${_managerid}/${_productid}`);
  }
}
