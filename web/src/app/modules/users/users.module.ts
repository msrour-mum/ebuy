import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfileComponent } from './profile/profile.component';
import { OrdersListComponent } from './orders-list/orders-list.component';



@NgModule({
  declarations: [ProfileComponent, OrdersListComponent],
  imports: [
    CommonModule
  ]
})
export class UsersModule { }
