package com.comp3617.assignment2.mytasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView lvTasks;
    Intent intent;
    private static final int REQUEST_CODE = 0;
    TaskListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTasks = (ListView) findViewById(R.id.lvTasks);

        adapter = new TaskListAdapter(this, TaskListInstance.getInstance().getTaskList());
        lvTasks.setAdapter(adapter);


           lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            intent.putExtra("taskPosition", position);
            startActivity(intent);
        }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.notifyDataSetChanged();

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.action_add) {

            intent = new Intent(this,AddTaskActivity.class);

           startActivity(intent);
        } else return super.onOptionsItemSelected(item);


        return true;
    }


}