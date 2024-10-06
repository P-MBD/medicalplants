package com.example.medicalplants;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import com.example.medicalplants.Adapter.Pager;
import com.example.medicalplants.Database.MedicalPlantsDBAdapter;
import com.example.medicalplants.Fragment.DescriptionFragment;
import com.example.medicalplants.Fragment.MapFragments;
import com.example.medicalplants.Fragment.MovesFragment;
import com.example.medicalplants.Model.MedicalPlants;

public class InformationActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MedicalPlantsDBAdapter medicalPlantsDBAdapter;
    private MedicalPlants medicalPlants;
    private byte[] img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        // Initialize the database adapter
        medicalPlantsDBAdapter = new MedicalPlantsDBAdapter(this);

        // Retrieve the ID from the Intent
        String idStation = getIntent().getStringExtra("ID_STATION");

        // Check if the ID is missing
        if (idStation == null || idStation.isEmpty()) {
            Toast.makeText(this, "Error: Station ID is missing", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity
            return; // Exit the method
        }

        // Retrieve plant details from the database
        medicalPlants = medicalPlantsDBAdapter.getMedicalPlansDetail(Integer.parseInt(idStation));

        // Set up the collapsing toolbar with the plant name
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsingToolbarLayout);
        if (medicalPlants != null) {
            collapsingToolbar.setTitle(medicalPlants.getPlantsName());
        }

        // Set up the image
        ImageView imageView = findViewById(R.id.backdrop);
        if (medicalPlants != null && medicalPlants.getImg() != null) {
            img = medicalPlants.getImg();
            Bitmap bmp = BitmapFactory.decodeByteArray(img, 0, img.length);
            Glide.with(this).load(bmp).apply(RequestOptions.centerCropTransform()).into(imageView);
        } else {
            imageView.setImageResource(R.drawable.ic_launcher_background); // Default image if no image found
        }

        // Set up ViewPager and TabLayout
        viewPager = findViewById(R.id.pager);
        setupViewPager(viewPager);
        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        Pager adapter = new Pager(getSupportFragmentManager());
        adapter.addFragment(DescriptionFragment.newInstance(getIntent().getStringExtra("ID_STATION")), "گیاه شناسی");
        adapter.addFragment(MapFragments.newInstance(getIntent().getStringExtra("ID_STATION")), "جزییات بیشتر");
        adapter.addFragment(MovesFragment.newInstance(getIntent().getStringExtra("ID_STATION")), "خواص و کاربردها");
           viewPager.setAdapter(adapter);
    }
}
