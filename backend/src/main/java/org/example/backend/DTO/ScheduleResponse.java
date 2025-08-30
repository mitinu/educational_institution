package org.example.backend.DTO;

import org.example.backend.models.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleResponse {
    private ArrayList<Schedule> schedules = new ArrayList<>();


    public ScheduleResponse() {}

    public void add(Schedule schedule){
        this.schedules.add(schedule);
    }
    public void add(List<Schedule> schedules) {
        for (Schedule schedule : schedules) {
            this.add(schedule);
        }
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
    }

}
