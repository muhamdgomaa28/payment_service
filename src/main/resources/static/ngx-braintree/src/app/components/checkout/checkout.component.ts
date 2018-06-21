import { shopingcart } from './../../models/shopingcart';
import { shipping } from './../../models/shipping';
import { ShopingcartService } from './../../services/shopingcart.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
selectedTab:number;
shopingcart:shopingcart;
productid:number;
amount:number;
 cartList: shopingcart[];
constructor(private router:Router,private ShopingcartService:ShopingcartService,private ActivatedRouter:ActivatedRoute) { 

}

  // goToReview() {
  // 	this.selectedTab=2;
  // }


  getallcarts() {
    this.ShopingcartService.get_allcarts().subscribe(
      res => {
       this.cartList=res.json();
    console.log(this.cartList);

      }, 
      error => {
        console.log(error);
      }
    );
  }



  ngOnInit() {

   
  	// this.ActivatedRouter.params.forEach((params: Params) => {
  	// 	this.productid= Number.parseInt(params['id']);
  	// });

//   	this.ShopingcartService.get_cart(this.productid).subscribe(
//   		res => {
// this.shopingcart = res.json();
//       },
//   		error => {
//   			console.log(error);
//   		}
//     );
//get the amount to pay 
//     this.ShopingcartService.get_product(this.productid).subscribe(
//       data => {
// //calculate the amount to pay
// this.amount=data.json().price-data.json().discount;

//       },
//   		error => {
//   			console.log(error);
//   		}





    //);




    //sthis.getallcarts();


}

}