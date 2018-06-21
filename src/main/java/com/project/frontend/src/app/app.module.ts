import { ShopingcartService } from './services/shopingcart.service';
import { MyOwnCustomMaterialModule } from './material.module';
import { routing } from './app.routing';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { NgxBraintreeModule } from './modules/ngx-braintree/ngx-braintree.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';


import { AppComponent } from './app.component';
import { CartComponent } from './components/cart/cart.component';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { CheckoutComponent } from './components/checkout/checkout.component';



@NgModule({
  declarations: [
    AppComponent,
    CartComponent,
    CheckoutComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    NgxBraintreeModule,routing,MyOwnCustomMaterialModule,BrowserAnimationsModule,
    FormsModule,HttpModule
  ],
  providers: [ShopingcartService],
  bootstrap: [AppComponent]
})
export class AppModule { }
