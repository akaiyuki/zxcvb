package com.smartwave.taskr.fragment;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.smartwave.taskr.R;
import com.smartwave.taskr.core.AppController;
import com.smartwave.taskr.core.BaseActivity;
import com.smartwave.taskr.core.DBHandler;
import com.smartwave.taskr.core.SharedPreferencesCore;
import com.smartwave.taskr.core.TSingleton;
import com.smartwave.taskr.dialog.ChooseDateDialog;
import com.smartwave.taskr.object.TaskObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDescriptionFragment extends Fragment {

    private ArrayList<TaskObject> mResultSet = new ArrayList<>();

    private FloatingActionMenu mFloatingButton;

    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;


    private List<FloatingActionMenu> menus = new ArrayList<>();
    private Handler mUiHandler = new Handler();

    private FloatingActionButton fabEdit;

    private TextView mTextDate;
    private DBHandler db;


    public TaskDescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_description, container, false);

        db = new DBHandler(getActivity());

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

        mTextDate = (TextView) view.findViewById(R.id.textdate);
        TextView mTextProject = (TextView) view.findViewById(R.id.textproject);



        mTextName.setText(SharedPreferencesCore.getSomeStringValue(AppController.getInstance(),"taskname"));
        mTextDesc.setText(SharedPreferencesCore.getSomeStringValue(AppController.getInstance(), "taskdesc"));
        mTextStatus.setText("Status: "+SharedPreferencesCore.getSomeStringValue(AppController.getInstance(), "taskstatus"));


        mTextDate.setText("Due Date: "+SharedPreferencesCore.getSomeStringValue(AppController.getInstance(),"taskdate"));
        mTextProject.setText(SharedPreferencesCore.getSomeStringValue(AppController.getInstance(),"taskproject"));


        mFloatingButton = (FloatingActionMenu) view.findViewById(R.id.menu_blue);
        mFloatingButton.setClosedOnTouchOutside(true);
        mFloatingButton.hideMenuButton(false);

        fab1 = (FloatingActionButton) view.findViewById(R.id.fab14);
        fab2 = (FloatingActionButton) view.findViewById(R.id.fab24);
        fab3 = (FloatingActionButton) view.findViewById(R.id.fab34);



        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        menus.add(mFloatingButton);

        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);
        fab3.setOnClickListener(clickListener);

        int delay = 400;
        for (final FloatingActionMenu menu : menus) {
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    menu.showMenuButton(true);
                }
            }, delay);
            delay += 150;
        }

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                fabEdit.show(true);
//            }
//        }, delay + 150);



//        mFloatingButton.setOnMenuButtonClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mFloatingButton.isOpened()) {
//                    Toast.makeText(getActivity(), mFloatingButton.getMenuButtonLabelText(), Toast.LENGTH_SHORT).show();
//                }
//
//                mFloatingButton.toggle(true);
//            }
//        });

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab14:
                    Log.d("clicked", "set estimate");
                    break;
                case R.id.fab24:
                    Log.d("clicked", "due date");
                    ChooseDateDialog datePicker = new ChooseDateDialog((BaseActivity) getActivity(), mTextDate, db);

//                    db.updateTask(Integer.parseInt(TSingleton.getTaskId()),TSingleton.getTaskName(),TSingleton.getTaskDesc(),TSingleton.getTaskStatus(),
//                            TSingleton.getTaskProject(),TSingleton.getDueDate());
//


                    break;
                case R.id.fab34:
                    Log.d("clicked", "move to finished");

                    db.updateTask(Integer.parseInt(TSingleton.getTaskId()),TSingleton.getTaskName(),TSingleton.getTaskDesc(),"finished",
                            TSingleton.getTaskProject(),TSingleton.getTaskDate());

                    break;
            }
        }
    };





}
