package AFPA.CDA06.demo.Entities;

import java.util.Comparator;

/**
 * class use to sort ArrayList client and prospect
 * sorting by compagny name then by gross sale for clients
 * sorting by compagny name then by interest for prospects
 */
public class SortByName {

    //Sort Client's list by compagny's names, then gross sale if names are the same.
    public static class SortByNameThenGrossSale implements Comparator<Client> {
        @Override
        public int compare(Client o1, Client o2) {
            int i;  //prend la valeur -1 si o1<o2,
            // 0 si o1==o2
            //1 si o1>o2
            i = o1.getCompagnyName().compareTo(o2.getCompagnyName());
            if (i == 0) {
                i = Double.compare(o1.getGrossSales(), o2.getGrossSales());
            }
            return i;
        }
    }

    //Sort Prospect's list by compagny's names, then if they're intereseted if names are the same.
    public static class SortByNameThenInterested implements Comparator<Prospected> {
        @Override
        public int compare(Prospected o1, Prospected o2) {
            int i;  //prend la valeur -1 si o1<o2,
            // 0 si o1==o2
            //1 si o1>o2
            i = o1.getCompagnyName().compareTo(o2.getCompagnyName());
            if (i == 0) {
                i = o1.getProspectedInterested().compareTo(o2.getProspectedInterested());
            }
            return i;
        }
    }
}
