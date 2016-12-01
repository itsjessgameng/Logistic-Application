package se350.logistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderSummary {
    public OrderSummary(){

    }

    public void printAllOrders(List<OrderImpl> orderImplList) {
        for (OrderImpl order : orderImplList) {
            printSingleOrder(order);

        }
    }

    public void printSingleOrder(OrderImpl order) {
        Integer orderNumber = order.getOrderNumber();
        OrderInfo orderInfo = order.getOrderInfo();

        StringBuilder status = new StringBuilder();
        status.append("\n------------------------------------------------------------------------------------------\n");
        status.append("Order #").append(orderNumber);
        status.append("\n\nOrder ID: ").append(orderInfo.getOrderID());
        status.append("\nOrder Time: Day ").append(orderInfo.getOrderTime());
        status.append("\nDestination: ").append(orderInfo.getDestination());
        status.append("\nList of Order Items: \n");
        status.append("\tItem ID \t\tQuantity");
        for (Map.Entry<String, Integer> entry : orderInfo.getOrderList().entrySet()) {
            if (entry.getKey().length() > 7 && entry.getValue() > 0) {
                status.append("\n\t").append(entry.getKey()).append("\t\t").append(entry.getValue());
            } else {
                status.append("\n\t").append(entry.getKey()).append("\t\t\t").append(entry.getValue());
            }

        }
        OrderProcessing process = new OrderProcessing();
        HashMap<String, HashMap<String, Integer>> listFacilityItem = process.getAllFacilitiesWithAllItems(order.getOrderInfo().getDestination(),
                order.getOrderInfo().getOrderList());
        status.append("\n\n");
        status.append("Processing Solution: \n\n");
        for(Map.Entry<String, HashMap<String, Integer>> entry : listFacilityItem.entrySet()) {
            status.append("\t").append(entry.getKey()).append("\n");
            status.append("\t\tFacility\t\t\tQuantity\n");
            for(Map.Entry<String, Integer> entry1: entry.getValue().entrySet()) {
                if(entry1.getKey().length() > 14 && entry1.getKey().length() < 17) {
                    status.append("\t\t").append(entry1.getKey()).append("\t\t").append(entry1.getValue()).append("\n");
                } else if(entry1.getKey().length() >= 17) {
                    status.append("\t\t").append(entry1.getKey()).append("\t").append(entry1.getValue()).append("\n");
                } else {
                    status.append("\t\t").append(entry1.getKey()).append("\t\t\t").append(entry1.getValue()).append("\n");
                }
            }
        }
        System.out.print(status.toString());
    }

}
