package com.example.pszen.deadlinetracker.model;

/**
 * Created by pszen on 15.01.2018.
 */

public class Reminder {
    private int id;
    private String message;
    private String dateAndTime;
    private String description;

    public Reminder() {
    }

    public Reminder(int id, String message, String dateAndTime, String description) {
        this.id = id;
        this.message = message;
        this.dateAndTime = dateAndTime;
        this.description = description;
    }

    public Reminder(String message, String dateAndTime, String description) {
        this.message = message;
        this.dateAndTime = dateAndTime;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
