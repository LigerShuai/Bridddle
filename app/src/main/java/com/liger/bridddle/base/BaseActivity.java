package com.liger.bridddle.base;

import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Liger
 * @date 2020/11/21 12:13
 */
public class BaseActivity extends AppCompatActivity {

    protected String TAG = "----";
    private static final int STACK_TRACE_DEEP = 4;

    private void getTag() {
        StackTraceElement[] traces = Thread.currentThread().getStackTrace();  // 最核心的方法
        String clsName = traces[STACK_TRACE_DEEP].getFileName();
        String methodName = traces[STACK_TRACE_DEEP].getMethodName();
        TAG = clsName + " " + methodName + " " + TAG;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        /*if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }*/
        getTag();

    }
}
