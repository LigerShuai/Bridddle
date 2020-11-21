package com.liger.bridddle.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.liger.bridddle.R;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}