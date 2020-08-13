import {HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Cart} from '../models/shopping/cart';
import {CartManager} from '../models/shopping/cart-manager';
import {AppConfig} from '../config/app.config';

@Injectable({
  providedIn: 'root',
})
export class ReportsService {

  constructor(private http: HttpClient) { }

  

}
