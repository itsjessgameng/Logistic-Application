package se350.logistics;

import java.util.HashMap;

public class OrderFactory {

    public OrderInfo buildOrderInfo(String orderID, Integer orderTime, String destination,
                                    HashMap<String,Integer> orderList){
        return new OrderInfo(orderID, orderTime, destination, orderList);
    }

    public OrderImpl buildOrderImpl(Integer orderNumber, OrderInfo orderInfo){
        return new OrderImpl(orderNumber, orderInfo);
    }
}
