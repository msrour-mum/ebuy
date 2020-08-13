import { NgModule } from '@angular/core';
import {Routes, RouterModule, PreloadAllModules} from '@angular/router';
import {LoginComponent} from './modules/authentication/login/login.component';
import {SignupComponent} from './modules/authentication/signup/signup.component';
import {ProductCatalogListComponent} from './modules/shopping/products/product-catalog-list/product-catalog-list.component';
import { ApproveProductComponent } from './modules/admin/manage-product/approve-product/approve-product.component';
import {CategoryListComponent} from './modules/admin/manage-category/category-list/category-list.component';

const routes: Routes =  [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: ProductCatalogListComponent},
  {path: 'login', component: LoginComponent  },
  {path: 'signup', component: SignupComponent},
  {path: 'approve-product', component: ApproveProductComponent},
  {path: 'categories', component: CategoryListComponent},
  {path: '**', redirectTo: '/home'}];

@NgModule({
  imports: [RouterModule.forRoot(routes, {preloadingStrategy: PreloadAllModules})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
