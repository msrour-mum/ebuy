import {HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {AppConfig} from '../config/app.config';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) { }

  public update(body: any) {
    return this.http.put<any>(`${AppConfig.settings.apiServiceUrl}/categories/${1}`, body);
  }

  public create(body: any) {
    return this.http.post<any>(`${AppConfig.settings.apiServiceUrl}/categories/`, body);
  }

  public getOne(categoryId) {

    return this.http.get<any>(`${AppConfig.settings.apiServiceUrl}/categories/${categoryId}`);
  }

  public get() {
    return this.http.get<any>(`${AppConfig.settings.apiServiceUrl}/categories/`);
  }

  public getActive() {
    return this.http.get<any>(`${AppConfig.settings.apiServiceUrl}/categories/active`);
  }

  public delete(categoryId) {
     return this.http.delete<any>(`${AppConfig.settings.apiServiceUrl}/categories/${categoryId}`);
  }
}
