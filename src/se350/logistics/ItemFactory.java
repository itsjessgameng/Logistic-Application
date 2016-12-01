package se350.logistics;


public class ItemFactory {

    public ItemImpl build (String itemID, double itemCost) {
        return new ItemImpl(itemID, itemCost);
    }

}
