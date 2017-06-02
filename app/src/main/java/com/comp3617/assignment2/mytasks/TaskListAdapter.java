package com.comp3617.assignment2.mytasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**

 */
    public class TaskListAdapter extends ArrayAdapter<Task> {
        private final Context ctx;
        private List<Task> taskList;
        private int id;

        public TaskListAdapter(Context ctx, List<Task> tList){
            super(ctx, 0, tList);
            this.ctx = ctx;
            this.taskList = tList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View rowView = null;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.row_layout, parent, false);

            }
            else {
                rowView = convertView;

            }

            TextView tvTitle = (TextView)rowView.findViewById(R.id.taskTitle);
            TextView tvDesc = (TextView)rowView.findViewById(R.id.taskdescription);

            ImageView ivStatus = (ImageView)rowView.findViewById(R.id.imgStatus);
            ImageView ivPriority = (ImageView)rowView.findViewById(R.id.imgPriority);

            Task task = taskList.get(position);

            tvTitle.setText(task.getTitle());
            tvDesc.setText(task.getDescription());


            if (task.getStatus() != null){
                ivStatus.setVisibility(View.VISIBLE) ;
                if (task.getStatus().toString().equalsIgnoreCase("Pending")){
                    ivStatus.setImageResource(R.drawable.status_pending);

                }
                else if (task.getStatus().toString().equalsIgnoreCase("Completed")){
                    ivStatus.setImageResource(R.drawable.status_complete);


                }
            } else {
                ivStatus.setVisibility(View.INVISIBLE) ;
            }

            if (task.getPriority() != null) {
                ivPriority.setVisibility(View.VISIBLE);
                if (task.getPriority().toString().equalsIgnoreCase("Low")) {

                    ivPriority.setImageResource(R.drawable.priority_low);

                } else if (task.getPriority().toString().equalsIgnoreCase("Medium")) {
                    ivPriority.setImageResource(R.drawable.priority_medium);


                } else if (task.getPriority().toString().equalsIgnoreCase("High")) {
                    ivPriority.setImageResource(R.drawable.priority_high);


                }
            } else {
                ivPriority.setVisibility(View.INVISIBLE);
            }

            return rowView;
        }

    }

