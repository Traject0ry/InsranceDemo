package com.example.demo_image_scroll;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseCompanyActivty extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_company_activty);
    }

    public void hebao(View view){

        Intent intent=new Intent(ChooseCompanyActivty.this,HebaoActivity.class);
        startActivity(intent);
    }
}
