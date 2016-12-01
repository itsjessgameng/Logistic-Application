package se350.logistics;

import java.util.*;

public class FacilityInventory implements FacilityInventoryInterface {
    private HashMap<String, Integer> facilityInventory;

    public FacilityInventory (HashMap<String,Integer> facilityInventory) {
        this.facilityInventory = facilityInventory;
    }

    public HashMap<String,Integer> getAll() {
        return facilityInventory;
    }

    public Set<String> getAllItems() {
        return facilityInventory.keySet();
    }

    public int getTotalQuantity() {
        int total = 0;
        for(Map.Entry<String,Integer> entry : facilityInventory.entrySet()){
            total += entry.getValue();
        }
        return total;
    }

    public int getQuantity(String itemID) {
        if(!facilityInventory.containsKey(itemID)) {
            try {
                throw new Exception("Item ID does not exist");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return facilityInventory.get(itemID);
    }

    public boolean isOutOfStock(String itemID){
        return !facilityInventory.containsKey(itemID);
    }

    private void addOrUpdate(String itemID, Integer quantity){
        facilityInventory.put(itemID, quantity);
    }

    private void remove(String itemID){
        if(!facilityInventory.containsKey(itemID)) {
            try {
                throw new Exception("Item ID does not exist");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        facilityInventory.remove(itemID);
    }

    private void setInventory(HashMap<String,Integer> inventory){
        this.facilityInventory = inventory;
    }

    public List<String> getAllItemsOutOfStock(){
        List<String> outOfStock = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : facilityInventory.entrySet()){
            if(entry.getValue() == 0){
                outOfStock.add(entry.getKey());
            }
        }
        return outOfStock;
    }
}
