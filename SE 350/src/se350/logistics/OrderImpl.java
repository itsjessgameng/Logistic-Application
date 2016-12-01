package se350.logistics;

public class OrderImpl implements Order {

    private Integer orderNumber;
    private OrderInfo orderInfo;

    public OrderImpl(Integer orderNumber, OrderInfo orderInfo){
        this.orderNumber = orderNumber;
        this.orderInfo = orderInfo;
    }

    public int getOrderNumber(){
        return orderNumber;
    }

    private void setOrderNumber(int orderNumber){
        this.orderNumber = orderNumber;
    }

    public OrderInfo getOrderInfo(){
        return orderInfo;
    }
}
