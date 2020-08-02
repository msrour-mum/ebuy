import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartComponent } from './cart/cart.component';
import { ProductListComponent } from './products/product-list/product-list.component';
import { ProductDetailsComponent } from './products/product-details/product-details.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { ProductSearchComponent } from './products/product-search/product-search.component';



@NgModule({
  declarations: [CartComponent, ProductListComponent, ProductDetailsComponent, CheckoutComponent, ProductSearchComponent],
  imports: [
    CommonModule
  ]
})
export class ShoppingModule { }
