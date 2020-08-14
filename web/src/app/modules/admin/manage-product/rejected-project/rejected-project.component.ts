import { Component, OnInit } from '@angular/core';
import {Observable, Subject} from 'rxjs';
import { ProductService } from 'src/app/services/product.service';
import { AppConfig } from 'src/app/config/app.config';

@Component({
  selector: 'app-rejected-project',
  templateUrl: './rejected-project.component.html',
  styleUrls: ['./rejected-project.component.css']
})
export class RejectedProjectComponent implements OnInit {
  private hostUrl: string;
  search: string;
  // public lstProduct =[];
  public listProduct:Observable<any[]>;

  constructor(private dataService: ProductService) { }

  ngOnInit() {
    this.listProduct = this.dataService.getRejected();
  }

}
