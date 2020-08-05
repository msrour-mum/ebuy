import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductListComponent } from './manage-product/product-list/product-list.component';
import { CategoryListComponent } from './manage-category/category-list/category-list.component';
import { AddCategoryComponent } from './manage-category/add-category/add-category.component';
import { AddProductComponent } from './manage-product/add-product/add-product.component';
import {ReactiveFormsModule} from "@angular/forms";
import { ApproveProductComponent } from './manage-product/approve-product/approve-product.component';
import { ViewSalesComponent } from './view-sales/view-sales.component';
import { EditProductComponent } from './manage-product/edit-product/edit-product.component';
import { EditCategoryComponent } from './manage-category/edit-category/edit-category.component';
import { AddUserComponent } from './manage-users/add-user/add-user.component';
import { EditUserComponent } from './manage-users/edit-user/edit-user.component';
import { UserListComponent } from './manage-users/user-list/user-list.component';
import { ProductHelpComponent } from './manage-product/product-help/product-help.component';
import {AppRoutingModule} from '../../app-routing.module';

@NgModule({
  declarations: [ProductListComponent,
    CategoryListComponent,
    ProductHelpComponent,
    AddCategoryComponent,
    AddProductComponent,
    ApproveProductComponent,
    ViewSalesComponent,
    EditProductComponent,
    EditCategoryComponent,
    AddUserComponent,
    EditUserComponent,
    UserListComponent,
    ProductHelpComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    AppRoutingModule,
  ],
})
export class AdminModule { }
