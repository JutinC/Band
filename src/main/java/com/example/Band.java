/*
Justin Choi
CSP Period 6
Schenk
11 April 2024
Band peoople
 */
package com.example.band;


//Band class
public class Band {
    //Setting the variables
    public int id = -1;
    public String firstName = "";
    public String lastName = "";
    //THIS IS AN ENUM
    public String grade = "";
    public String instrument = "";
    //THIS IS AN ENUM
    public String band = "";
    public int stateChair = -1;
    public int districtChair = -1;
    public boolean marchingBand = false;
    public boolean jazzBand = false;
    public boolean fullOrchestra = false;

    //Gets the id
    public int getId() {
        return id;
    }
    //Sets id
    public void setId(int id) {
        this.id = id;
    }
    //Gets firstname
    public String getFirstName() {
        return firstName;
    }
    //Sets firstname
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    //getsLastname
    public String getLastName() {
        return lastName;
    }
    //Setslastname
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    //Getter
    public String getGrade() {
        return grade;
    }
    //Setter
    public void setGrade(String grade) {
        this.grade = grade;
    }
    //Get
    public String getInstrument() {
        return instrument;
    }
    //Set
    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }
    //Get
    public String getBand() {
        return band;
    }
    //Set
    public void setBand(String band) {
        this.band = band;
    }
    //Get
    public int getStateChair() {
        return stateChair;
    }
    //SEt
    public void setStateChair(int stateChair) {
        this.stateChair = stateChair;
    }
    //Get
    public int getDistrictChair() {
        return districtChair;
    }
    //SEt
    public void setDistrictChair(int districtChair) {
        this.districtChair = districtChair;
    }
    //Get
    public boolean getMarchingBand() {
        return marchingBand;
    }
    //SEt
    public void setMarchingBand(boolean marchingBand) {
        this.marchingBand = marchingBand;
    }
    //Get
    public boolean getJazzBand() {
        return jazzBand;
    }
    //SEt
    public void setJazzBand(boolean jazzBand) {
        this.jazzBand = jazzBand;
    }
    //Get
    public boolean getFullOrchestra() {
        return fullOrchestra;
    }
    //SEt
    public void setFullOrchestra(boolean fullOrchestra) {
        this.fullOrchestra = fullOrchestra;
    }

    //Class constructor for Band
    public Band(int id, String firstName, String lastName, String grade, String instrument, String band, int stateChair, int districtChair, boolean marchingBand, boolean jazzBand, boolean fullOrchestra) {
        //uses the variables to enter them in
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.instrument = instrument;
        this.band = band;
        this.stateChair = stateChair;
        this.districtChair = districtChair;
        this.marchingBand = marchingBand;
        this.jazzBand = jazzBand;
        this.fullOrchestra = fullOrchestra;
    }
}


