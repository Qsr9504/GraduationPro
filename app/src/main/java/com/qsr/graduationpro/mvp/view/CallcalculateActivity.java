package com.qsr.graduationpro.mvp.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qsr.graduationpro.R;
import com.qsr.graduationpro.utils.ActivityManager;

public class CallcalculateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_callcalculate);
    }
    public void back(View view){
        ActivityManager.getInstance().removeCurrent();
    }
}
