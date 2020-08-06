import {Component, Output, OnInit} from '@angular/core';

import {FormBuilder, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-approve-product',
  templateUrl: './approve-product.component.html',
  styleUrls: ['./approve-product.component.css']
})
export class ApproveProductComponent implements OnInit {

  constructor(public  sev: ProductService,
                            private  fb: FormBuilder,
                            private router: Router) { }

  ngOnInit() {
  }

}
