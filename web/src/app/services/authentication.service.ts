import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {map} from 'rxjs/operators';
import {BehaviorSubject, Observable} from 'rxjs';
import {JwtHelperService} from '@auth0/angular-jwt';
import {AppConfig} from '../config/app.config';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService{

  private authentication: Observable<any>;

  private authenticationSubject: BehaviorSubject<any>;
  private jwtHelperService: JwtHelperService;
  private AUTH_STORAGE_NAME = '_AUTH';

  constructor(private http: HttpClient,
              private router: Router) {

    this.authenticationSubject = new BehaviorSubject<any>(JSON.parse(localStorage.getItem(this.AUTH_STORAGE_NAME)));
    this.authentication = this.authenticationSubject.asObservable();
    this.jwtHelperService = new JwtHelperService();
  }

  public get currentUser(): any {
    return this.authenticationSubject.value ? this.authenticationSubject.value.user : null;
  }

  public get currentVendorId(): number {
    let usrid= this.currentUser.id ;   
    if(this.currentUser.vendor)
      usrid= this.currentUser.vendor.id ;   
      console.log("usrid : "+usrid)
    return usrid;

  }


  public get isAdmin(): boolean {
    //if(!this.jwtHelperService.isTokenExpired(this.authToken)) return false;
    if(! this.isAuthenticated) return false;
    if(this.authenticationSubject.value.user.role.id==1 || this.authenticationSubject.value.user.role.id==2) return true;
  
    return false;
     
   }

  public hasRole(role :string): boolean {
   if(!this.jwtHelperService.isTokenExpired(this.authToken)) return false;
   if(role=='admin' && this.authenticationSubject.value.user.role.id==1) return true;
   if(role=='vendor' && this.authenticationSubject.value.user.role.id==2) return true;
   return false;
    
  }


  public get authToken(): any {
    return this.authenticationSubject.value ? this.authenticationSubject.value.token : '';
  }

  public get isAuthenticated(): boolean {
    return !this.jwtHelperService.isTokenExpired(this.authToken);
  }

  // tslint:disable-next-line:typedef
  register(body: any) {
    return this.http.post(`${AppConfig.settings.apiServiceUrl}/users/signup`,
      body,{ observe: 'body' });
  }

  login(body: any) {
    return this.http.post<any>(`${AppConfig.settings.apiServiceUrl}/auth/login`, body)
      .pipe(map(result => {
        if(result.data && !result.error) {
          localStorage.setItem(this.AUTH_STORAGE_NAME, JSON.stringify(result.data));
        }
        this.authenticationSubject.next(result.data);
        return result;
      }));
  }

  logout() {
    localStorage.removeItem(this.AUTH_STORAGE_NAME);
    this.authenticationSubject.next(null);
    this.router.navigate(['login']);
  }
}
