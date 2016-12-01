package se350.logistics;

import java.util.*;

public class OrderProcessing{

    private HashMap<String, HashMap<String, Integer>> facilityListWithItems;

    public OrderProcessing(){
        this.facilityListWithItems = new HashMap<>();
    }

    public HashMap<String, HashMap<String, Integer>> getAllFacilitiesWithAllItems(String destination,HashMap<String,Integer> orderList){
        facilityListWithItems = new HashMap<>();
        for(Map.Entry<String, Integer> entry: orderList.entrySet()){
            facilityListWithItems.put(entry.getKey(), getAllFacilitiesWithItem(entry.getKey(), destination));
        }
        return facilityListWithItems;
    }

    public HashMap<String, Integer> getAllFacilitiesWithItem(String itemID, String destination){
        FacilitySvc facilitySvc = FacilitySvc.getInstance();
        List<FacilityImpl> facilityList = facilitySvc.getFacilityList();
        HashMap<String, Integer> facilitiesWithItem = new HashMap<>();
        for(FacilityImpl facility: facilityList){
            FacilityInventory inventory = facility.getFacilityInventory();
            if(!inventory.isOutOfStock(itemID) && !facility.getName().equals(destination)){
                String facilityNameAndCity = facility.getName() + ", " + facility.getFacilityInfo().getState();
                facilitiesWithItem.put(facilityNameAndCity, inventory.getQuantity(itemID));
            }
        }

        int distance = Integer.MAX_VALUE;
        List<String> facilityPath = new ArrayList<>();
        HashMap<Integer, List<String>> shortestPath = new HashMap<>();
        shortestPath.clear();
        for(String facility : facilitiesWithItem.keySet()){
            HashMap<Integer, List<String>> shortestPathTmp = facilitySvc.shortestPath(facility, destination);
            for(Map.Entry<Integer, List<String>> entry: shortestPathTmp.entrySet()){
                int facilityDistance = entry.getKey();
                List<String> path = entry.getValue();
                if(facilityDistance < distance) {
                    facilityPath = entry.getValue();
                    path = new ArrayList<>();
                    distance = facilityDistance;
                    shortestPath = shortestPathTmp;
                }
            }
        }

        HashMap<Integer, List<String>> shortestPathWithItem = new HashMap<>();
        shortestPathWithItem.put(distance, facilityPath);
        return facilitiesWithItem;
    }

}
