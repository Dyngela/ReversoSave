package AFPA.CDA06.demo.Entities;

import java.util.ArrayList;

/**
 * ArrayList containing all our prospect
 */
public class ArrayListProspect {

    //ArrayList referencing all the Prospect's Object
    public static ArrayList<Prospected> prospectedList = new ArrayList<>();
    public static ArrayList<Prospected> getProspespectedList() {
        return prospectedList;
    }
}
