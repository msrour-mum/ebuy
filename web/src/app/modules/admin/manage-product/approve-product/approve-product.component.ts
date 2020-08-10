import { Component, OnInit } from '@angular/core';
import {Observable, Subject} from 'rxjs';
import { BrowserModule } from '@angular/platform-browser';

import { ProductService } from 'src/app/services/product.service';
import { AppConfig } from 'src/app/config/app.config';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-approve-product',
  templateUrl: './approve-product.component.html',
  styleUrls: ['./approve-product.component.css']
})
export class ApproveProductComponent implements OnInit {

  private hostUrl: string;
  search: string;
  // public lstProduct =[];
  public listProduct:Observable<any[]>;
  //searchForm: FormGroup;
  //public searchItem:any;
  constructor(private dataService: ProductService, private  fb: FormBuilder) {
      //this.lstProduct = this.dataService.getActive();
    }

  ngOnInit(): void {
    this.hostUrl = AppConfig.settings.apiServiceUrl;
    this.loadData();
    //this.listProduct.subscribe(x=>console.log(x));

  }
  loadData(): void {

    this.listProduct = this.dataService.getPending();
  }

  OnSubmit(): void {

    //this.lstProduct = this.dataService.search(this.searchItem);
  }

  OnAprove(productId: number): void {

    let xx=  this.dataService.approve(productId);
    xx.subscribe(x=>console.log(x))
    this.loadData();
  }

  OnReject(productId: number): void {

    this.dataService.reject(productId);
     this.loadData();
   }


  clear():void  {

  }

}
