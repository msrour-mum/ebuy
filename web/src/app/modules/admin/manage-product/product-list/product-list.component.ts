import { Component, OnInit } from '@angular/core';
import {Observable, Subject} from 'rxjs';
import { BrowserModule } from '@angular/platform-browser';

import { ProductService } from 'src/app/services/product.service';
import { AppConfig } from 'src/app/config/app.config';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-manage-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  
  public hostUrl: string;
  search: string;
  public lstProduct:Observable<any[]>;
  constructor(private dataService: ProductService,
    public authService: AuthenticationService,
    private router: Router,
     private  fb: FormBuilder) {    
    }

  ngOnInit(): void {
    this.hostUrl = AppConfig.settings.apiServiceUrl;
    this.loadData();
  }

  loadData(): void {

    if(this.authService.currentUser.role.id==1)
      this.lstProduct = this.dataService.getAdminList();
    else if(this.authService.currentUser.role.id==2)
      this.lstProduct = this.dataService.getVendorList(this.authService.currentUser.id);
    else
     this.router.navigate(['/']);

  
  }


  OnSubmit(): void {
   
    //this.lstProduct = this.dataService.search(this.searchItem);
  }

  published(productId:number ):void  {
   
    this.dataService.published(productId,true).subscribe(x=>this.loadData());    
  }
  unPublished(productId:number ):void  {
   
    this.dataService.published(productId,false).subscribe(x=>this.loadData());    
    
  }

  delete(productId:number ):void  {
   
    if(confirm("Are you sure you want to delete this item ?"))
    {
      this.dataService.delete(productId).subscribe(x=>this.loadData());    
    }
  }

}
