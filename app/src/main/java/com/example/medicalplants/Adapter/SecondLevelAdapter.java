/*
 * Copyright (c) 2017 Selva.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.medicalplants.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.example.medicalplants.R;
import com.example.medicalplants.classPackage.SecondLevel;
import com.example.medicalplants.classPackage.ThreeLevel;


public class SecondLevelAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<ThreeLevel> data;
    private List<SecondLevel> headers;
    ImageView ivGroupIndicator;


    public SecondLevelAdapter(Context context, List<SecondLevel> headers, List<ThreeLevel> data) {
        this.context = context;
        this.data = data;
        this.headers = headers;

    }

    @Override
    public Object getGroup(int groupPosition) {

        return headers.get(groupPosition);
    }

    @Override
    public int getGroupCount() {

        return headers.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_second, null);
        TextView text = (TextView) convertView.findViewById(R.id.rowSecondText);
        ImageView img = (ImageView)convertView.findViewById(R.id.rowSecondImg);
        text.setText(headers.get(groupPosition).getSecondLevelName());
        img.setImageResource(headers.get(groupPosition).getSecondLevelImg());
        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        ThreeLevel childData;
        childData = data.get(groupPosition);

        return childData;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_third, null);

        TextView textView = (TextView) convertView.findViewById(R.id.rowThirdText);
        ImageView img = (ImageView)convertView.findViewById(R.id.rowThirdImg);

        ThreeLevel childArray = data.get(groupPosition);

        String text = childArray.getThreeLevelName();

        textView.setText(text);
        img.setImageResource(childArray.getThreeLevelImg());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return 1;
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
