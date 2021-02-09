package AFPA.CDA06.demo.Entities;

import AFPA.CDA06.demo.Exception.ExceptionHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Class inherited from Society
 * set and get specific attribute of prospect prospectedInterested and prospectingDate
 * Create an attribute IDProspectedCount, allowing us to give a unique number to each prospect
 */
public class Prospected extends Society{

    private LocalDate prospectingDate;
    private String prospectedInterested;
    public static int IDProspectedCount = 0;

    public Prospected(int ID, String compagnyName, String streetNumber,
                      String streetName, String postalCode, String city,
                      String phoneNumber, String email, String comments,
                      LocalDate prospectingDate, String prospectedInterested)
            throws ExceptionHandler {

        super( ID, compagnyName, streetNumber,
                streetName, postalCode, city,
                phoneNumber, email, comments);
        this.setProspectingDate(prospectingDate);
        this.setProspectedInterested(prospectedInterested);
        this.setID(IDProspectedCount);
        IDProspectedCount++;

    }
    public void setProspectingDate(LocalDate prospectingDate) throws ExceptionHandler{


        if(prospectingDate == null){
            throw new ExceptionHandler("Date field is unfilled");
        }
        this.prospectingDate = prospectingDate;

    }
    public void setProspectedInterested(String prospectedInterested) {
        this.prospectedInterested = prospectedInterested;
    }
    public static int setIDProspectedCount(int IDProspectedCount){
        Prospected.IDProspectedCount = IDProspectedCount;
        return IDProspectedCount;
    }

    public LocalDate getProspectingDate() {
        return prospectingDate;
    }

    /**
     * getter to allow us to easily display if the prospected is interested or not
     * use in Delete, Display and Modify prospect.
     * @return a String yes | no.
     * It's actually the index of the selected item in CreateModifyDeleteFrame's comboBox
     */
    public String getProspectedInterested() {
        if(this.prospectedInterested.equals("0"))
            return "Yes";

        if(this.prospectedInterested.equals("1"))
            return "No";

        else
            return prospectedInterested;
    }
    public static int getIDProspectedCount(){
        return IDProspectedCount;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        LocalDate dateToBeChanged = this.prospectingDate;
        String dateOfProspection = dateFormatter.format(dateToBeChanged);
        return super.toString() + "\nj'ai été appelé le " +  dateOfProspection +
                "\net j'ai été intéréssé : " + this.prospectedInterested + "\n";
    }

}