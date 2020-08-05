import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfileComponent } from './profile/profile.component';
import { MyOrdersListComponent } from './my-orders-list/my-orders-list.component';
import {ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule} from '../../app-routing.module';



@NgModule({
  declarations: [ProfileComponent, MyOrdersListComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    AppRoutingModule,
  ]
})
export class UsersModule { }
