package se350.logistics;

public class ItemImpl implements Item {
    private String itemID;
    private double itemCost;

    public ItemImpl(String itemID, double itemCost ) throws NullPointerException {
        this.itemID = itemID;
        this.itemCost = itemCost;
    }

    @Override
    public String getID() {
        return itemID;
    }

    @Override
    public double getCost() {
        return itemCost;
    }

    private void setID(String itemID) throws NullPointerException {
        this.itemID = itemID;
    }

    private void setCost(double itemCost) throws NullPointerException {
        this.itemCost = itemCost;
    }

}
