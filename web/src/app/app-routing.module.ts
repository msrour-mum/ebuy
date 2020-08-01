import { NgModule } from '@angular/core';
import {Routes, RouterModule, PreloadAllModules} from '@angular/router';
import {LoginComponent} from './modules/authentication/login/login.component';
import {SignupComponent} from './modules/authentication/signup/signup.component';
import {ProductListComponent} from './modules/shopping/products/product-list/product-list.component';
import { ApproveProductComponent } from './modules/admin/approve-product/approve-product.component';

const routes: Routes =  [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: ProductListComponent},
  {path: 'login', component: LoginComponent  },
  {path: 'signup', component: SignupComponent},
  {path: 'approve-product', component: ApproveProductComponent},
  {path: '**', redirectTo: '/home'}];

@NgModule({
  imports: [RouterModule.forRoot(routes, {preloadingStrategy: PreloadAllModules})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
