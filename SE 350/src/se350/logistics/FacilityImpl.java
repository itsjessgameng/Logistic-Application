package se350.logistics;

public class FacilityImpl implements Facility {

    private String name;
    private FacilityInfo facilityInfo;
    private FacilitySchedule facilitySchedule;
    private FacilityInventory facilityInventory;
    private FacilityConnections facilityConnections;

    public FacilityImpl (String name, FacilityInfo facilityInfo, FacilitySchedule facilitySchedule,
                         FacilityInventory facilityInventory, FacilityConnections facilityConnections) {
        this.name = name;
        this.facilityInfo = facilityInfo;
        this.facilitySchedule = facilitySchedule;
        this.facilityInventory = facilityInventory;
        this.facilityConnections = facilityConnections;

    }

    @Override

    public String getName(){
        return name;
    }

    private void setName(String name){
        this.name = name;
    }

    public FacilityInfo getFacilityInfo() {
        return facilityInfo;
        }

    public FacilityInventory getFacilityInventory(){
        return facilityInventory;
    }

    public FacilitySchedule getFacilitySchedule(){
        return facilitySchedule;
    }

    public FacilityConnections getFacilityConnections(){
        return facilityConnections;
    }



}
