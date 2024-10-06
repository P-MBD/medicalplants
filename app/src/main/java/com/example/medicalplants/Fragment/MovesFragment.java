package com.example.medicalplants.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.medicalplants.Adapter.ThreeLevelListAdapter;
import com.example.medicalplants.Database.MedicalPlantsDBAdapter;
import com.example.medicalplants.Model.MedicalPlants;
import com.example.medicalplants.R;
import com.example.medicalplants.Setting.Default_MedicalPlants;
import com.example.medicalplants.classPackage.Parent;
import com.example.medicalplants.classPackage.SecondLevel;
import com.example.medicalplants.classPackage.ThreeLevel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MovesFragment extends Fragment {
    private static final String ARG_ID_STATION = "ID_STATION";
    private String id_station;

    private MedicalPlantsDBAdapter medicalPlantsDBAdapter;
    private MedicalPlants medicalPlants;
    private ExpandableListView expandableListView;

    // Level 1
    private String[] parentText = new String[]{"زراعت", "عملیات نگهداری و مراقبت از گیاه", "زمان برداشت"};
    private int[] parentImg = new int[]{R.drawable.rosemari, R.drawable.zireseyah, R.drawable.ostokhodos};

    // Level 2
    private String[] q1 = new String[]{"زراعت", "بررسی رویشگاه"};
    private int[] qImg1 = new int[]{R.drawable.apple, R.drawable.badranjobe};

    private String[] q2 = new String[]{"نوع خاک", "نیاز آبی", "نیاز کودی"};
    private int[] qImg2 = new int[]{R.drawable.ostokhodos, R.drawable.rosemari, R.drawable.zireseyah};

    private String[] q3 = new String[]{"زمان گلدهی و برداشت محصول"};
    private int[] qImg3 = new int[]{R.drawable.rosemari};

    private LinkedHashMap<SecondLevel, ThreeLevel> thirdLevelq1 = new LinkedHashMap<>();
    private LinkedHashMap<SecondLevel, ThreeLevel> thirdLevelq2 = new LinkedHashMap<>();
    private LinkedHashMap<SecondLevel, ThreeLevel> thirdLevelq3 = new LinkedHashMap<>();

    private List<Parent> parentList = new ArrayList<>();
    private List<SecondLevel> secondLevelList1 = new ArrayList<>();
    private List<SecondLevel> secondLevelList2 = new ArrayList<>();
    private List<SecondLevel> secondLevelList3 = new ArrayList<>();
    private List<List<SecondLevel>> secondLevels = new ArrayList<>();
    private List<ThreeLevel> threeLevels = new ArrayList<>();
    private List<LinkedHashMap<SecondLevel, ThreeLevel>> data = new ArrayList<>();

    public MovesFragment() {
        // Required empty public constructor
    }

    // Factory method to create a new instance of this fragment
    public static MovesFragment newInstance(String id) {
        MovesFragment fragment = new MovesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ID_STATION, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id_station = getArguments().getString(ARG_ID_STATION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_moves, container, false);
        setupDatabase();
        setupExpandableListView(rootview);
        return rootview;
    }

    private void setupDatabase() {
        Default_MedicalPlants default_medicalPlants = new Default_MedicalPlants(getActivity());
        id_station = default_medicalPlants.get_id();
        medicalPlantsDBAdapter = new MedicalPlantsDBAdapter(getActivity());
        medicalPlants = medicalPlantsDBAdapter.getMedicalPlantsInformation(Integer.parseInt(id_station));
    }

    private void setupExpandableListView(View rootview) {
        expandableListView = rootview.findViewById(R.id.expandableListView);

        populateParentList();
        populateSecondLevelLists();
        populateThreeLevelList();

        setupData();

        // Here we pass both secondLevels and data
        ExpandableListAdapter expandableListAdapter = new ThreeLevelListAdapter(getContext(), parentList, secondLevels, data);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupExpandListener(groupPosition ->
                Toast.makeText(getActivity().getApplicationContext(),
                        parentList.get(groupPosition).getParentName() + " List Expanded.",
                        Toast.LENGTH_SHORT).show()
        );

        expandableListView.setOnGroupCollapseListener(groupPosition ->
                Toast.makeText(getActivity().getApplicationContext(),
                        parentList.get(groupPosition).getParentName() + " List Collapsed.",
                        Toast.LENGTH_SHORT).show()
        );

        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            Toast.makeText(getActivity().getApplicationContext(),
                    parentList.get(groupPosition).getParentName() + " -> " +
                            data.get(groupPosition).keySet().toArray()[childPosition],
                    Toast.LENGTH_SHORT).show();
            return false;
        });
    }

    private void populateParentList() {
        for (int i = 0; i < parentText.length; i++) {
            Parent parent = new Parent();
            parent.setParentName(parentText[i]);
            parent.setParentImg(parentImg[i]);
            parentList.add(parent);
        }
    }

    private void populateSecondLevelLists() {
        for (String name : q1) {
            SecondLevel secondLevel = new SecondLevel();
            secondLevel.setSecondLevelName(name);
            secondLevel.setSecondLevelImg(qImg1[0]);
            secondLevelList1.add(secondLevel);
        }
        secondLevels.add(secondLevelList1); // Add to secondLevels

        for (String name : q2) {
            SecondLevel secondLevel = new SecondLevel();
            secondLevel.setSecondLevelName(name);
            secondLevel.setSecondLevelImg(qImg2[0]);
            secondLevelList2.add(secondLevel);
        }
        secondLevels.add(secondLevelList2); // Add to secondLevels

        for (String name : q3) {
            SecondLevel secondLevel = new SecondLevel();
            secondLevel.setSecondLevelName(name);
            secondLevel.setSecondLevelImg(qImg3[0]);
            secondLevelList3.add(secondLevel);
        }
        secondLevels.add(secondLevelList3); // Add to secondLevels
    }

    private void populateThreeLevelList() {
        String txt_ChemicalCompound = medicalPlants.getChemicalCompounds();
        String txt_Habitat = medicalPlants.getHabitat();
        String txt_SoilType = medicalPlants.getSoilType();
        String txt_WaterReq = medicalPlants.getWaterReq();
        String txt_KodeNeeds = medicalPlants.getKodeNeeds();
        String txt_Flowring = medicalPlants.getFlowring();

        String[] descLevel3 = new String[]{
                txt_ChemicalCompound, txt_Habitat, txt_SoilType, txt_WaterReq, txt_KodeNeeds, txt_Flowring};

        for (String description : descLevel3) {
            ThreeLevel threeLevel = new ThreeLevel();
            threeLevel.setThreeLevelName(description);
            threeLevel.setThreeLevelImg(R.drawable.ic_launcher_foreground); // Use an appropriate image
            threeLevels.add(threeLevel);
        }
    }

    private void setupData() {
        thirdLevelq1.put(secondLevelList1.get(0), threeLevels.get(0));
        thirdLevelq1.put(secondLevelList1.get(1), threeLevels.get(1));
        thirdLevelq2.put(secondLevelList2.get(0), threeLevels.get(2));
        thirdLevelq2.put(secondLevelList2.get(1), threeLevels.get(3));
        thirdLevelq2.put(secondLevelList2.get(2), threeLevels.get(4));
        thirdLevelq3.put(secondLevelList3.get(0), threeLevels.get(5));

        data.add(thirdLevelq1);
        data.add(thirdLevelq2);
        data.add(thirdLevelq3);
    }
}
