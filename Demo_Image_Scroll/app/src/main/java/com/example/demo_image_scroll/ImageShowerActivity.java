package com.example.demo_image_scroll;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

public class ImageShowerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageshower);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }
}
