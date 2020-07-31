import { BrowserModule } from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {AppConfig} from './config/app.config';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthenticationService} from './services/authentication.service';
import {JwtInterceptor} from './common/interceptors/jwt.interceptor';
import {ReactiveFormsModule} from '@angular/forms';
import {PreloadAllModules, RouterModule} from '@angular/router';
import {LoginComponent} from './modules/authentication/login/login.component';
import {SignupComponent} from './modules/authentication/signup/signup.component';
import {CommonModule} from '@angular/common';
import {ProductListComponent} from './modules/shopping/products/product-list/product-list.component';

export function initializeApp(appConfig: AppConfig) {
  return () => appConfig.load();
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    ProductListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    CommonModule,
    HttpClientModule,
  ],
  providers: [AppConfig,
    { provide: APP_INITIALIZER,
      useFactory: initializeApp,
      deps: [AppConfig], multi: true
    },
    AuthenticationService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
