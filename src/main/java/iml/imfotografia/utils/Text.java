package iml.imfotografia.utils;

/**
 * Created by imarquina on 3/7/16.
 */
public class Text {
    public static String SetLength(String text, Integer size) {
        if (text.isEmpty()) return "";
        else if (text.length() <= size) return text;
        else {
            return text.substring(0, size) + "...";
        }
    }
}
