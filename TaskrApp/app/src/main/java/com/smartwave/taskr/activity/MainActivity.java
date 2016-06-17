package com.smartwave.taskr.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smartwave.taskr.R;
import com.smartwave.taskr.core.BaseActivity;
import com.smartwave.taskr.core.Engine;
import com.smartwave.taskr.fragment.TaskDetailsFragment;

public class MainActivity extends BaseActivity {

    public static MainActivity INSTANCE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        INSTANCE = this;

                /* Initialize Frame Layout */
        setFrameLayout(R.id.framelayout);

        Engine.switchFragment(INSTANCE, new TaskDetailsFragment(), getFrameLayout());

    }
}
