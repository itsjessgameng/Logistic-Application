package se350.logistics;

import java.util.HashMap;

public interface FacilityScheduleInterface {

    public HashMap<Integer,Integer> getSchedule();

    public int getRatePerDay();
}
