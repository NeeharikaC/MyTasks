package com.comp3617.assignment2.mytasks;

import android.util.Log;

import java.util.ArrayList;

/**

 */
public class TaskListInstance {
    private static TaskListInstance taskListInstance = null;
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private TaskListInstance() {
        //default constructor
    }

    /* Static 'instance' method */
    public static TaskListInstance getInstance( ) {

        if (taskListInstance == null){
            taskListInstance = new TaskListInstance();
        }

        return taskListInstance;
    }


    /* CRUD operation for Task Item */
    public boolean addTask(Task task) {
        Log.d("*********"," in Add Task List... " + task.getTitle());
        if (taskList.size()== 1){
            if (taskList.get(0).getTitle().equalsIgnoreCase("Title")) {
                taskList.remove(0);
            }
        }

        return(taskList.add(task));
    }

    public Task deleteTask(int position) {
        Log.d("*********","in delete Task...");
        return(taskList.remove(position));
    }

    public void updateTask(int position, Task task ) {
        Log.d("*********","in update Task...");
        taskList.set(position, task);
    }

    public Task getTask(int position) {
        Log.d("*********","in get Task...");
        return(taskList.get(position));
    }

    public ArrayList<Task> getTaskList() {

        Log.d("*********","in get Task List...");
        //For default text;
        if (taskList.isEmpty()) {
            Task tTask = new Task ("Title", "Description", "12/12/2012", "Completed", "Work", "Low", "Y");
            taskList.add(tTask);
        }

        Log.d("*********"," in get Task List... " + taskList.size());
        return taskList;
    }
}