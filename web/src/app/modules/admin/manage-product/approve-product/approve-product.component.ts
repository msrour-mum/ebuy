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
  public lstProduct:Observable<any[]>;
  //searchForm: FormGroup;
  //public searchItem:any;
  constructor(private dataService: ProductService, private  fb: FormBuilder) {
    }

  ngOnInit(): void {
    this.hostUrl = AppConfig.settings.apiServiceUrl;
    this.loadData();

  }
  loadData(): void {

    this.lstProduct = this.dataService.getPending();
  }

  OnSubmit(): void {

  }

  OnAprove(productId: number): void {

    this.dataService.approve(productId).subscribe(x=>this.loadData());
  }

  OnReject(productId: number): void {

    this.dataService.reject(productId).subscribe(x=>this.loadData());
   }


  clear():void  {

  }

}
