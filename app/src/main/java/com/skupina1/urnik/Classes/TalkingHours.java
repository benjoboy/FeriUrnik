package com.skupina1.urnik.Classes;

public class TalkingHours {
    String start;
    String end;
    String day;

    public TalkingHours(String start, String end, String day) {
        this.start = start;
        this.end = end;
        this.day = day;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getDay() {
        return day;
    }
}
