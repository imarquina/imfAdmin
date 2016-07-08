package iml.imfotografia.utils;

/**
 * Created by inaki.marquina on 01/07/2016.
 */
public class Number {
    /**
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
