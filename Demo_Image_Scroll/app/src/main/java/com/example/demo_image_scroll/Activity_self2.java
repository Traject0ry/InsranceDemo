package com.example.demo_image_scroll;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;

import java.util.Calendar;


public class Activity_self2 extends Activity {
    private SwitchButton mSwitch;
    private RelativeLayout mRelativeLayout;
    private LinearLayout btnLinearLayout;
    private Button submit_btn;
    public static final int REQUSET_CODE  =12234 ;
    private Intent serviceIntent;
    final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self2);
        initView();
        initEvent();
        serviceIntent=new Intent(this,FloatService.class);

    }


    private void initView() {
        mSwitch = (SwitchButton) findViewById(R.id.switch_guohu);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.guohu_layout);
        mRelativeLayout.setVisibility(View.INVISIBLE);
        btnLinearLayout = (LinearLayout) findViewById(R.id.btn_layout);
        submit_btn = (Button) findViewById(R.id.next);
        btnLinearLayout.setY(btnLinearLayout.getY() - 50);
    }

    private void initEvent() {
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, boolean isChecked) {
                AnimatorSet set =new AnimatorSet ();
                if (isChecked) {
                    ObjectAnimator animator1=ObjectAnimator.ofFloat(btnLinearLayout,"translationY",0F).setDuration(500);
                    ObjectAnimator animator2=ObjectAnimator.ofFloat(mRelativeLayout,"alpha",0F,1F).setDuration(500);
                    animator1.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mRelativeLayout.setVisibility(View.VISIBLE);
                            mRelativeLayout.setAlpha(0);
                        }
                    });
                   set.playSequentially(animator1,animator2);
                    set.start();
                } else {
                    set.playSequentially(ObjectAnimator.ofFloat(mRelativeLayout,"alpha",1F,0F).setDuration(500),
                            ObjectAnimator.ofFloat(btnLinearLayout,"translationY",-50F).setDuration(500));

                    set.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mRelativeLayout.setVisibility(View.INVISIBLE);
                        }
                    });
                    set.start();

                }

            }
        });
    }

    public void chooseTime(final View view) {

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener
                () {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(year,monthOfYear,dayOfMonth);
                TextView textView=(TextView) view;
                textView.setText(android.text.format.DateFormat.format("yyyy-MM-dd",calendar));
            }
        }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();

    }

    public void toSearchModelNumActivity(View view){
        Intent intent=new Intent(this,SearchModelNumActivity.class);
        startActivityForResult(intent,REQUSET_CODE);
    }

    public void showDrivingLicense(View view){
        startActivity(new Intent(this,ImageShowerActivity.class));

    }
    public void submit(View view){
        Intent intent=new Intent(this,ChooseInsuranceActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(serviceIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(serviceIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(serviceIntent);
    }
}
