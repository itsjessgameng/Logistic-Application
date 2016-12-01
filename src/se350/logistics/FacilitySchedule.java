package se350.logistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacilitySchedule implements FacilityScheduleInterface {

    private int ratePerDay;
    private HashMap<Integer,Integer> schedule;

    public FacilitySchedule(int ratePerDay){
        this.ratePerDay = ratePerDay;

        HashMap<Integer, Integer> schedule = new HashMap<>();

        for (int i = 1; i <= 20; i++){
            schedule.put(i, ratePerDay);
        }
        this.schedule = schedule;

    }

    @Override
    public int getRatePerDay() {
        return ratePerDay;
    }

    private void setRatePerDay(int ratePerDay) {
        this.ratePerDay = ratePerDay;
    }

    @Override
    public HashMap<Integer, Integer> getSchedule() {
        return schedule;
    }

    private void setSchedule(HashMap<Integer, Integer> schedule) {
        this.schedule = schedule;
    }

    public void processItem(int quantity){
        List<Integer> toRemove = new ArrayList<>();
        HashMap<Integer,Integer> toUpdate = new HashMap<>();
        for(Map.Entry<Integer,Integer> entry : schedule.entrySet()){
            int rate = entry.getValue();
            int day = entry.getKey();
            if (quantity >= rate){
                toRemove.add(day);
                quantity = quantity - rate;
            } else {
                if (quantity < rate && quantity > 0){
                    toUpdate.put(day,quantity);
                    quantity = 0;
                } else {
                    try {
                        throw new Exception("Invalid Quantity");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        for (Integer i : toRemove){
            schedule.remove(i);
        }
        for (Map.Entry<Integer, Integer> entry : toUpdate.entrySet()){
            schedule.put(entry.getKey(), entry.getValue());
        }
    }

}
