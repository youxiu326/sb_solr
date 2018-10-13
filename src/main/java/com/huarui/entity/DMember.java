package com.huarui.entity;

import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

public class DMember implements Serializable{

    @Field
    private String id;

    @Field
    private String name;

    @Field
    private Integer age;

    @Field
    private Double height;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public DMember(){}
    public DMember(String id, String name, Integer age, Double height) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
    }

    @Override
    public String toString() {
        return "DMember{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}