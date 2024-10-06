package com.example.medicalplants.Fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.medicalplants.Class.JustifiedTextView;
import com.example.medicalplants.Database.MedicalPlantsDBAdapter;
import com.example.medicalplants.Model.MedicalPlants;
import com.example.medicalplants.R;
import com.example.medicalplants.Setting.Default_MedicalPlants;

public class MapFragments extends Fragment {

    private JustifiedTextView txt_ChemicalCompound, txt_Habitat, txt_SoilType, txt_KodeNeeds, txt_Flowring, txt_Agriculture, txt_WaterReq;
    private Default_MedicalPlants default_medicalPlants;
    private String id_station;
    private MedicalPlantsDBAdapter medicalPlantsDBAdapter;
    private MedicalPlants medicalPlants;

    public MapFragments() {
        // Required empty public constructor
    }
    // Implementing the newInstance method
    public static MapFragments newInstance(String id) {
        MapFragments fragment = new MapFragments();
        Bundle args = new Bundle();
        args.putString("id_station", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id_station = getArguments().getString("id_station");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_map_fragments, container, false);
        txt_ChemicalCompound = rootview.findViewById(R.id.txt_ChemicalCompound);
        txt_Habitat = rootview.findViewById(R.id.txt_Habitat);
        txt_Agriculture = rootview.findViewById(R.id.txt_Agriculture);
        txt_SoilType = rootview.findViewById(R.id.txt_SoilType);
        txt_WaterReq = rootview.findViewById(R.id.txt_WaterReq);
        txt_KodeNeeds = rootview.findViewById(R.id.txt_KodeNeeds);
        txt_Flowring = rootview.findViewById(R.id.txt_Flowring);

        // Get the station ID from Default_MedicalPlants
        default_medicalPlants = new Default_MedicalPlants(getActivity());
        id_station = default_medicalPlants.get_id();

        // Initialize database adapter and retrieve medical plant information
        medicalPlantsDBAdapter = new MedicalPlantsDBAdapter(getActivity());
        medicalPlants = medicalPlantsDBAdapter.getMedicalPlantsInformation(Integer.parseInt(id_station));

        if (medicalPlants != null) {
            Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/iranian-sans-light.ttf");

            txt_ChemicalCompound.setText(medicalPlants.getChemicalCompounds());
            txt_ChemicalCompound.setLineSpacing(15f, 1f);  // تغییر برای استفاده صحیح از setLineSpacing
            txt_ChemicalCompound.setTypeface(typeface);

            txt_Habitat.setText(medicalPlants.getHabitat());
            txt_Habitat.setLineSpacing(15f, 1f);
            txt_Habitat.setTypeface(typeface);

            txt_Agriculture.setText(medicalPlants.getAgriculture());
            txt_Agriculture.setLineSpacing(15f, 1f);
            txt_Agriculture.setTypeface(typeface);

            txt_SoilType.setText(medicalPlants.getSoilType());
            txt_SoilType.setLineSpacing(15f, 1f);
            txt_SoilType.setTypeface(typeface);

            txt_WaterReq.setText(medicalPlants.getWaterReq());
            txt_WaterReq.setLineSpacing(15f, 1f);
            txt_WaterReq.setTypeface(typeface);

            txt_KodeNeeds.setText(medicalPlants.getKodeNeeds());
            txt_KodeNeeds.setLineSpacing(15f, 1f);
            txt_KodeNeeds.setTypeface(typeface);

            txt_Flowring.setText(medicalPlants.getFlowring());
            txt_Flowring.setLineSpacing(15f, 1f);
            txt_Flowring.setTypeface(typeface);
        }

        return rootview;
    }
}
