import { NgxBraintreeComponent } from './modules/ngx-braintree/ngx-braintree.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { CartComponent } from './components/cart/cart.component';
import { AppComponent } from './app.component';

import {ModuleWithProviders} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

const appRoutes: Routes = [
	// {
	// 	path : '',
	// 	redirectTo: '/AppComponent',
	// 	pathMatch: 'full'
	// },
	{
		path: 'cart',
		component: CartComponent
	},{
		path: 'checkout/:id',
		component: CheckoutComponent
	},{
		path: 'ngxbraintree',
		component: NgxBraintreeComponent
	},
	{
		path: 'checkout',
		component: CheckoutComponent
	},
	{
		path: 'products/bussness/:id',
		component: CheckoutComponent
	}



];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);