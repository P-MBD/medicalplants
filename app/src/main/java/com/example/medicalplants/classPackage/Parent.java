package com.example.medicalplants.classPackage;

public class Parent {

    private String parentName;
    private int parentImg;

    public Parent(){

    }

    public Parent(String parentName, int parentImg) {
        this.parentName = parentName;
        this.parentImg = parentImg;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public int getParentImg() {
        return parentImg;
    }

    public void setParentImg(int parentImg) {
        this.parentImg = parentImg;
    }
}
