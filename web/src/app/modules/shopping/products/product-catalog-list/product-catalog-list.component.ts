import { Component, OnInit } from '@angular/core';
import {Observable, Subject} from 'rxjs';
import { BrowserModule } from '@angular/platform-browser';

import { ProductService } from 'src/app/services/product.service';
import { AppConfig } from 'src/app/config/app.config';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';


@Component({
  selector: 'app-product-list',
  templateUrl: './product-catalog-list.component.html',
  styles: [
  ]
})
export class ProductCatalogListComponent implements OnInit {

  private hostUrl: string;
  // public lstProduct =[];
  public lstProduct:Observable<any[]>;
  searchForm: FormGroup;
  public searchItem:any;
  constructor(private dataService: ProductService, private  fb: FormBuilder) {   
    //  this.dataService.get().subscribe( {
    //    next: (result)=> {        
    //     this.lstProduct = result.data;
    //     console.log(this.lstProduct);
    //    },
    //    error: (err)=> console.log(err.console.error())
    //   });

    
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
    

    // if (this.searchItem.bid_price < 0) {
    //   this.errorMsg = 'bid price could not be negative number';
    //   return;
    // }

    console.log(this.searchItem);
    this.lstProduct = this.dataService.search(this.searchItem);


   // let formData: any = new FormData();
    //formData.append("payload", JSON.stringify(this.searchItem));
    //formData.append("photo", this.frm.get('photo').value);

    //this.dataService.save(formData).subscribe(( err:any) => {
      // console.log('add auction : ',resp);
      // if (err) {
      //   this.errorMsg = err;
      // }
    

  }

  clear():void  {
    this.searchForm.reset();
  }

}
