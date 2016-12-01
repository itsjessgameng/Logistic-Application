package se350.logistics;


import java.util.HashMap;
import java.util.Map;

public class FacilityConnections implements FacilityConnectionsInterface {

    public static final int fullDayInHours = 8;
    public static final int averageSpeed = 50;
    private HashMap<String,Double> connections;


    public FacilityConnections(HashMap<String,Double> connections) {
        this.connections = connections;

    }

    public HashMap<String,Double> getConnections(){
        return connections;
    }

    private void setConnections(HashMap<String,Double> connections){
        this.connections = connections;
    }

    public double getDistanceInDays(String name){
        double distanceInDays = 0.0;
        for(Map.Entry<String,Double> entry: connections.entrySet()){
            if(entry.getKey().equals(name)) {
                distanceInDays = entry.getValue() / (fullDayInHours * averageSpeed);
                return distanceInDays;
            }
        }
        return 0.0;
    }

}
