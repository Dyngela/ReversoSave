package AFPA.CDA06.demo.Entities;

import java.util.ArrayList;

public class ArrayListProspectDAO {

    /**
     * ArrayList to stock all the society name of prospects from the database for display purposes
     */
        public static ArrayList<Prospected> prospectedArrayList = new ArrayList<>();
        public static ArrayList<Prospected> getProspectListDAO() {
            return prospectedArrayList;
        }
}
