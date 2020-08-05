import { BrowserModule } from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {AppConfig} from './config/app.config';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthenticationService} from './services/authentication.service';
import {JwtInterceptor} from './common/interceptors/jwt.interceptor';
import {ReactiveFormsModule} from '@angular/forms';
import {LoginComponent} from './modules/authentication/login/login.component';
import {SignupComponent} from './modules/authentication/signup/signup.component';
import {CommonModule} from '@angular/common';
import { ApproveProductComponent } from './modules/admin/manage-product/approve-product/approve-product.component';
import {ProductListComponent} from './modules/admin/manage-product/product-list/product-list.component';
import {CategoryListComponent} from './modules/admin/manage-category/category-list/category-list.component';
import {AddProductComponent} from './modules/admin/manage-product/add-product/add-product.component';
import {AddCategoryComponent} from './modules/admin/manage-category/add-category/add-category.component';
import {ProductCatalogListComponent} from './modules/shopping/products/product-catalog-list/product-catalog-list.component';
import {AddUserComponent} from './modules/admin/manage-users/add-user/add-user.component';
import {EditUserComponent} from './modules/admin/manage-users/edit-user/edit-user.component';
import {UserListComponent} from './modules/admin/manage-users/user-list/user-list.component';
import {EditProductComponent} from './modules/admin/manage-product/edit-product/edit-product.component';
import {EditCategoryComponent} from './modules/admin/manage-category/edit-category/edit-category.component';
import {UsersModule} from './modules/users/users.module';


export function initializeApp(appConfig: AppConfig) {
  return () => appConfig.load();
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    ProductListComponent,
    ProductCatalogListComponent,
    CategoryListComponent,
    AddProductComponent,
    AddCategoryComponent,
    EditCategoryComponent,
    ApproveProductComponent,
    EditProductComponent,
    AddUserComponent,
    EditUserComponent,
    UserListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    CommonModule,
    HttpClientModule,
  ],
  providers: [AppConfig,
    { provide: APP_INITIALIZER,
      useFactory: initializeApp,
      deps: [AppConfig], multi: true
    },
    AuthenticationService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
