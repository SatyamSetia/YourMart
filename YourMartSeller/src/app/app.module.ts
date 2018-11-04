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

const routes: Routes = [
  {
    path: '',
    component: LoginFormComponent
  }, {
    path: 'register',
    component: SignupFormComponent
  }, {
    path: 'home',
    component: HomeComponent
  }, {
    path: 'product/:id',
    component: ProductDetailComponent
  }, {
    path: 'create',
    component: ProductFormComponent
  }, {
    path: 'edit/:id',
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
