package com.example.medicalplants.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.medicalplants.InformationActivity;
import com.example.medicalplants.Model.MedicalPlants;
import com.example.medicalplants.R;

public class MedicalPlantsListAdapter extends BaseAdapter implements Filterable {
    Context mContext;
    List<MedicalPlants> list_plants;
    List<MedicalPlants> list_plants_display;

    public MedicalPlantsListAdapter(Context context, int resource, List<MedicalPlants> items) {
        this.mContext = context;
        this.list_plants = items;
        this.list_plants_display = items;
    }

    @Override
    public int getCount() {
        return list_plants_display.size();
    }

    @Override
    public Object getItem(int position) {
        return list_plants_display.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list_plants_display.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder viewholder;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.medicalplants_row, null);
            viewholder = new Viewholder();
            viewholder.txt_id = convertView.findViewById(R.id.txt_id);
            viewholder.txt_PlantsName = convertView.findViewById(R.id.txt_MedicalPlant);
            viewholder.txt_SCName = convertView.findViewById(R.id.txt_MedicalPlant_english);
            viewholder.relative = convertView.findViewById(R.id.relative);
            viewholder.imageView = convertView.findViewById(R.id.img);
            convertView.setTag(viewholder);
        } else {
            viewholder = (Viewholder) convertView.getTag();
        }

        MedicalPlants currentPlant = list_plants_display.get(position);
        viewholder.txt_PlantsName.setText(currentPlant.getPlantsName());
        viewholder.txt_SCName.setText(currentPlant.getSCName());
        viewholder.txt_id.setText(String.valueOf(currentPlant.getId()));

        String img = currentPlant.getImg2();
        if (img != null && !img.isEmpty()) {
            int imageResId = mContext.getResources().getIdentifier(img, "drawable", mContext.getPackageName());
            if (imageResId != 0) {
                viewholder.imageView.setImageResource(imageResId);
            } else {
                viewholder.imageView.setImageResource(R.drawable.ic_launcher_background); // تصویر پیش‌فرض
            }
        } else {
            viewholder.imageView.setImageResource(R.drawable.ic_launcher_background); // تصویر پیش‌فرض
        }

        // اضافه کردن OnClickListener برای کلیک روی آیتم
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ایجاد یک Intent برای انتقال به InformationActivity
                Intent intent = new Intent(mContext, InformationActivity.class);
                // ارسال ID گیاه به InformationActivity
                intent.putExtra("ID_STATION", String.valueOf(currentPlant.getId()));
                // شروع Activity
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    private class Viewholder {
        TextView txt_PlantsName, txt_SCName, txt_id;
        LinearLayout relative;
        ImageView imageView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();
                ArrayList<MedicalPlants> plants = new ArrayList<>();

                if (charSequence == null || charSequence.length() == 0) {
                    results.count = list_plants.size();
                    results.values = list_plants;
                } else {
                    charSequence = charSequence.toString().toLowerCase();
                    for (int i = 0; i < list_plants.size(); i++) {
                        String plant_name = list_plants.get(i).getPlantsName().toLowerCase();
                        String plant_sCName = list_plants.get(i).getSCName().toLowerCase();

                        if (plant_name.contains(charSequence) || plant_sCName.contains(charSequence)) {
                            plants.add(new MedicalPlants(list_plants.get(i).getId(), list_plants.get(i).getPlantsName(), list_plants.get(i).getSCName(),
                                    list_plants.get(i).getFamily(), list_plants.get(i).getImg2()));
                        }
                    }

                    results.count = plants.size();
                    results.values = plants;
                }

                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list_plants_display = (ArrayList<MedicalPlants>) filterResults.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
