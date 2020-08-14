import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CartComponent} from './cart/cart.component';
import {ProductCatalogListComponent} from './products/product-catalog-list/product-catalog-list.component';
import {ProductDetailsComponent} from './products/product-details/product-details.component';
import {CheckoutComponent} from './checkout/checkout.component';
import {AppRoutingModule} from '../../app-routing.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';

// import { ProductSearchComponent } from './products/product-search/product-search.component';


@NgModule({
  declarations: [CartComponent,
    ProductCatalogListComponent,
    ProductDetailsComponent,
    CheckoutComponent],
  imports: [
    CommonModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
})
export class ShoppingModule {
}
