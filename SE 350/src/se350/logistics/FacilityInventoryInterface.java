package se350.logistics;

import java.util.HashMap;
import java.util.Set;

public interface FacilityInventoryInterface {

    public HashMap<String,Integer> getAll();

    public Set<String> getAllItems();

    public int getTotalQuantity();

    public int getQuantity(String itemID);

    public boolean isOutOfStock(String itemID);

}
