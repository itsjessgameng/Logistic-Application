package se350.logistics;

import java.util.HashMap;

public interface OrderInfoInterface {

    public String getOrderID();

    public Integer getOrderTime();

    public String getDestination();

    public HashMap<String,Integer> getOrderList();

}
