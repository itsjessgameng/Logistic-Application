package se350.logistics;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class FacilitySvc {
    private static FacilitySvc ourInstance = null;
    private List<FacilityImpl> facilityList;
    private final File file = new File("facility.xml");
    private List<Graph.Edge> graph;
    protected FacilitySvc(){
        ourInstance = this;
    }

    public static FacilitySvc getInstance(){
        if(ourInstance == null){
            ourInstance = new FacilitySvc();
        }
        return ourInstance;
    }

    private Facility delegate;

    public void facilityLoader(){
        if (!file.exists()) {
            try {
                throw new Exception("File does not exist. XML File " + file + " cannot be found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(!file.canRead()){
            try {
                throw new Exception("Cannot read XML File" + file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        FacilityLoader loader = new FacilityLoader(file);
        this.facilityList = loader.load();
        this.graph = loader.getGraph();
    }

    public List<FacilityImpl> getFacilityList(){
        return facilityList;
    }

    public void getAllFacilityStatus(){
        FacilityStatus allStatus = new FacilityStatus();
        allStatus.printAllStatus(facilityList);
    }

    public void getFacilityStatus(String name){
        FacilityImpl facility = getFacility(name);
        FacilityStatus status = new FacilityStatus();
        status.printStatus(facility);
    }

    public FacilityImpl getFacility(String name){
        for(FacilityImpl facility: facilityList){
            if(facility.getName().equals(name)){
                return facility;
            }
        }
        return null;
    }

    public void getShortestPath(String origin, String destination) {
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.setOrigin(origin);
        dijkstra.printPath(destination);
    }

    public HashMap<Integer, List<String>> shortestPath(String origin, String destination) {
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.setOrigin(origin);
        return dijkstra.shortestPath(destination);
    }

}
