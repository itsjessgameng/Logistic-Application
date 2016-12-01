package se350.logistics;

public class FacilityInfo implements FacilityInfoInterface{

    private String city;
    private String state;
    private double costPerDay;


    public FacilityInfo(String city, String state, double costPerDay) {
        this.city = city;
        this.state = state;
        this.costPerDay = costPerDay;
    }

    public String getCity(){
        return city;
    }

    public String getState(){
        return state;
    }

    public double getCostPerDay(){
        return costPerDay;
    }

    private void setCity(String city) throws NullPointerException {
        this.city = city;
    }

    private void setState(String state) throws NullPointerException {
        this.state = state;
    }

    private void setCostPerDay(){
        this.costPerDay = costPerDay;
    }

}



