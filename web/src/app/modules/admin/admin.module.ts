import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ManageProductListComponent } from './manage-product/manage-product-list.component';
import { ManageCategoryListComponent } from './manage-category/manage-category-list.component';
import { AddCategoryComponent } from './manage-category/add-category/add-category.component';
import { AddProductComponent } from './manage-product/add-product/add-product.component';
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [ManageProductListComponent, ManageCategoryListComponent, AddCategoryComponent, AddProductComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ]
})
export class AdminModule { }
