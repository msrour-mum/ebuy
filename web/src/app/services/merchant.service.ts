import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppConfig} from '../config/app.config';

@Injectable({
  providedIn: 'root'
})
export class MerchantService {

  constructor(private http: HttpClient) {

  }

  public validate(body: any) {
    return this.http.post<any>(`${AppConfig.settings.apiServiceUrl}/payment/validateCard`, body);
  }

  public pay(body: any, amount: number) {
    return this.http.post<any>(`${AppConfig.settings.apiServiceUrl}/payment/validateCard/${amount}`, body);
  }

}
