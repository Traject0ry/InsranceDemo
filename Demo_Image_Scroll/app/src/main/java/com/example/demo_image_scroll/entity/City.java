package com.example.demo_image_scroll.entity;

/**
 * Created by admin on 2016/8/5.
 */
public class City implements Cloneable{
    private String province;
    private String value;
    private String name;

    public City() {
    }

    public City(String province, String value, String name) {
        this.province = province;
        this.value = value;
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "City{" +
                "province='" + province + '\'' +
                ", value='" + value + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void clear(){
        name="";
        value="";
        province="";
    }

}
