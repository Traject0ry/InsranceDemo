package com.example.demo_image_scroll.validations;

import android.content.Context;
import android.widget.Toast;

import com.cndemoz.avalidations.ValidationExecutor;

import java.util.regex.Pattern;

/**
 * Created by Trajectory on 2016/7/28.
 */
public class NameValidation extends ValidationExecutor {
    @Override
    public boolean doValidate(Context context, String text) {
        String regex = "[\\u4E00-\\u9FA5]{2,5}(?:·[\\u4E00-\\u9FA5]{2,5})*";
        boolean result = Pattern.compile(regex).matcher(text).find();
        if (!result) {
            Toast.makeText(context,"请输入正确的中文姓名", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }
}
