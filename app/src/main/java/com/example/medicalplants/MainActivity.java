package com.example.medicalplants;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.medicalplants.Database.DatabaseAssets;
import com.example.medicalplants.Fragment.MedicalPlantsFragment;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    DatabaseAssets mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize and open database
        mydatabase = new DatabaseAssets(getApplicationContext());
        try {
            mydatabase.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mydatabase.openDataBase();

        // Load MedicalPlantsFragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new MedicalPlantsFragment()).commit();
        }
    }
}
