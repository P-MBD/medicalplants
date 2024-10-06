package com.example.medicalplants.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.medicalplants.Class.JustifiedTextView;
import com.example.medicalplants.Database.MedicalPlantsDBAdapter;
import com.example.medicalplants.Model.MedicalPlants;
import com.example.medicalplants.R;
import com.example.medicalplants.Setting.Default_MedicalPlants;

public class DescriptionFragment extends Fragment {
    private JustifiedTextView lblTitle, txtTitle, txtDescription;  // اطمینان حاصل کنید که از JustifiedTextView استفاده می‌شود
    private ImageView imgView;
    private String idStation;
    private MedicalPlantsDBAdapter medicalPlantsDBAdapter;
    private MedicalPlants medicalPlants;

    public DescriptionFragment() {
        // Required empty public constructor
    }

    public static DescriptionFragment newInstance(String idStation) {
        DescriptionFragment fragment = new DescriptionFragment();
        Bundle args = new Bundle();
        args.putString("ID_STATION", idStation);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idStation = getArguments().getString("ID_STATION");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_description, container, false);

        // Initialize views with JustifiedTextView
        lblTitle = rootView.findViewById(R.id.lbl_title);
        txtTitle = rootView.findViewById(R.id.txt_title);  // اطمینان حاصل کنید که این هم از JustifiedTextView است
        txtDescription = rootView.findViewById(R.id.txt_description);  // اطمینان حاصل کنید که این هم از JustifiedTextView است
        imgView = rootView.findViewById(R.id.img_app);

        // Initialize database adapter and get plant details
        medicalPlantsDBAdapter = new MedicalPlantsDBAdapter(getActivity());
        medicalPlants = medicalPlantsDBAdapter.getMedicalPlansDetail(Integer.parseInt(idStation));

        // Populate UI with the retrieved data
        if (medicalPlants != null) {
            lblTitle.setText(medicalPlants.getPlantsName());
            txtTitle.setText(medicalPlants.getSCName());
            txtDescription.setText(medicalPlants.getDescription());

            byte[] img = medicalPlants.getImg();
            if (img != null && img.length > 0) {
                Bitmap bmp = BitmapFactory.decodeByteArray(img, 0, img.length);
                imgView.setImageBitmap(bmp);
            }
        }

        return rootView;
    }
}
