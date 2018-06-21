import { shopingcart } from './../models/shopingcart';
import { routing } from './../app.routing';
import { ActivatedRoute, Params, Route, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { shipping } from './../models/shipping';
import { Injectable } from '@angular/core';
import { Http,Headers} from '@angular/http';

@Injectable()
export class ShopingcartService {
  myMethod$: Observable<any>;
  private ship:shipping = new shipping();



  constructor(private http:Http,private ActivatedRouter:ActivatedRoute,private router:Router) {

  
   }

 

  



  get_allcarts() {
  	let url = "http://localhost:8082/getcarts";
    
    let headers = new Headers ({
      'Content-Type': 'application/json',
    });

    return this.http.get(url , {headers: headers});

}
get_cart(id:number) {
  let url = "http://localhost:8082/checkout/"+id;

    let headers = new Headers ({
    'Content-Type': 'application/json',
  });

  return this.http.get(url , {headers: headers});

}


get_product(id:number) {
  let url = "http://localhost:8082/product/"+id;

    let headers = new Headers ({
    'Content-Type': 'application/json',
  });

  return this.http.get(url , {headers: headers});

}

getbussness_product(id:number) {
  let url = "http://localhost:8082/products/bussness/"+id;

    let headers = new Headers ({
    'Content-Type': 'application/json',
  });

  return this.http.get(url , {headers: headers});

}


get_total_amounts(cartLists:shopingcart[]) {
  let url = "http://localhost:8082/amounts/";
  
  const headers = new Headers({ 'Content-Type': 'application/json' });
  return this.http.post(url,cartLists, {headers: headers});
  
}



cartList: shopingcart[];
amount:number;
num:number;
checkoutall(){
//  this.router.navigate(['/products/bussness/', id])

//use to get all product specfic bussness
// this.getbussness_product(this.busnessid).subscribe(
//   res => {
//     console.log('done y');
//     console.log(res.json());
//   },
//   error => {
//     console.log(error);
//   }
// );

this.get_allcarts().subscribe(
  res => {
   this.cartList=res.json();
//console.log(this.cartList);
this.get_total_amounts(this.cartList).subscribe(

res =>{
// this.amount=Number.parseInt(res.toString());
console.log(res.json())
this.amount=res.json();
},error =>{


}

);



  }, 
  error => {
    console.log(error);
  }
);

this.router.navigate(['/checkout/']);
}


  


}





