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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cndemoz.avalidations.EditTextValidator;
import com.cndemoz.avalidations.ValidationModel;
import com.example.demo_image_scroll.validations.IdCardValidation;
import com.example.demo_image_scroll.validations.NameValidation;
import com.example.demo_image_scroll.validations.PhoneValidation;
import com.kyleduo.switchbutton.SwitchButton;
import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;

public class MainActivity extends Activity {

    private LayoutInflater inflater;
    private BannerComponent bannerComponent;
    private TextView cityET;
    private SwitchButton checkSW;
    private EditText carnumberET, idCardET, phoneET, nameET;
    private CheckBox checkBox;
    private Button bt;
    private EditTextValidator mEditTextValidator;
    private int[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflater = LayoutInflater.from(MainActivity.this);
        initView();
    }

    public void initView() {
        images= new int[]{R.drawable.ad1, R.drawable.ad2};
        ViewPager viewPager = (ViewPager) findViewById(R.id.banner_viewPager);
        viewPager.setOffscreenPageLimit(2);
        Indicator indicator = (Indicator) findViewById(R.id.banner_indicator);
        bannerComponent=new BannerComponent(indicator,viewPager,true);
        bannerComponent.setAdapter(new MyAdapter(images));
        //默认就是800毫秒，设置单页滑动效果的时间
        bannerComponent.setScrollDuration(1500);
        //设置播放间隔时间，默认情况是3000毫秒
        bannerComponent.setAutoPlayTime(4000);
        nameET = (EditText) findViewById(R.id.name_et);
        cityET = (TextView) findViewById(R.id.citytv);
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
                if (mEditTextValidator.validate()) {
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

        mEditTextValidator = new EditTextValidator(MainActivity.this);
        mEditTextValidator.setButton(bt).add(new ValidationModel(idCardET, new IdCardValidation()))
                .add(new ValidationModel(phoneET, new PhoneValidation()))
                .add(new ValidationModel(nameET, new NameValidation()))
                .execute();
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

private class MyAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter{
    private int[] images ;
    public MyAdapter(int[] images) {
        this.images = images;
    }



    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView==null){
            convertView=inflater.inflate(R.layout.banner_tab_layout,container,false);

        }
        return convertView;
    }

    @Override
    public View getViewForPage(final int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = new ImageView(getApplicationContext());
            convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                    .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        ImageView imageView = (ImageView) convertView;
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(images[position]);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}

    @Override
    protected void onStart() {
        super.onStart();
        bannerComponent.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bannerComponent.stopAutoPlay();
    }
}
