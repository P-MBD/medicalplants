package com.example.medicalplants.Model;

public class MedicalPlants {

    int Id;
    String PlantsName;
    String SCName;
    String family;

    String Description;
    String ChemicalCompounds;
    String Habitat;



    String Agriculture;
    String SoilType;
    String WaterReq;
    String KodeNeeds;
    String Disease;
    String Flowring;
    String Properties;


    String img2;
    byte[] img;

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    int fav;
    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public  MedicalPlants()
    {

    }


    public  MedicalPlants(int Id,String PlantsName,String SCName , String family, String img2)
    {
         this.Id=Id;
         this.PlantsName=PlantsName;
         this.SCName= SCName;
         this.family= family;
         this.img2=img2;
    }

    public  MedicalPlants(int Id,String PlantsName, String SCName, String Description, byte[] img)
    {
        this.Id=Id;
        this.PlantsName=PlantsName;
        this.SCName=SCName;
        this.Description=Description;
        this.img=img;
    }
    public  MedicalPlants(int Id,String ChemicalCompounds, String Habitat, String Agriculture, String SoilType,String WaterReq, String KodeNeeds, String Flowring )
    {
        this.Id=Id;
        this.ChemicalCompounds=ChemicalCompounds;
        this.Habitat= Habitat;
        this.Agriculture= Agriculture;
        this.SoilType= SoilType;
        this.WaterReq= WaterReq;
        this.KodeNeeds= KodeNeeds;
        this.Flowring=Flowring;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPlantsName() {
        return PlantsName;
    }

    public void setPlantsName(String plantsName) {
        PlantsName = plantsName;
    }

    public String getSCName() {
        return SCName;
    }

    public void setSCName(String SCName) {
        this.SCName = SCName;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getChemicalCompounds() {
        return ChemicalCompounds;
    }

    public void setChemicalCompounds(String chemicalCompounds) {
        ChemicalCompounds = chemicalCompounds;
    }

    public String getHabitat() {
        return Habitat;
    }

    public void setHabitat(String habitat) {
        Habitat = habitat;
    }

    public String getAgriculture() {
        return Agriculture;
    }

    public void setAgriculture(String agriculture) {
        Agriculture = agriculture;
    }

    public String getSoilType() {
        return SoilType;
    }

    public void setSoilType(String soilType) {
        SoilType = soilType;
    }

    public String getWaterReq() {
        return WaterReq;
    }

    public void setWaterReq(String waterReq) {
        WaterReq = waterReq;
    }

    public String getKodeNeeds() {
        return KodeNeeds;
    }

    public void setKodeNeeds(String kodeNeeds) {
        KodeNeeds = kodeNeeds;
    }

    public String getDisease() {
        return Disease;
    }

    public void setDisease(String disease) {
        Disease = disease;
    }

    public String getFlowring() {
        return Flowring;
    }

    public void setFlowring(String flowring) {
        Flowring = flowring;
    }

    public String getProperties() {
        return Properties;
    }

    public void setProperties(String properties) {
        Properties = properties;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }




}
