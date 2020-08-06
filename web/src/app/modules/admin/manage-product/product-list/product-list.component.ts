import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-manage-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  public searchForm: FormGroup;

  constructor(private  fb: FormBuilder) {
  this.searchForm = this.fb.group(
      {
        search: [''],
      });
  }

  ngOnInit() {
  }

  onSubmit(): void {
    // let search = this.searchForm.get('search').value;
    // this.searchForm.reset();
    // this.router.navigate(['search'], {queryParams: {q: search}});
  }

}
