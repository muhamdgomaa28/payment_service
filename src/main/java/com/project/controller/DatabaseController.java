package com.project.controller;

import com.project.model.OrderDetail;
import com.project.model.Orders;
import com.project.services.paymentservice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by muhamd gomaa on 1/25/2018.
 */

@RestController
@Api(value="Database controller",description = "show all restful apis to transaction in database when user pay products")
public class DatabaseController {
//    @Autowired
//    private paymentservice payservice ;



// delete  from carts by (product and customer)
//@ApiOperation("delete cart object by (product and customer)")
//@RequestMapping(value = "deletecarts/cus_id/{cid}/pro_id/{pid}", method = RequestMethod.DELETE)
//public void Delete_cart(@PathVariable int cid,@PathVariable int pid){
//        payservice.deletecart(cid,pid);
//
//    }
    ////////// delete from cart that all belong specific busness (when buy all///////////)
//    @ApiOperation("delete carts object by  specific buseness when (buy all) transaction")
//    @RequestMapping(value = "deletecarts/busniss_id/{bid}", method = RequestMethod.DELETE)
//    public void Delete_cart_busniss(@PathVariable int bid){
//        payservice.deletecart_bus(bid);
//
//    }


///////////////////////// save order api ////////////////////////////////

//    @ApiOperation("save order  and return with order object")
//    @RequestMapping(value = "/saveorder/", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public Orders save_order(@RequestBody Orders order) {
//     //   order.setDeleted(0);
// payservice.insert_order_service(order);
//       return order;
//    }
//
//////////////////// save order detailed /////////////////
//@ApiOperation("save order detail object and return with orderdetail")
//    @RequestMapping(value = "/SaveOrderDetail/", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//  public OrderDetail saveorder_detail(@RequestBody OrderDetail orderDetail ){
//
//            payservice.save_orderDetail(orderDetail);
//
//return orderDetail;
//    }
//
//    /////////// get all  current orders////////////////
//    @ApiOperation("get all orders in order detail table")
//    @GetMapping(value = "/Orders/")
//    public List<OrderDetail> get_all_orders(){
//
//   return payservice.all_orders();
//
//    }
//    /////////// delete or cancel order /////////
//    @ApiOperation("delete order by order_id when user cancl pay")
//@DeleteMapping(value="/DeleteOrder/{o_id}")
//    public void deleteOrder(@PathVariable int o_id){
//
//        payservice.delete_order(o_id);
//
//
//}
//
/////////////////////////////////
//@ApiOperation(" update order  by set deleted order =1   means user cancl pay")
//@PutMapping(value="/cancelOrder/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//public Orders cancel_Order(@RequestBody Orders order){
//        order.setDeletedorder(1);
//    payservice.insert_order_service(order);
//    return order;
//
//}









}
