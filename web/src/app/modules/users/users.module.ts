import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfileComponent } from './profile/profile.component';
import { MyOrdersListComponent } from './my-orders-list/my-orders-list.component';
import {ReactiveFormsModule} from '@angular/forms';



@NgModule({
  declarations: [ProfileComponent, MyOrdersListComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
  ]
})
export class UsersModule { }
