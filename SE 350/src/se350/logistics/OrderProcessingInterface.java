package se350.logistics;

import java.util.HashMap;

public interface OrderProcessingInterface {

    public HashMap<String, HashMap<String, Integer>> getAllFacilitiesWithAllItems();

    public HashMap<String, Integer> getAllFacilitiesWithItem();
}
