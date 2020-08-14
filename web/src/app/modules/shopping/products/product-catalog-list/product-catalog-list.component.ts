import { Component, OnInit } from '@angular/core';
import {Observable, Subject} from 'rxjs';
import { BrowserModule } from '@angular/platform-browser';

import { ProductService } from 'src/app/services/product.service';
import { AppConfig } from 'src/app/config/app.config';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ShoppingService} from '../../../../services/shopping.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-product-list',
  templateUrl: './product-catalog-list.component.html',
  styles: [
  ]
})
export class ProductCatalogListComponent implements OnInit {

  public hostUrl: string;
  public lstProduct:Observable<any[]>;
  searchForm: FormGroup;
  public searchItem:any;
  constructor(private dataService: ProductService, private  fb: FormBuilder, private shoppingService: ShoppingService, private router: Router) {
    this.searchForm = this.fb.group(
      {
        productName: [''],
        vendorName: [''],
        categoryName: [''],
        priceFrom: [''],
        priceTo: ['']
      });
      this.lstProduct = this.dataService.getActive();

    }

  ngOnInit(): void {
    this.hostUrl = AppConfig.settings.apiServiceUrl;
  }


  OnSubmit(): void {
    this.searchItem = this.searchForm.value;

    console.log(this.searchItem);
    this.lstProduct = this.dataService.search(this.searchItem);
  }

  addToCart(product: any) {
    this.shoppingService.addProduct(product, 1);
    this.router.navigate(['/cart']);
  }
  clear():void  {
    this.searchForm.reset();
  }

}
