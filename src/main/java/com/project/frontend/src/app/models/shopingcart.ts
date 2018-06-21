
// export class shopingcart{


// 	//public id:string["customersid","productid"]; 
// public id = ["customersid","productid"] ;
// 	public quantity: string;


// }
export class shopingcart {
	public cart: Cart;
  }
  export interface Cart {
	id: Id;
	quantity: number;
  }
  export interface Id {
	customersid: number;
	productid: number;
  }
  