package com.example.demo_image_scroll;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.demo_image_scroll.bean.InsuranceBean;
import com.example.demo_image_scroll.radioGroup.FlowRadioGroup;

import java.util.ArrayList;
import java.util.List;

public class ChooseInsuranceActivity extends Activity {
    public static final int NO_SUBLAYOUT_FLAG = -1;
    private CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10, cb11;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11;
    private FlowRadioGroup gp1, gp2, gp3, gp4,gp5;
    private List<CheckBox> checkBoxes;
    private List<InsuranceBean> insurances;
    private List<Button> buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_insurance);

        initData();
        initView();
        initEvent();

    }

    private void initData() {

        checkBoxes = new ArrayList<CheckBox>();
        insurances = new ArrayList<>();
        buttons = new ArrayList<Button>();
        insurances.add(new InsuranceBean("1", "交强险/车船险", "", false, true));
        insurances.add(new InsuranceBean("2", "车辆损失险", "", false, true));
        insurances.add(new InsuranceBean("3", "第三方责任险", "10万", false, true));
        insurances.add(new InsuranceBean("4", "全车盗抢险", "", false, true));
        insurances.add(new InsuranceBean("5", "司机责任险", "5万", false, true));
        insurances.add(new InsuranceBean("6", "乘客责任险", "1万", false, true));
        insurances.add(new InsuranceBean("7", "玻璃破碎险", "国产", false, true));
        insurances.add(new InsuranceBean("8", "自燃损失险", "", false, true));
        insurances.add(new InsuranceBean("9", "车身划痕险", "2000", false, true));
        insurances.add(new InsuranceBean("10", "涉水险", "", false, true));
        insurances.add(new InsuranceBean("11", "指定专修厂", "", false, true));
    }

    private void initView() {
        cb1 = (CheckBox) findViewById(R.id.checkbox1);
        cb2 = (CheckBox) findViewById(R.id.checkbox2);
        cb3 = (CheckBox) findViewById(R.id.checkbox3);
        cb4 = (CheckBox) findViewById(R.id.checkbox4);
        cb5 = (CheckBox) findViewById(R.id.checkbox5);
        cb6 = (CheckBox) findViewById(R.id.checkbox6);
        cb7 = (CheckBox) findViewById(R.id.checkbox7);
        cb8 = (CheckBox) findViewById(R.id.checkbox8);
        cb9 = (CheckBox) findViewById(R.id.checkbox9);
        cb10 = (CheckBox) findViewById(R.id.checkbox10);
        cb11 = (CheckBox) findViewById(R.id.checkbox11);
        checkBoxes.add(cb1);
        checkBoxes.add(cb2);
        checkBoxes.add(cb3);
        checkBoxes.add(cb4);
        checkBoxes.add(cb5);
        checkBoxes.add(cb6);
        checkBoxes.add(cb7);
        checkBoxes.add(cb8);
        checkBoxes.add(cb9);
        checkBoxes.add(cb10);
        checkBoxes.add(cb11);
        btn1 = (Button) findViewById(R.id.mianpei_btn1);
        btn2 = (Button) findViewById(R.id.mianpei_btn2);
        btn3 = (Button) findViewById(R.id.mianpei_btn3);
        btn4 = (Button) findViewById(R.id.mianpei_btn4);
        btn5 = (Button) findViewById(R.id.mianpei_btn5);
        btn6 = (Button) findViewById(R.id.mianpei_btn6);
        btn7 = (Button) findViewById(R.id.mianpei_btn7);
        btn8 = (Button) findViewById(R.id.mianpei_btn8);
        btn9 = (Button) findViewById(R.id.mianpei_btn9);
        btn10 = (Button) findViewById(R.id.mianpei_btn10);
        btn11 = (Button) findViewById(R.id.mianpei_btn11);

        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btn4);
        buttons.add(btn5);
        buttons.add(btn6);
        buttons.add(btn7);
        buttons.add(btn8);
        buttons.add(btn9);
        buttons.add(btn10);
        buttons.add(btn11);

        gp1 = (FlowRadioGroup) findViewById(R.id.radiogroup1);
        gp2 = (FlowRadioGroup) findViewById(R.id.radiogroup2);
        gp3 = (FlowRadioGroup) findViewById(R.id.radiogroup3);
        gp4 = (FlowRadioGroup) findViewById(R.id.radiogroup4);
        gp5 = (FlowRadioGroup) findViewById(R.id.radiogroup5);
    }

    private void initEvent() {
        for (int i = 0; i < insurances.size(); i++) {

            checkBoxes.get(i).setChecked(insurances.get(i).getIschecked());
            checkedChangeEvent(checkBoxes.get(i), insurances.get(i).getIschecked());
        }
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton
                .OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton v, boolean b) {
                checkedChangeEvent(v, b);
            }
        };
        for (CheckBox checkBox : checkBoxes) {
            checkBox.setOnCheckedChangeListener(listener);
        }
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                switch (v.getId()) {
                    case R.id.mianpei_btn1:
                        changeColor(button, 0);
                        break;
                    case R.id.mianpei_btn2:
                        changeColor(button, 1);
                        break;
                    case R.id.mianpei_btn3:
                        changeColor(button, 2);
                        break;
                    case R.id.mianpei_btn4:
                        changeColor(button, 3);
                        break;
                    case R.id.mianpei_btn5:
                        changeColor(button, 4);
                        break;
                    case R.id.mianpei_btn6:
                        changeColor(button, 5);
                        break;
                    case R.id.mianpei_btn7:
                        changeColor(button, 6);
                        break;
                    case R.id.mianpei_btn8:
                        changeColor(button, 7);
                        break;
                    case R.id.mianpei_btn9:
                        changeColor(button, 8);
                        break;
                    case R.id.mianpei_btn10:
                        changeColor(button, 9);
                        break;
                    case R.id.mianpei_btn11:
                        changeColor(button, 10);
                        break;
                }

            }
        };
        for (Button button : buttons) {

            button.setOnClickListener(clickListener);
        }

    }

    private void hideOrShow(CompoundButton v, boolean b, int layoutId) {
        if (b) {
            LinearLayout linearLayout = (LinearLayout) v.getParent();
            linearLayout.getChildAt(1).setVisibility(View.VISIBLE);
            linearLayout.getChildAt(2).setVisibility(View.VISIBLE);
            if (layoutId != NO_SUBLAYOUT_FLAG) {
                findViewById(layoutId).setVisibility(View.VISIBLE);
            }
        } else {

            LinearLayout linearLayout = (LinearLayout) v.getParent();
            linearLayout.getChildAt(1).setVisibility(View.INVISIBLE);
            linearLayout.getChildAt(2).setVisibility(View.INVISIBLE);
            if (layoutId != NO_SUBLAYOUT_FLAG) {
                findViewById(layoutId).setVisibility(View.GONE);
            }
        }

    }

    private void changeColor(Button btn, int i) {
        if (insurances.get(i).getIsbijimianpei()) {
            btn.setBackground(getResources().getDrawable(R.drawable.radius5dp_sm_gray));
            insurances.get(i).setIsbijimianpei(false);
        } else {
            btn.setBackground(getResources().getDrawable(R.drawable.radius5dp_sm_orange));
            insurances.get(i).setIsbijimianpei(true);
        }
    }

    private void checkedChangeEvent(CompoundButton v, boolean b) {
        switch (v.getId()) {
            case R.id.checkbox1:
                hideOrShow(checkBoxes.get(0), b, NO_SUBLAYOUT_FLAG);
                insurances.get(0).setIschecked(b);
                break;
            case R.id.checkbox2:
                hideOrShow(checkBoxes.get(1), b, NO_SUBLAYOUT_FLAG);
                insurances.get(1).setIschecked(b);
                break;
            case R.id.checkbox3:
                hideOrShow(checkBoxes.get(2), b, R.id.disanfanglayout);
                insurances.get(2).setIschecked(b);
                gp1.setCheckedStateByText(insurances.get(2).getCount(),true);
                break;
            case R.id.checkbox4:
                hideOrShow(checkBoxes.get(3), b, NO_SUBLAYOUT_FLAG);
                insurances.get(3).setIschecked(b);
                break;
            case R.id.checkbox5:
                hideOrShow(checkBoxes.get(4), b, R.id.sijilayout);
                insurances.get(4).setIschecked(b);
                gp2.setCheckedStateByText(insurances.get(4).getCount(),true);
                break;
            case R.id.checkbox6:
                hideOrShow(checkBoxes.get(5), b, R.id.chengkelayout);
                insurances.get(5).setIschecked(b);
                gp3.setCheckedStateByText(insurances.get(5).getCount(),true);
                break;
            case R.id.checkbox7:
                hideOrShow(checkBoxes.get(6), b, R.id.bolilayout);
                insurances.get(6).setIschecked(b);
                gp4.setCheckedStateByText(insurances.get(6).getCount(),true);
                break;
            case R.id.checkbox8:
                hideOrShow(checkBoxes.get(7), b, NO_SUBLAYOUT_FLAG);
                insurances.get(7).setIschecked(b);
                break;
            case R.id.checkbox9:
                hideOrShow(checkBoxes.get(8), b, R.id.cheshenlayout);
                insurances.get(8).setIschecked(b);
                gp5.setCheckedStateByText(insurances.get(8).getCount(),true);
                break;
            case R.id.checkbox10:
                hideOrShow(checkBoxes.get(9), b, NO_SUBLAYOUT_FLAG);
                insurances.get(9).setIschecked(b);
                break;
            case R.id.checkbox11:
                hideOrShow(checkBoxes.get(10), b, NO_SUBLAYOUT_FLAG);
                insurances.get(10).setIschecked(b);
                break;

        }
    }

    public void next(View view) {
        Intent intent = new Intent(this, ChooseCompanyActivty.class);
        startActivity(intent);
    }
}
