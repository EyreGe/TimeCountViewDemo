package com.timecountviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimeCountView timeCountView = (TimeCountView) findViewById(R.id.count_view);
        timeCountView.start();
    }
}
