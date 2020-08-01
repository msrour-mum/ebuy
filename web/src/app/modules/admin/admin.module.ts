import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ManageProductListComponent } from './manage-product-list/manage-product-list.component';
import { ManageCategoryListComponent } from './manage-category-list/manage-category-list.component';

@NgModule({
  declarations: [ManageProductListComponent, ManageCategoryListComponent],
  imports: [
    CommonModule
  ]
})
export class AdminModule { }
