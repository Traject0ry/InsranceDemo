package com.example.demo_image_scroll;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo_image_scroll.entity.City;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectActivity extends Activity {
    private List<String> parent;
    private Map<String, List<City>> map;
    private ExpandableListView mainlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        map = new HashMap<>();
        parent = new ArrayList<>();
        getData();
        mainlistview = (ExpandableListView) findViewById(R.id.main_expandablelistview);
        mainlistview.setAdapter(new MyExpandableListAdapter());
        mainlistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int
                    childPosition, long id) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent.putExtra("city", map.get(SelectActivity.this
                        .parent.get(groupPosition)).get(childPosition).getName()));
                setResult(RESULT_OK, intent.putExtra("value", map.get(SelectActivity.this
                        .parent.get(groupPosition)).get(childPosition).getValue()));
                finish();

                return true;
            }
        });
    }

    private void getData() {
        List<City> list = new ArrayList<City>();
        XmlResourceParser xrp = getResources().getXml(R.xml.city_values);
        try {
            // 直到文档的结尾处
            String proviceName = "";
            City city = new City();

            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                switch (xrp.getEventType()) {
                    case XmlResourceParser.START_TAG:
                        if (xrp.getName().equals("province")) {
                            proviceName = xrp.getAttributeValue(null, "name");
                        } else if (xrp.getName().equals("name")) {
                            city.setName(xrp.nextText());
                        } else if (xrp.getName().equals("value")) {
                            city.setValue(xrp.nextText());
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (xrp.getName().equals("city")) {
                            city.setProvince(proviceName);
                            City temp = (City) city.clone();
                            list.add(temp);
                            city.clear();
                        } else if (xrp.getName().equals("province")) {
                            parent.add(proviceName);
                            List<City> tempList = new ArrayList<>();
                            tempList.addAll(list);
                            map.put(proviceName, tempList);
                            list.clear();
                            proviceName = "";
                        }
                        break;
                }
                xrp.next();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    class MyExpandableListAdapter extends BaseExpandableListAdapter {

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            String key = parent.get(groupPosition);
            return (map.get(key).get(childPosition));
        }

        //得到子item的ID
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        //设置子item的组件
        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            String key = SelectActivity.this.parent.get(groupPosition);
            String info = map.get(key).get(childPosition).getName();
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) SelectActivity.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.layout_children, null);
            }
            TextView tv = (TextView) convertView
                    .findViewById(R.id.second_textview);
            tv.setText(info);
            return convertView;
        }

        //获取当前父item下的子item的个数
        @Override
        public int getChildrenCount(int groupPosition) {
            String key = parent.get(groupPosition);
            int size = map.get(key).size();
            return size;
        }

        //获取当前父item的数据
        @Override
        public Object getGroup(int groupPosition) {
            return parent.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return parent.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        //设置父item组件
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) SelectActivity.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.layout_parent, null);
            }
            TextView tv = (TextView) convertView
                    .findViewById(R.id.parent_textview);
            tv.setText(SelectActivity.this.parent.get(groupPosition));
            return tv;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }

}