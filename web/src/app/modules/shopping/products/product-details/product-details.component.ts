import { Component, OnInit } from '@angular/core';
import {AppConfig} from '../../../../config/app.config';
import {ShoppingService} from '../../../../services/shopping.service';
import {Router, ActivatedRoute} from '@angular/router';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { ProductService } from 'src/app/services/product.service';
import {SubSink} from 'subsink';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styles: [
  ]
})
export class ProductDetailsComponent implements OnInit {

  public hostUrl: string = AppConfig.settings.apiServiceUrl;
  public product = {};
  public subs = new SubSink();

  private productSubject: Observable<any> = new BehaviorSubject({}) ;

  constructor(public shoppingService: ShoppingService,
              private router: Router,
              private activeRouter: ActivatedRoute,
              private productService: ProductService) { 

                this.activeRouter.params.subscribe((p) => {
                  this.productService.getOne(p.productId).subscribe(x=>this.product = x.data);  });    
    }

  public ngOnInit(): void {

   
    //   this.subs.add(this.productSubject
    //     .subscribe({
    //         next: (result) => {
    //           this.product = result.data;
    //           console.log(result)
    //         },
    //       error: (err) => { console.log(err); },
    //       },
    //     ));
     

  }

  public addToCart(product): void {
    this.shoppingService.addProduct(product, 1);
    this.router.navigate(['/cart']);
  }

}
