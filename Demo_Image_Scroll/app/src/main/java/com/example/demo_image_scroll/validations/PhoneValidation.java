package com.example.demo_image_scroll.validations;

import android.content.Context;
import android.widget.Toast;

import com.cndemoz.avalidations.ValidationExecutor;

import java.util.regex.Pattern;

/**
 * Created by Trajectory on 2016/7/28.
 */
public class PhoneValidation extends ValidationExecutor {
    @Override
    public boolean doValidate(Context context, String text) {
        String regex = "(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}";
        boolean result = Pattern.compile(regex).matcher(text).find();
        if (!result) {
            Toast.makeText(context,"请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
