import { CheckoutComponent } from './../checkout/checkout.component';
import { shipping } from './../../models/shipping';
import { ShopingcartService } from './../../services/shopingcart.service';
import { shopingcart } from './../../models/shopingcart';

import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',

  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  private cartlist:shopingcart[];
  busnessid=1;

  constructor(private ShopingcartService:ShopingcartService,private router:Router) { }


  checkoutall(){

    this.router.navigate(['/checkout']);
}

 public onSubmit(){

}

  onSelect(id:string) {
    this.router.navigate(['/checkout', id])
    // .then(s => location.reload())
    ;
  }







  getallcarts() {
    this.ShopingcartService.get_allcarts().subscribe(
      res => {
        console.log(res.json());
       this.cartlist=res.json();
     
      }, 
      error => {
        console.log(error);
      }
    );
  }

  ngOnInit() {
    this.getallcarts();
  
  }

}
