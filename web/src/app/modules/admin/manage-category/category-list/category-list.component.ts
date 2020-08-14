import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {CategoryService} from "../../../../services/category.service";

@Component({
  selector: 'app-manage-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit {
  search: string;
  public categories = new Observable<any[]>();
  constructor(private categoryService:CategoryService) { }

  ngOnInit() {
    this.categories = this.categoryService.get();
  }

  deleteCategory(categoryId: number) {
    if(confirm("Are you sure you want to delete this item ?"))
    {
      this.categoryService.delete(categoryId).subscribe(x=>
        this.categories = this.categoryService.get());
    }
  }
}
