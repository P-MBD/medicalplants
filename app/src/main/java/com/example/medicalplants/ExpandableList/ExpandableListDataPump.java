package com.example.medicalplants.ExpandableList;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> agriculture = new ArrayList<String>();
        agriculture.add("زراعت");
        agriculture.add("بررسی رویشگاه");


        List<String> keepMedicalPlants = new ArrayList<String>();
        keepMedicalPlants.add("نوع خاک");
        keepMedicalPlants.add("نیاز آبی");
        keepMedicalPlants.add("نیاز کودی");


        List<String> harvestTime = new ArrayList<String>();
        harvestTime.add("زمان گلدهی و برداشت محصول");

        expandableListDetail.put( "زراعت", agriculture);
        expandableListDetail.put("عملیات نگهداری و مراقبت از گیاه", keepMedicalPlants);
        expandableListDetail.put("زمان برداشت", harvestTime);
        return expandableListDetail;
    }
}
