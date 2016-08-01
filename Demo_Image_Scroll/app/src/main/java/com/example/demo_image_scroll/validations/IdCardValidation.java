package com.example.demo_image_scroll.validations;

import android.content.Context;
import android.widget.Toast;

import com.cndemoz.avalidations.ValidationExecutor;

import java.util.regex.Pattern;

/**
 * Created by Trajectory on 2016/7/28.
 */
public class IdCardValidation extends ValidationExecutor {
    @Override
    public boolean doValidate(Context context, String text) {
        String regex = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}" +
                "([0-9]|X)$";
        boolean result = Pattern.compile(regex).matcher(text).find();
        if (!result) {
            Toast.makeText(context,"请输入正确的身份证号", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }
}
