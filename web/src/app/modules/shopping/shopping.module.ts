import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CartComponent} from './cart/cart.component';
import {ProductCatalogListComponent} from './products/product-catalog-list/product-catalog-list.component';
import {ProductDetailsComponent} from './products/product-details/product-details.component';
import {CheckoutComponent} from './checkout/checkout.component';

// import { ProductSearchComponent } from './products/product-search/product-search.component';


@NgModule({
  declarations: [CartComponent,
    ProductCatalogListComponent,
    ProductDetailsComponent,
    CheckoutComponent],
  imports: [
    CommonModule,
  ],
})
export class ShoppingModule {
}
