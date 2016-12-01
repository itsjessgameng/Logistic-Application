package se350.logistics;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderSvc {
    private static OrderSvc ourInstance = null;
    private List<OrderImpl> orderImplList;
    private final File file = new File("orders.xml");
    protected OrderSvc(){
        ourInstance = this;
    }

    public static OrderSvc getInstance(){
        if(ourInstance == null){
            ourInstance = new OrderSvc();
        }
        return ourInstance;
    }

    private Order delegate;

    public void OrderLoader(){
        OrderLoader loader = new OrderLoader(file);
        this.orderImplList = loader.load();
    }

    public List<OrderImpl> getAllOrders(){
        return orderImplList;
    }

    public void getOrderSummary(){
        OrderSummary allOrders = new OrderSummary();
        allOrders.printAllOrders(orderImplList);
    }

    public void getSingleOrder(Integer orderNumber){
        OrderImpl order = getOrder(orderNumber);
        OrderSummary singleOrder = new OrderSummary();
        singleOrder.printSingleOrder(order);
    }

    public OrderImpl getOrder(Integer orderNumber){
        for(OrderImpl order: orderImplList){
            if(order.getOrderNumber() == orderNumber){
                return order;
            }
        }
        return null;
    }

    public List<HashMap<String, HashMap<String, Integer>>> processOrder(){
        List<HashMap<String, HashMap<String, Integer>>> listHash = new ArrayList<>();
        for(OrderImpl order: orderImplList){
            OrderProcessing process = new OrderProcessing();
            HashMap<String, HashMap<String, Integer>> listFacilityItem = process.getAllFacilitiesWithAllItems(order.getOrderInfo().getDestination(),
                    order.getOrderInfo().getOrderList());
            listHash.add(listFacilityItem);
        }
        return listHash;

    }

}
