package AFPA.CDA06.demo.Utilities;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utilities {

    /**
     * Get a Type localdate and convert it into a date
     * @param localDate date of type LocalDate to convert
     * @return LocalDate converted in Date
     */
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Get a Type date and convert it into a localdate
     * @param date date of type Date to convert
     * @return date converted in LocalDate
     */
    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
