package com.example.pszen.deadlinetracker.model;

/**
 * Created by pszen on 15.01.2018.
 */

public class Reminder {
    private int id;
    private String message;
    private long dateAndTime;
    private String description;
    private int notificationId;
    private int pictogram;

    public Reminder() {
    }

    public Reminder(int id, String message, long dateAndTime, String description) {
        this.id = id;
        this.message = message;
        this.dateAndTime = dateAndTime;
        this.description = description;
    }

    public Reminder(String message, long dateAndTime, String description) {
        this.message = message;
        this.dateAndTime = dateAndTime;
        this.description = description;
    }

    public Reminder(int id, String message, long dateAndTime, String description, int notificationId, int pictogram) {
        this.id = id;
        this.message = message;
        this.dateAndTime = dateAndTime;
        this.description = description;
        this.notificationId = notificationId;
        this.pictogram = pictogram;
    }

    public Reminder(String message, long dateAndTime, String description, int notificationId, int pictogram) {
        this.message = message;
        this.dateAndTime = dateAndTime;
        this.description = description;
        this.notificationId = notificationId;
        this.pictogram = pictogram;
    }

    public Reminder(String message, long dateAndTime, String description, int notificationId) {
        this.message = message;
        this.dateAndTime = dateAndTime;
        this.description = description;
        this.notificationId = notificationId;
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

    public long getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(long dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getPictogram() {
        return pictogram;
    }

    public void setPictogram(int pictogram) {
        this.pictogram = pictogram;
    }
}
