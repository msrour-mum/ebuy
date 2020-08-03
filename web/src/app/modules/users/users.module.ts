import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfileComponent } from './profile/profile.component';
import { MyOrdersListComponent } from './my-orders-list/my-orders-list.component';



@NgModule({
  declarations: [ProfileComponent, MyOrdersListComponent],
  imports: [
    CommonModule
  ]
})
export class UsersModule { }
