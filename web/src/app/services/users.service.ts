import {HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {AppConfig} from '../config/app.config';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UsersService {

  constructor(private http: HttpClient) { }

  public update(body: any) {
    return this.http.put<any>(`${AppConfig.settings.apiServiceUrl}/users/${1}`, body);
  }

  public get(userId) {
    return this.http.get<any>(`${AppConfig.settings.apiServiceUrl}/users/${userId}`);
  }

  public getAll() {
    return this.http.get<any>(`${AppConfig.settings.apiServiceUrl}/users`);
  }

}
