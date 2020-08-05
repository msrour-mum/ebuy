import { NgModule } from '@angular/core';
import {Routes, RouterModule, PreloadAllModules} from '@angular/router';
import {LoginComponent} from './modules/authentication/login/login.component';
import {SignupComponent} from './modules/authentication/signup/signup.component';
import {ProductCatalogListComponent} from './modules/shopping/products/product-catalog-list/product-catalog-list.component';
import { ApproveProductComponent } from './modules/admin/manage-product/approve-product/approve-product.component';
import {CategoryListComponent} from './modules/admin/manage-category/category-list/category-list.component';
import { AddProductComponent} from "./modules/admin/manage-product/add-product/add-product.component";
import { AddCategoryComponent} from "./modules/admin/manage-category/add-category/add-category.component";
import {EditUserComponent} from './modules/admin/manage-users/edit-user/edit-user.component';
import {ProfileComponent} from './modules/users/profile/profile.component';
import {CartComponent} from './modules/shopping/cart/cart.component';
import {CheckoutComponent} from './modules/shopping/checkout/checkout.component';
import {ProductListComponent} from './modules/admin/manage-product/product-list/product-list.component';
import {EditProductComponent} from './modules/admin/manage-product/edit-product/edit-product.component';

const routes: Routes =  [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: ProductCatalogListComponent},
  {path: 'login', component: LoginComponent  },
  {path: 'signup', component: SignupComponent},
  {path: 'product-list', component: ProductListComponent},
  {path: 'approve-product', component: ApproveProductComponent},
  {path: 'add-product', component: AddProductComponent},
  {path: 'edit-product/:productId', component: EditProductComponent},
  {path: 'categories', component: CategoryListComponent},
  {path: 'add-category', component: AddCategoryComponent},
  {path: 'edit-user/:id', component: EditUserComponent},
  {path: 'edit-profile', component: ProfileComponent},
  {path: 'cart', component: CartComponent},
  {path: 'checkout', component: CheckoutComponent},
  {path: '**', redirectTo: '/home'}];

@NgModule({
  imports: [RouterModule.forRoot(routes, {preloadingStrategy: PreloadAllModules})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
