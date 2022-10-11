import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddStaffComponent } from './Manager/add-staff/add-staff.component';
import { ListStaffComponent } from './Manager/list-staff/list-staff.component';
import { EditStaffComponent } from './Manager/edit-staff/edit-staff.component';
import { AddProductComponent } from './Manager/add-product/add-product.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ListProductComponent } from './Manager/list-product/list-product.component';
import { EditProductComponent } from './Manager/edit-product/edit-product.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { CreateOrderComponent } from './Staff/create-order/create-order.component';
import { ListOrdersComponent } from './Staff/list-orders/list-orders.component';
import { EditOrderComponent } from './Staff/edit-order/edit-order.component';
import { ListMenuComponent } from './Manager/list-menu/list-menu.component';
import { NavbarComponent } from './navbar/navbar.component';
import { TypingAnimatorModule } from 'angular-typing-animator';

@NgModule({
  declarations: [
    AppComponent,
    AddStaffComponent,
    ListStaffComponent,
    EditStaffComponent,
    AddProductComponent,
    ListProductComponent,
    EditProductComponent,
    LoginComponent,
    HomeComponent,
    CreateOrderComponent,
    ListOrdersComponent,
    EditOrderComponent,
    ListMenuComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    TypingAnimatorModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
