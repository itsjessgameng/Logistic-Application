package se350.logistics;


import java.util.HashMap;

public class OrderInfo implements OrderInfoInterface{

    private String orderID;
    private Integer orderTime;
    private String destination;
    private HashMap<String, Integer> orderList;


    public OrderInfo(String orderID, Integer orderTime, String destination, HashMap<String, Integer> orderList) {
        this.orderID = orderID;
        this.orderTime = orderTime;
        this.destination = destination;
        this.orderList = orderList;
    }

    @Override
    public String getOrderID() {
        return orderID;
    }

    private void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @Override
    public Integer getOrderTime() {
        return orderTime;
    }

    private void setOrderTime(Integer orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    private void setDestination(String destination) {
        this.destination = destination;
    }

    public HashMap<String, Integer> getOrderList() {
        return orderList;
    }

    private void setOrderList(HashMap<String,Integer> orderList) {
        this.orderList = orderList;
    }
}
