import { Component, OnInit } from '@angular/core';
import {AppConfig} from '../../../../config/app.config';
import {ShoppingService} from '../../../../services/shopping.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styles: [
  ]
})
export class ProductDetailsComponent implements OnInit {

  public hostUrl: string = AppConfig.settings.apiServiceUrl;
  constructor(public shoppingService: ShoppingService,
              private router: Router) { }

  public ngOnInit(): void {
  }

  public addToCart(product): void {
    this.shoppingService.addProduct(product, 1);
    this.router.navigate(['/cart']);
  }

}
