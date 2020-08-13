import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {ProductService} from '../../../../services/product.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-promotion-list',
  templateUrl: './promotion-list.component.html',
  styleUrls: ['./promotion-list.component.css']
})
export class PromotionListComponent implements OnInit {

  public search: string;
  public product: Observable<any>;

public users = new Observable<any[]>();
  constructor(private productService: ProductService,
              private router: Router,
              private activeRouter: ActivatedRoute) {
  }

  ngOnInit() {
    this.activeRouter.params.subscribe((p) => {
      this.product = this.productService.getOne(p.productId);
    });
  }

  public deletePromotion(promotionId:number) {

      this.product.subscribe((res) => {
        this.productService.deletePromotion(res.data.id, promotionId).subscribe((result) => {
          if (result.error) {
            console.log(result.error);
          } else if (result.status.code == 200) {
            this.router.navigate(['/promotions', res.data.id]);
          }
        });
      });

  }
}
