package com.example.demo_image_scroll;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cndemoz.avalidations.EditTextValidator;
import com.cndemoz.avalidations.ValidationModel;
import com.example.demo_image_scroll.validations.IdCardValidation;
import com.example.demo_image_scroll.validations.NameValidation;
import com.example.demo_image_scroll.validations.PhoneValidation;
import com.kyleduo.switchbutton.SwitchButton;

public class MainActivity extends Activity {

    private LayoutInflater inflater;
    private ViewPager mviewPager;

    /**
     * 用于小圆点图片
     */
    private List<ImageView> dotViewList;
    /**
     * 用于存放轮播效果图片
     */
    private List<ImageView> list;
    LinearLayout dotLayout;

    private int currentItem = 0;//当前页面

    boolean isAutoPlay = true;//是否自动轮播

    private ScheduledExecutorService scheduledExecutorService;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if (msg.what == 100) {
                mviewPager.setCurrentItem(currentItem);
            }
        }

    };

    private EditText cityET;

    private SwitchButton checkSW;
    private EditText carnumberET, idCardET, phoneET, nameET;
    private CheckBox checkBox;
    private Button bt;
    private EditTextValidator meditTextValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inflater = LayoutInflater.from(MainActivity.this);

        mviewPager = (ViewPager) findViewById(R.id.myviewPager);
        dotLayout = (LinearLayout) findViewById(R.id.dotLayout);
        dotLayout.removeAllViews();

        initView();

        if (isAutoPlay) {
            startPlay();
        }


    }

    public void initView() {
        dotViewList = new ArrayList<ImageView>();
        list = new ArrayList<ImageView>();


        for (int i = 0; i < 2; i++) {
            ImageView dotView = new ImageView(MainActivity.this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

            params.leftMargin = 5;//设置小圆点的外边距
            params.rightMargin = 5;

            params.height = 4;//设置小圆点的大小
            params.width = 4;

            if (i == 0) {
                dotView.setBackgroundResource(R.drawable.point_pressed);
            } else {

                dotView.setBackgroundResource(R.drawable.point_unpressed);
            }
            dotLayout.addView(dotView, params);

            dotViewList.add(dotView);
            //上面是动态添加了四个小圆点
        }


        ImageView img1 = (ImageView) inflater.inflate(R.layout.scroll_vew_item, null);
        ImageView img2 = (ImageView) inflater.inflate(R.layout.scroll_vew_item, null);


        img1.setBackgroundResource(R.drawable.ad1);
        img2.setBackgroundResource(R.drawable.ad2);


        list.add(img1);
        list.add(img2);


        ImagePaperAdapter adapter = new ImagePaperAdapter((ArrayList) list);

        mviewPager.setAdapter(adapter);
        mviewPager.setCurrentItem(0);
        mviewPager.setOnPageChangeListener(new MyPageChangeListener());
        nameET = (EditText) findViewById(R.id.name_et);
        cityET = (EditText) findViewById(R.id.cityet);
        idCardET = (EditText) findViewById(R.id.id_card_et);
        phoneET = (EditText) findViewById(R.id.phone_et);
        carnumberET = (EditText) findViewById(R.id.carnumberET);
        checkSW = (SwitchButton) findViewById(R.id.check_sw);
        checkSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    carnumberET.setVisibility(View.INVISIBLE);

                } else {
                    carnumberET.setVisibility(View.VISIBLE);
                }
            }
        });
        bt = (Button) findViewById(R.id.submit_bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (meditTextValidator.validate()) {
                    Intent intent = new Intent(MainActivity.this, Activity_self2.class);
                    startActivity(intent);
                }
            }
        });
        checkBox = (CheckBox) findViewById(R.id.xieyi_checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    bt.setEnabled(false);
                } else {
                    bt.setEnabled(true);
                }
            }
        });

        meditTextValidator = new EditTextValidator(MainActivity.this);
        meditTextValidator.setButton(bt).add(new ValidationModel(idCardET, new IdCardValidation()))
                .add(new ValidationModel(phoneET, new PhoneValidation()))
                .add(new ValidationModel(nameET,new NameValidation()))
                .execute();
    }

    /**
     * 开始轮播图切换
     */
    private void startPlay() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
        //根据他的参数说明，第一个参数是执行的任务，第二个参数是第一次执行的间隔，第三个参数是执行任务的周期；
    }

    /**
     * 执行轮播图切换任务
     */
    private class SlideShowTask implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (mviewPager) {
                currentItem = (currentItem + 1) % list.size();
                handler.sendEmptyMessage(100);
            }
        }
    }

    /**
     * ViewPager的监听器
     * 当ViewPager中页面的状态发生改变时调用
     */
    private class MyPageChangeListener implements OnPageChangeListener {

        boolean isAutoPlay = false;

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            switch (arg0) {
                case 1:// 手势滑动，空闲中
                    isAutoPlay = false;
                    System.out.println(" 手势滑动，空闲中");
                    break;
                case 2:// 界面切换中
                    isAutoPlay = true;
                    System.out.println(" 界面切换中");
                    break;
                case 0:// 滑动结束，即切换完毕或者加载完毕
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (mviewPager.getCurrentItem() == mviewPager.getAdapter().getCount() - 1 &&
                            !isAutoPlay) {
                        mviewPager.setCurrentItem(0);
                        System.out.println(" 滑动到最后一张");
                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (mviewPager.getCurrentItem() == 0 && !isAutoPlay) {
                        mviewPager.setCurrentItem(mviewPager.getAdapter().getCount() - 1);
                        System.out.println(" 滑动到第一张");
                    }
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onPageSelected(int pos) {
            // TODO Auto-generated method stub
            //这里面动态改变小圆点的被背景，来实现效果
            currentItem = pos;
            for (int i = 0; i < dotViewList.size(); i++) {
                if (i == pos) {
                    ((View) dotViewList.get(pos)).setBackgroundResource(R.drawable.point_pressed);
                } else {
                    ((View) dotViewList.get(i)).setBackgroundResource(R.drawable.point_unpressed);
                }
            }
        }

    }

    public void selectCity(View view) {

        Intent i = new Intent(MainActivity.this, SelectActivity.class);
        startActivityForResult(i, 10001);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10001 && resultCode == RESULT_OK) {
            cityET.setText(data.getStringExtra("city"));
            carnumberET.setText("浙B");

        }
    }

    public void xieyi(View view) {
        new AlertDialog.Builder(MainActivity.this).setTitle("服务协议")//设置对话框标题

                .setMessage(R.string.xieyi_string)//设置显示的内容

                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }


    @Override
    protected void onPause() {
        super.onPause();
        scheduledExecutorService.shutdown();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startPlay();
    }
}
