package com.smartwave.taskr.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartwave.taskr.R;
import com.smartwave.taskr.core.AppController;
import com.smartwave.taskr.core.BaseActivity;
import com.smartwave.taskr.core.DBHandler;
import com.smartwave.taskr.core.SharedPreferencesCore;
import com.smartwave.taskr.object.TaskObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDescriptionFragment extends Fragment {

    private ArrayList<TaskObject> mResultSet = new ArrayList<>();


    public TaskDescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_description, container, false);

        final DBHandler db = new DBHandler(getActivity());

         /* Initialize toolbar */
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.app_bar);
        ((BaseActivity) getActivity()).setSupportActionBar(toolbar);
        ((BaseActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((BaseActivity) getActivity()).getSupportActionBar().setTitle("Task Details");

        ImageView mImageSettings = (ImageView) toolbar.findViewById(R.id.settings);
        mImageSettings.setVisibility(View.GONE);

        TextView mTextLogout = (TextView) toolbar.findViewById(R.id.logout);
        mTextLogout.setVisibility(View.GONE);

        TextView mTextTitle = (TextView) toolbar.findViewById(R.id.titlename);
        mTextTitle.setText("Task Details");


        final List<TaskObject> tasks = db.getAllTask();

        mResultSet.clear();

        for (TaskObject taskObject : tasks) {
            String log = "Id: " + taskObject.getId() + " ,TaskName: " + taskObject.getTaskName() + " ,TaskDescription: "
                    + taskObject.getTaskDescription() + " ,TaskStatus: "
                    + taskObject.getTaskStatus();
            // Writing shops  to log
            Log.d("Task: : ", log);


            mResultSet.add(taskObject);

            Log.d("resultset", String.valueOf(mResultSet.size()));

        }



        TextView mTextName = (TextView) view.findViewById(R.id.textname);
        TextView mTextDesc = (TextView) view.findViewById(R.id.textdesc);
        TextView mTextStatus = (TextView) view.findViewById(R.id.textstatus);


        mTextName.setText("Task Name: "+SharedPreferencesCore.getSomeStringValue(AppController.getInstance(),"taskname"));
        mTextDesc.setText("Task Description: "+SharedPreferencesCore.getSomeStringValue(AppController.getInstance(), "taskdesc"));
        mTextStatus.setText("Task Status: "+SharedPreferencesCore.getSomeStringValue(AppController.getInstance(), "taskstatus"));






        return view;
    }

}
