package com.example.demo_image_scroll;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

public class SearchModelNumActivity extends Activity {
private SearchView searchView;
    private ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_model_num);
        initView();
        initEvent();
    }

    private void initView() {
        searchView=(SearchView)findViewById(R.id.searchView);
        searchView.onActionViewExpanded();
        back=(ImageButton)findViewById(R.id.back);
    }

    private void initEvent() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
