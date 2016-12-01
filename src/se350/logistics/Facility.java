package se350.logistics;

public interface Facility {

    public FacilityInfo getFacilityInfo();

    public FacilityInventory getFacilityInventory();

    public FacilitySchedule getFacilitySchedule();

    public FacilityConnections getFacilityConnections();

    public String getName();

}
