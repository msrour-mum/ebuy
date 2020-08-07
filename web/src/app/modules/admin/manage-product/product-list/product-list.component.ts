import { Component, OnInit } from '@angular/core';
import {Observable, Subject} from 'rxjs';
import { BrowserModule } from '@angular/platform-browser';

import { ProductService } from 'src/app/services/product.service';
import { AppConfig } from 'src/app/config/app.config';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';



@Component({
  selector: 'app-manage-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  
  public hostUrl: string;
  search: string;
  // public lstProduct =[];
  public lstProduct:Observable<any[]>;
  //searchForm: FormGroup;
  //public searchItem:any;
  constructor(private dataService: ProductService, private  fb: FormBuilder) {    
      //this.lstProduct = this.dataService.getActive();
    }

  ngOnInit(): void {
    this.hostUrl = AppConfig.settings.apiServiceUrl;
    this.lstProduct = this.dataService.getActive();
    
  
  }


  OnSubmit(): void {
   
    //this.lstProduct = this.dataService.search(this.searchItem);
  }

  clear():void  {
   
  }

}
