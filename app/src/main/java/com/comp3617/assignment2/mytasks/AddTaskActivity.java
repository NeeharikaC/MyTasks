package com.comp3617.assignment2.mytasks;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener {

  public  List<Task> t_list = new ArrayList<Task>();
    int forTaskPosition = -1;
    boolean flagDelete = false;
    boolean flagShare = false;

    private DatePicker datePicker;
    private Calendar calendar;

    private int year, month, day;

    EditText tvDueDate;
    final static int req1=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        final RelativeLayout rl = null;

        Button button= (Button) findViewById(R.id.add_button);
        Button buttonUpdate = (Button) findViewById(R.id.update_button);
        Button buttonDelete = (Button) findViewById(R.id.delete_button);
        Button buttonEmail = (Button) findViewById(R.id.email_button);
        Button buttonShare = (Button) findViewById(R.id.share_button);

        tvDueDate = (EditText) findViewById(R.id.duedate_Text);

        Intent i = getIntent();
        if (i != null &&  i.hasExtra("taskPosition")){
            forTaskPosition = i.getIntExtra("taskPosition",0);

            if (forTaskPosition >= 0){
                Log.d("*********", "Update / Delete Task Visibility");
                button.setVisibility(View.INVISIBLE);
                buttonUpdate.setVisibility(View.VISIBLE);
                buttonDelete.setVisibility(View.VISIBLE);
                buttonEmail.setVisibility(View.VISIBLE);
                buttonShare.setVisibility(View.VISIBLE);
            }

            Task tempTask = TaskListInstance.getInstance().getTask(forTaskPosition);

            EditText tvTitle = (EditText) findViewById(R.id.titleText);
            EditText tvDesc = (EditText) findViewById(R.id.descText);
            RadioButton tvStatus;
            RadioButton tvCategory;
            RadioButton tvPriority;
            CheckBox tvReminder;

            tvTitle.setText(tempTask.getTitle());
            tvDesc.setText(tempTask.getDescription());
            tvDueDate.setText(tempTask.getDueDate());

            if (tempTask.getStatus() != null) {
                if (tempTask.getStatus().toString().equalsIgnoreCase("Pending")) {
                    tvStatus = (RadioButton) findViewById(R.id.pending_RB);
                    tvStatus.setChecked(true);
                } else if (tempTask.getStatus().toString().equalsIgnoreCase("Completed")) {
                    tvStatus = (RadioButton) findViewById(R.id.complete_RB);
                    tvStatus.setChecked(true);
                }
            }

            if (tempTask.getCategory() != null) {
                if (tempTask.getCategory().toString().equalsIgnoreCase("Work")) {
                    tvCategory = (RadioButton) findViewById(R.id.work_RB);
                    tvCategory.setChecked(true);
                } else if (tempTask.getCategory().toString().equalsIgnoreCase("home")) {
                    tvCategory = (RadioButton) findViewById(R.id.home_RB);
                    tvCategory.setChecked(true);
                } else if (tempTask.getCategory().toString().equalsIgnoreCase("Personal")) {
                    tvCategory = (RadioButton) findViewById(R.id.personal_RB);
                    tvCategory.setChecked(true);
                }
            }

            if (tempTask.getPriority() != null) {
                if (tempTask.getPriority().toString().equalsIgnoreCase("Low")){
                tvPriority = (RadioButton) findViewById(R.id.low_RB);
                tvPriority.setChecked(true);
            } else if (tempTask.getPriority().toString().equalsIgnoreCase("Medium")){
                tvPriority = (RadioButton) findViewById(R.id.medium_RB);
                tvPriority.setChecked(true);
            } else if (tempTask.getPriority().toString().equalsIgnoreCase("High")){
                tvPriority = (RadioButton) findViewById(R.id.high_RB);
                tvPriority.setChecked(true);
            }
            }

            if (tempTask.getReminder() != null) {
                if (tempTask.getReminder().toString().equalsIgnoreCase("Y")) {
                    tvReminder = (CheckBox) findViewById(R.id.reminder_CB);
                    tvReminder.setChecked(true);
                }
            }


        } else {
                Log.d("*********", "Add Task Visibility");
                button.setVisibility(View.VISIBLE);
                buttonUpdate.setVisibility(View.INVISIBLE);
                buttonDelete.setVisibility(View.INVISIBLE);
                buttonEmail.setVisibility(View.INVISIBLE);
                buttonShare.setVisibility(View.INVISIBLE);

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                showDate(year, month+1, day);

        }

        tvDueDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    setDate(v);

                    Log.d("*********", "Got Focus - Due Date" );
                }else {
                    Log.d("*********", "Lost Focus - Due Date" );

                }
            }
        });

    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    @SuppressWarnings("deprecation")
    protected Dialog onCreateDialog(int id) {

        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {

                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        String mth="";
        String dy="";

        if (month < 10){
            mth = "0" + month;
        } else {
            mth = "" + month;
        }

        if (day < 10) {
            dy = "0" + day;
        } else {
            dy = "" + day;
        }

        tvDueDate.setText(new StringBuilder().append(dy).append("/")
                .append(mth).append("/").append(year));
    }

    private String getCurrentDate() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        return (new StringBuilder().append(day).append("/").append(month+1).append("/").append(year)).toString();
    }

    private void setAlarm(Calendar target){
        Log.d("*********", "Reminder is set @ " + target.getTime());

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), req1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, target.getTimeInMillis(), pendingIntent);
    }

    private void cancelAlarm(){
        Log.d("*********", "Reminder is cancelled @ ");
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), req1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

    private boolean invalidDueDate (String tDueDate){
        boolean invalidFlag = false;

        Calendar calNow = Calendar.getInstance();

        Calendar calDueDate = Calendar.getInstance();
        calDueDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tDueDate.substring(0,2)));
        calDueDate.set(Calendar.MONTH, (Integer.parseInt(tDueDate.substring(3,5))-1));
        calDueDate.set(Calendar.YEAR, Integer.parseInt(tDueDate.substring(6)));
        calDueDate.set(Calendar.HOUR_OF_DAY, 8);
        calDueDate.set(Calendar.MINUTE, 0);
        calDueDate.set(Calendar.SECOND, 0);
        calDueDate.set(Calendar.MILLISECOND, 0);

        Log.d("********* calNow: " , ""+calNow.getTime());
        Log.d("********* calDueDate: " , ""+calDueDate.getTime());

        if (calDueDate.before(calNow) || calDueDate.equals(calNow)) {
            Log.d("*********", "calDueDate Before/Equal");
            tvDueDate.setError("Selected date should NOT be in past.");
            invalidFlag = true;
        }
        return invalidFlag;
    }


    @Override
    public void onClick(View v) {

        EditText tvTitle = (EditText) findViewById(R.id.titleText);



        String strTaskId = tvTitle.getText().toString();

        if(TextUtils.isEmpty(strTaskId)) {
            tvTitle.setError("Please specify Task title.");
            return;
        }


        EditText tvDesc = (EditText) findViewById(R.id.descText);
        EditText tvDueDate = (EditText) findViewById(R.id.duedate_Text);

        String tDueDateCheck = tvDueDate.getText().toString();

        if(! TextUtils.isEmpty(tDueDateCheck)) {
            if (invalidDueDate(tDueDateCheck)){
                Log.d("*********", "Invalid Due date.");
                tvDueDate.setError("Selected date should NOT be in past.");
                return;
            }
        }



        RadioGroup tvRG = (RadioGroup) findViewById(R.id.status_Radio);
        int checkedRadioButtonId = 0;
        checkedRadioButtonId = tvRG.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) findViewById(checkedRadioButtonId);

        String tStatus = null;

        if (radioButton != null) {
            if (radioButton.getId() == R.id.pending_RB) {
                tStatus = "Pending";
            } else if (radioButton.getId() == R.id.complete_RB) {
                tStatus = "Completed";
            }
        }


        tvRG = (RadioGroup) findViewById(R.id.priority_Radio);
        checkedRadioButtonId = 0;
        checkedRadioButtonId = tvRG.getCheckedRadioButtonId();

        radioButton = (RadioButton) findViewById(checkedRadioButtonId);

        String tPriority = null;

        if (radioButton != null) {
            if (radioButton.getId() == R.id.low_RB) {
                tPriority = "Low";
            } else if (radioButton.getId() == R.id.medium_RB) {
                tPriority = "Medium";
            } else if (radioButton.getId() == R.id.high_RB) {
                tPriority = "High";
            }
        }

        tvRG = (RadioGroup) findViewById(R.id.category_Radio);
        checkedRadioButtonId = 0;
        checkedRadioButtonId = tvRG.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(checkedRadioButtonId);

        String tCategory = null;
        if (radioButton != null) {
            if (radioButton.getId() == R.id.work_RB) {
                tCategory = "Work";
            } else if (radioButton.getId() == R.id.home_RB) {
                tCategory = "Home";
            } else if (radioButton.getId() == R.id.personal_RB) {
                tCategory = "Personal";
            }
        }
        String tReminder = null;
        CheckBox tvCBox = (CheckBox) findViewById(R.id.reminder_CB);
            if (tvCBox != null) {
            if (tvCBox.isChecked()){
                tReminder = "Y";

                tvDueDate = (EditText) findViewById(R.id.duedate_Text);
                tDueDateCheck = tvDueDate.getText().toString();
                if(! TextUtils.isEmpty(tDueDateCheck)) {
                    if (invalidDueDate(tDueDateCheck)){
                        Log.d("*********", "Invalid Due date.");
                        tvDueDate.setError("Reminder cannot be set as entered Due Date in past.");
                        return;
                    }
                }

                Calendar cal1 = Calendar.getInstance();

              cal1.set(Integer.parseInt(tDueDateCheck.substring(6)), Integer.parseInt(tDueDateCheck.substring(3,5))-1, Integer.parseInt(tDueDateCheck.substring(0,2)), 8, 0, 0);
                Log.d("*********", "set Reminder Value...YEAR: " + Integer.parseInt(tDueDateCheck.substring(6)) + ", MONTH: " + (Integer.parseInt(tDueDateCheck.substring(3,5))-1) + ", DAY: " +  Integer.parseInt(tDueDateCheck.substring(0,2)));

                setAlarm(cal1);
            } else {
                tReminder = "N";
                cancelAlarm();
            }
        }

        String tTitle = tvTitle.getText().toString();
        String tDesc = tvDesc.getText().toString();
        String tDueDate = tvDueDate.getText().toString(); //new Date().toString();

        Task tempTask = new Task(tTitle, tDesc, tDueDate, tStatus, tCategory, tPriority, tReminder);

        switch(v.getId())
        {
            case R.id.add_button :
                TaskListInstance.getInstance().addTask(tempTask);
                Log.d("*********", "Add Task Call" );
                break;
            case R.id.update_button :
                TaskListInstance.getInstance().updateTask(forTaskPosition, tempTask);
                Log.d("*********", "Update Task Call");
                break;
            case R.id.delete_button :
                Task deletedTask = TaskListInstance.getInstance().deleteTask(forTaskPosition);
                Log.d("*********", "Delete Task Call");
                break;
            case R.id.email_button :

                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Task Details for " + tempTask.getTitle() + " !");
                intent.putExtra(Intent.EXTRA_TEXT, tempTask.getTaskDetails());
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                Log.d("*********", "Email Task Call");
                break;
            case R.id.share_button :
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, tempTask.getTaskDetails());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                Log.d("*********", "Share Task Call");
                break;

        }

        finish();
    }
}

