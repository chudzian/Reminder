package com.example.pszen.deadlinetracker.model;

/**
 * Created by pszen on 09.01.2018.
 */

public class ListItem {
    int id;
    private String message;
    private String dateAndTime;
    private String description;

    public ListItem(String message, String dateAndTime) {
        this.message = message;
        this.dateAndTime = dateAndTime;
    }

    public ListItem(String message, String dateAndTime, String description) {
        this.message = message;
        this.dateAndTime = dateAndTime;
        this.description = description;
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
