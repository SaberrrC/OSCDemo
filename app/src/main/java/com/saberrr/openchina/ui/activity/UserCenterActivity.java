package com.saberrr.openchina.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.saberrr.openchina.R;
import com.saberrr.openchina.utils.Utils;

public class UserCenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.showColoredBars(this);
        setContentView(R.layout.activity_user_center);
    }
}
