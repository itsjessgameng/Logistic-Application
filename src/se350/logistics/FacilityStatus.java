package se350.logistics;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class FacilityStatus {
    public FacilityStatus(){

    }

    public void printAllStatus(List<FacilityImpl> facilityList) {
        for (FacilityImpl facility : facilityList) {
            printStatus(facility);
        }
    }

    public void printStatus(FacilityImpl facility) {
        String facilityName = facility.getName();
        FacilityInventory inventory = facility.getFacilityInventory();
        FacilitySchedule schedule = facility.getFacilitySchedule();
        FacilityConnections connections = facility.getFacilityConnections();
        FacilityInfo info = facility.getFacilityInfo();

        StringBuilder status = new StringBuilder();
        status.append("\n------------------------------------------------------------------------------------------\n");
        status.append(facilityName).append("\n");
        for(int i = 0; i < facilityName.length(); i++) {
            status.append("-");
        }
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        status.append("\nRate per Day: ").append(schedule.getRatePerDay());
        status.append("\nCost per Day: $").append(formatter.format(info.getCostPerDay()));
        status.append("\nDirect Links: \n");
        for(Map.Entry<String, Double> entry : connections.getConnections().entrySet()) {
            FacilitySvc svc = FacilitySvc.getInstance();
            FacilityImpl linkedFacility = svc.getFacility(entry.getKey());
            double distanceInDays = connections.getDistanceInDays(entry.getKey());
            status.append(linkedFacility.getName()).append(", ").append(linkedFacility.getFacilityInfo().getState());
            status.append("(").append(String.format("%.2f", distanceInDays)).append("d); ");
        }
        status.append("\nActive Inventory: \n");
        status.append("\tItem ID \t\tQuantity");
        for(Map.Entry<String, Integer> entry : inventory.getAll().entrySet()) {
            if(entry.getKey().length() > 7 && entry.getValue() > 0) {
                status.append("\n\t").append(entry.getKey()).append("\t\t").append(entry.getValue());
            } else {
                status.append("\n\t").append(entry.getKey()).append("\t\t\t").append(entry.getValue());
            }
        }
        status.append("\nDepleted (Used-up) Inventory: ");
        if(inventory.getAllItemsOutOfStock().size() > 0) {
            status.append("\n\tItem ID");
            for(String itemOut : inventory.getAllItemsOutOfStock()) {
                status.append("\n\t").append(itemOut);
            }
        } else {
            status.append("None");
        }
        status.append("\nSchedule: \n");
        status.append("Day: \t\t");
        for(Map.Entry<Integer, Integer> entry : schedule.getSchedule().entrySet()) {
            status.append(entry.getKey()).append("\t");
        }
        status.append("\nAvailable: \t");
        for(Map.Entry<Integer, Integer> entry : schedule.getSchedule().entrySet()) {
            status.append(entry.getValue()).append("\t");
        }
        System.out.print(status.toString());
    }
}