package com.example.demo_image_scroll;


import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;

public class FloatService extends Service {

    WindowManager wm = null;
    WindowManager.LayoutParams wmParams = null;
    View view;
    private float mTouchStartX;
    private float mTouchStartY;
    private float x;
    private float y;
    int state;

    @Override
    public void onCreate() {
        Log.d("FloatService", "onCreate");
        super.onCreate();
        view = LayoutInflater.from(this).inflate(R.layout.floating, null);
        createView();
    }

    private void createView() {
        SharedPreferences shared = getSharedPreferences("float_flag",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putInt("float", 1);
        editor.commit();
        wm = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);
        wmParams = ((MyApplication) getApplication()).getMywmParams();
        wmParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        wmParams.flags |= 8;
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;
        wmParams.x = wm.getDefaultDisplay().getWidth();
        wmParams.y = wm.getDefaultDisplay().getHeight() * 4 / 5;
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.format = 1;

        wm.addView(view, wmParams);

        view.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                x = event.getRawX();
                y = event.getRawY() - 25;
                Log.i("currP", "currX" + x + "====currY" + y);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        state = MotionEvent.ACTION_DOWN;
                        mTouchStartX = event.getX();
                        mTouchStartY = event.getY();
                        Log.i("startP", "startX" + mTouchStartX + "====startY"
                                + mTouchStartY);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        state = MotionEvent.ACTION_MOVE;
                        updateViewPosition();
                        break;

                    case MotionEvent.ACTION_UP:
                        mTouchStartX = mTouchStartY = 0;
                        if (state != MotionEvent.ACTION_MOVE) {
                            Intent intent = new Intent(FloatService.this, UploadPhotoActivity
                                    .class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//service中启动activity需要加这个
                            startActivity(intent);
                        }
                        state = MotionEvent.ACTION_UP;
                        break;
                }
                return true;
            }
        });


    }


    private void updateViewPosition() {
        wmParams.x = (int) (x - mTouchStartX);
        wmParams.y = (int) (y - mTouchStartY);
        wm.updateViewLayout(view, wmParams);
    }


    @Override
    public void onDestroy() {
        Log.d("FloatService", "onDestroy");
        wm.removeView(view);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
