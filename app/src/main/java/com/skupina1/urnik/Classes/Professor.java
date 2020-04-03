package com.skupina1.urnik.Classes;

public class Professor {
    private String name;
    private String surname;
    private String workSpace;
    private TalkingHours talkingHours;

    public Professor(String name, String surname, String workSpace, TalkingHours talkingHours) {
        this.name = name;
        this.surname = surname;
        this.workSpace = workSpace;
        this.talkingHours = talkingHours;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getWorkSpace() {
        return workSpace;
    }

    public TalkingHours getTalkingHours() {
        return talkingHours;
    }
}
