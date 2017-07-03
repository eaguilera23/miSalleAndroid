package monk.com.mx.misalleandroid.domain;

/**
 * Created by edago on 7/2/17.
 */
public class StringFormater {
    public static String ToHour(int initial_hour, int final_hour){
        return String.format("%02d:00 - %02d:00", initial_hour, final_hour);
    }
}
