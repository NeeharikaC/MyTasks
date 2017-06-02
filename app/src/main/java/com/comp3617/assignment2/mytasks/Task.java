package com.comp3617.assignment2.mytasks;

/**

 */
public class Task {

    private String title;
    private String description;
    private String dueDate;
    private String status;
    private String category;
    private String priority;
    private String reminder;

    public Task(String title, String desc, String dueDate, String status, String category, String priority,String reminder) {
        this.title = title;
        this.description = desc;
        this.dueDate = dueDate;
        this.status = status;
        this.category = category;
        this.priority = priority;
        this.reminder = reminder;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public String getPriority() {
        return priority;
    }

    public String getReminder() {
        return reminder;
    }

    public String getTaskDetails() {
        return ("Title:"+ this.title + ", Description: " + this.description + ", DueDate: " + this.dueDate + ", Status: " + this.status + ", Category: " + this.category + ", Priority: " + this.priority + ", Reminder: " + this.reminder + " !");
    }

}

