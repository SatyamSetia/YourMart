import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { SignupFormComponent } from './signup-form/signup-form.component';
import { HomeComponent } from './home/home.component';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductListItemComponent } from './product-list-item/product-list-item.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { ProductFormComponent } from './product-form/product-form.component';
import { HeaderComponent } from './header/header.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
  {
    path: '',
    component: LoginFormComponent
  }, {
    path: 'register',
    component: SignupFormComponent
  }, {
    path: 'home',
    canActivate: [AuthGuard],
    component: HomeComponent
  }, {
    path: 'product/:id',
    canActivate: [AuthGuard],
    component: ProductDetailComponent
  }, {
    path: 'create',
    canActivate: [AuthGuard],
    component: ProductFormComponent
  }, {
    path: 'edit/:id',
    canActivate: [AuthGuard],
    component: ProductFormComponent
  }
]

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    SignupFormComponent,
    HomeComponent,
    ProductListComponent,
    ProductListItemComponent,
    ProductDetailComponent,
    ProductFormComponent,
    HeaderComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
