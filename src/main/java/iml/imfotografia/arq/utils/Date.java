package iml.imfotografia.arq.utils;

/**
 * Created by inaki.marquina on 07/07/2016.
 */
public class Date extends java.util.Date {

    /**
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long DateDiff(java.util.Date startDate, java.util.Date endDate){
        long diff = endDate.getTime() - startDate.getTime();
        return diff/(1000*60*60*24);
    }
}
