import {HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {AppConfig} from '../config/app.config';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

   public update(body: any,productId) {
      return this.http.put<any>(`${AppConfig.settings.apiServiceUrl}/products/${productId}`, body);
    }

    public create(body: any) {
      return this.http.post<any>(`${AppConfig.settings.apiServiceUrl}/products/`, body);
    }
    public getOne(productId) {
      return this.http.get<any>(`${AppConfig.settings.apiServiceUrl}/products/${productId}`);
    }

     public approve(productId:number) {
          return this.http.put<any>(`${AppConfig.settings.apiServiceUrl}/products/${productId}/approve/2`,null);
     }
     public reject(productId:number) {
      return this.http.put<any>(`${AppConfig.settings.apiServiceUrl}/products/${productId}/approve/3`,null);
     }

     public get() {
          return this.http.get<any>(`${AppConfig.settings.apiServiceUrl}/products/`);
     }
     public getPending() {
        return this.http.get<any>(`${AppConfig.settings.apiServiceUrl}/products/pending`);
     }

  public getRejected() {
    return this.http.get<any>(`${AppConfig.settings.apiServiceUrl}/products/`);
  }

     public getActive() {
                    return this.http.get<any>(`${AppConfig.settings.apiServiceUrl}/products/active`);
     }

     public delete(productId) {
           return this.http.delete<any>(`${AppConfig.settings.apiServiceUrl}/products/${productId}`);
     }
     public search(body: any) {
      return this.http.post<any>(`${AppConfig.settings.apiServiceUrl}/products/search/`, body);
    }

}
