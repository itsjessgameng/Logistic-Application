package se350.logistics;

public class OrderCost {

    private double itemCost;
    private int quantity;
    private double costPerDay;
    private int facilityDays;
    private int transportDays;
    private double transportCost = 500;

    public OrderCost(){

    }

    // Calculates the total cost of items ordered
    public void getAllItemCost(){
        // itemCost * quantity
        // return type double
    }

    // Calculates the facility processing cost
    public void getFacilityProcessingCost(){
        // number of days * facility's processing cost
        // return type double
    }

    // Calculates the total transport cost
    public void getTransportCost(){
        // travel time * 500
        // return type double
    }

    // Calculates the total cost for the entire order
    public double getTotalCOst(double getAllItemCost, double getFacilityProcessingCost, double getTransportCost){
         return getAllItemCost + getFacilityProcessingCost + getTransportCost;
    }



}

