import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApproveProductComponent } from './approve-product/approve-product.component';
import { ViewSalesComponent } from './view-sales/view-sales.component';



@NgModule({
  declarations: [ApproveProductComponent, ViewSalesComponent],
  imports: [
    CommonModule,
  ]
})
export class AdminModule { }
