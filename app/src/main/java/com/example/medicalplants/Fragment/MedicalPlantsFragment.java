package com.example.medicalplants.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toolbar;
import java.io.Reader;
import java.util.ArrayList;

import com.example.medicalplants.Adapter.MedicalPlantsListAdapter;
import com.example.medicalplants.Class.UIHelper;
import com.example.medicalplants.Database.MedicalPlantsDBAdapter;
import com.example.medicalplants.InformationActivity;
import com.example.medicalplants.Model.MedicalPlants;
import com.example.medicalplants.R;
import com.example.medicalplants.Setting.Default_MedicalPlants;

/**
 * A simple {@link Fragment} subclass.
 */
public class MedicalPlantsFragment extends Fragment {

    ListView lst_data;
    MedicalPlantsDBAdapter medicalPlantsDBAdapter;
    Default_MedicalPlants default_medicalPlants;
    ArrayList<MedicalPlants> mpArrayList;
    MedicalPlantsListAdapter mpListAdapter;

    private AppCompatEditText edt_search;
    private AppCompatImageView icon_search;
    private AppCompatImageView close_search;
    private RelativeLayout lay_search;

    public MedicalPlantsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_medical_plants, container, false);
        lst_data = RootView.findViewById(R.id.lst_data);

        edt_search = RootView.findViewById(R.id.edt_search);
        icon_search = RootView.findViewById(R.id.icon_search);
        close_search = RootView.findViewById(R.id.close_search);
        lay_search = RootView.findViewById(R.id.lay_search);

        medicalPlantsDBAdapter = new MedicalPlantsDBAdapter(getActivity());
        mpArrayList = medicalPlantsDBAdapter.getMedicalPlants();

        mpListAdapter = new MedicalPlantsListAdapter(getActivity(), R.layout.medicalplants_row, mpArrayList);


        lst_data.setAdapter(mpListAdapter);
        lst_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String txt_id = ((TextView) view.findViewById(R.id.txt_id)).getText().toString();
                default_medicalPlants = new Default_MedicalPlants(getActivity());
                default_medicalPlants.saveID(txt_id);


                Intent intent = new Intent(getActivity(), InformationActivity.class);
                startActivity(intent);
            }
        });

        icon_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lay_search.setVisibility(View.VISIBLE);
                edt_search.requestFocus();
                UIHelper.showKeyboard(getContext());

            }
        });

        close_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edt_search.setText("");
                UIHelper.hideKeyboardFrom(getContext(),close_search);
                lay_search.setVisibility(View.GONE);
            }
        });

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                mpListAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        return RootView;
    }



}
