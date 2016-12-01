package se350.logistics;

public interface OrderCostInterface {

    public double getAllItemCost();

    public double getFacilityProcessingCost();

    public double getTransportCost();

    public double getTotalCost();
}
