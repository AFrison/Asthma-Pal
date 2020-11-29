package com.AP.Asthma_Pal;

public class JournalEntry {

    private String date;
    private String cough;
    private String wheeze;
    private String chest;
    private String sleep;
    private String excercise;
    private String meds;
    private String result;

    public JournalEntry(String date, String cough, String wheeze, String chest, String sleep, String excercise, String meds) {
        this.date = date;
        this.cough = cough;
        this.wheeze = wheeze;
        this.chest = chest;
        this.sleep = sleep;
        this.excercise = excercise;
        this.meds = meds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCough() {
        return cough;
    }

    public void setCough(String cough) {
        this.cough = cough;
    }

    public String getWheeze() {
        return wheeze;
    }

    public void setWheeze(String wheeze) {
        this.wheeze = wheeze;
    }

    public String getChest() {
        return chest;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public String getSleep() {
        return sleep;
    }

    public void setSleep(String sleep) {
        this.sleep = sleep;
    }

    public String getExcercise() {
        return excercise;
    }

    public void setExcercise(String excercise) {
        this.excercise = excercise;
    }

    public String getMeds() {
        return meds;
    }

    public void setMeds(String meds) {
        this.meds = meds;
    }

    public String print(){
        return result = date + " " +  cough + " " + wheeze + " " +chest + " " + sleep + " " + excercise + " " + meds;
    }
}
