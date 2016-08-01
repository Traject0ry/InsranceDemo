package com.example.demo_image_scroll.bean;

import java.io.Serializable;

/**
 * Created by Trajectory on 2016/7/27.
 */
public class InsuranceBean implements Serializable{
    private String id;
    private String name;
    private String count;
    private Boolean ischecked;
    private Boolean isbijimianpei;

    public InsuranceBean(String id, String name, String count, Boolean ischecked, Boolean
            isbijimianpei) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.ischecked = ischecked;
        this.isbijimianpei = isbijimianpei;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Boolean getIschecked() {
        return ischecked;
    }

    public void setIschecked(Boolean ischecked) {
        this.ischecked = ischecked;
    }

    public Boolean getIsbijimianpei() {
        return isbijimianpei;
    }

    public void setIsbijimianpei(Boolean isbijimianpei) {
        this.isbijimianpei = isbijimianpei;
    }

    @Override
    public String toString() {
        return "InsuranceBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", count='" + count + '\'' +
                ", ischecked=" + ischecked +
                ", isbijimianpei=" + isbijimianpei +
                '}';
    }
}


