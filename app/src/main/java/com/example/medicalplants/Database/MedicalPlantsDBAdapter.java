package com.example.medicalplants.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import com.example.medicalplants.Model.MedicalPlants;

public class MedicalPlantsDBAdapter extends DatabaseAssets {

    private static final String KEY_ID = "Id";
    private static final String KEY_PLANTSNAME = "PlantsName";
    private static final String KEY_SCNAME = "SCName";
    private static final String KEY_FAMILY = "family";
    private static final String KEY_DESCRIPTION = "Description";
    private static final String KEY_CHEMICALCOMPOUNDS = "ChemicalCompounds";
    private static final String KEY_HABITAT = "Habitat";
    private static final String KEY_AGRICULTURE = "Agriculture";
    private static final String KEY_SOILTYPE = "SoilType";
    private static final String KEY_WATERREQ = "WaterReq";
    private static final String KEY_KODENEEDS = "KodeNeeds";
    private static final String KEY_FLOWRING = "Flowring";
    private static final String KEY_IMG = "img";
    private static final String KEY_FAV = "fav";

    private static final String TABLE_ALL_MEDICALPLANTS = "MedicalPlants";
    private MedicalPlants medicalPlants;

    public MedicalPlantsDBAdapter(Context context) {
        super(context);
    }

    // Insert a new medical plant into the database
    public void insert(MedicalPlants medicalPlants) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, medicalPlants.getId());
        values.put(KEY_PLANTSNAME, medicalPlants.getPlantsName());
        values.put(KEY_SCNAME, medicalPlants.getSCName());
        values.put(KEY_FAMILY, medicalPlants.getFamily());
        values.put(KEY_DESCRIPTION, medicalPlants.getDescription());
        values.put(KEY_IMG, medicalPlants.getImg());
        values.put(KEY_FAV, medicalPlants.getFav());
        db.insert(TABLE_ALL_MEDICALPLANTS, null, values);
    }

    // Retrieve a list of all medical plants from the database
    public ArrayList<MedicalPlants> getMedicalPlants() {
        ArrayList<MedicalPlants> plantList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_ALL_MEDICALPLANTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID));
                String plantsName = cursor.getString(cursor.getColumnIndexOrThrow(KEY_PLANTSNAME));
                String scName = cursor.getString(cursor.getColumnIndexOrThrow(KEY_SCNAME));
                String family = cursor.getString(cursor.getColumnIndexOrThrow(KEY_FAMILY));
                byte[] img = cursor.getBlob(cursor.getColumnIndexOrThrow(KEY_IMG));
                MedicalPlants plant = new MedicalPlants(id, plantsName, scName, family, img);
                plantList.add(plant);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return plantList;
    }

    // Retrieve details of a medical plant by ID
    public MedicalPlants getMedicalPlansDetail(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {KEY_ID, KEY_PLANTSNAME, KEY_SCNAME, KEY_DESCRIPTION, KEY_IMG};
        Cursor cursor = db.query(TABLE_ALL_MEDICALPLANTS, columns, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int plantId = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID));
            String plantsName = cursor.getString(cursor.getColumnIndexOrThrow(KEY_PLANTSNAME));
            String scName = cursor.getString(cursor.getColumnIndexOrThrow(KEY_SCNAME));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(KEY_DESCRIPTION));
            byte[] img = cursor.getBlob(cursor.getColumnIndexOrThrow(KEY_IMG));

            medicalPlants = new MedicalPlants(plantId, plantsName, scName, description, img);
            cursor.close();
        }

        return medicalPlants;
    }

    // Retrieve extended information about a medical plant by ID
    public MedicalPlants getMedicalPlantsInformation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {KEY_ID, KEY_CHEMICALCOMPOUNDS, KEY_HABITAT, KEY_AGRICULTURE, KEY_SOILTYPE, KEY_WATERREQ, KEY_KODENEEDS, KEY_FLOWRING};
        Cursor cursor = db.query(TABLE_ALL_MEDICALPLANTS, columns, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int plantId = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID));
            String chemicalCompounds = cursor.getString(cursor.getColumnIndexOrThrow(KEY_CHEMICALCOMPOUNDS));
            String habitat = cursor.getString(cursor.getColumnIndexOrThrow(KEY_HABITAT));
            String agriculture = cursor.getString(cursor.getColumnIndexOrThrow(KEY_AGRICULTURE));
            String soilType = cursor.getString(cursor.getColumnIndexOrThrow(KEY_SOILTYPE));
            String waterReq = cursor.getString(cursor.getColumnIndexOrThrow(KEY_WATERREQ));
            String kodeNeeds = cursor.getString(cursor.getColumnIndexOrThrow(KEY_KODENEEDS));
            String flowring = cursor.getString(cursor.getColumnIndexOrThrow(KEY_FLOWRING));

            medicalPlants = new MedicalPlants(plantId, chemicalCompounds, habitat, agriculture, soilType, waterReq, kodeNeeds, flowring);
            cursor.close();
        }

        return medicalPlants;
    }

    // Check if a plant is marked as favorite
    public boolean isFavorite(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ALL_MEDICALPLANTS, new String[]{KEY_FAV}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int favStatus = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_FAV));
            cursor.close();
            return favStatus == 1;
        }

        return false;
    }
}
