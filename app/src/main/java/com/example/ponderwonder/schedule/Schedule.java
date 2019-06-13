package com.example.ponderwonder.schedule;

public class Schedule {

    private String scheduleTitle;
    private String scheduleDescription;

    public Schedule(String title, String description) {
        this.scheduleTitle=title;
        this.scheduleDescription = description;
    }

    public String getScheduleTitle() {
        return scheduleTitle;
    }

    public void setScheduleTitle(String scheduleTitle) {
        this.scheduleTitle = scheduleTitle;
    }

    public String getScheduleDescription() {
        return scheduleDescription;
    }

    public void setScheduleDescription(String scheduleDescription) {
        this.scheduleDescription = scheduleDescription;
    }

}
