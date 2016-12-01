package se350.logistics;

import java.util.HashMap;


public class FacilityFactory {


    public FacilityInfo buildFacilityInfo(String city, String state, double costPerDay) {
        return new FacilityInfo(city, state, costPerDay);
    }

    public FacilitySchedule buildFacilitySchedule(int ratePerDay) {
        return new FacilitySchedule(ratePerDay);
    }

    public FacilityInventory buildFacilityInventory(HashMap<String,Integer> inventory) {
        return new FacilityInventory(inventory);
    }

    public FacilityConnections buildFacilityConnections(HashMap<String,Double> connections) {
        return new FacilityConnections(connections);
    }

    public FacilityImpl buildFacilityImpl(String name, FacilityInfo facilityInfo, FacilitySchedule facilitySchedule,
                                                 FacilityInventory facilityInventory, FacilityConnections facilityConnections) {
        return new FacilityImpl(name, facilityInfo, facilitySchedule, facilityInventory, facilityConnections);
    }
}
