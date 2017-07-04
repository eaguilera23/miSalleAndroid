package monk.com.mx.misalleandroid.domain;

/**
 * Created by edago on 7/2/17.
 */
public class StringFormater {
    public static String ToHour(int initial_hour, int final_hour){
        return String.format("%02d:00 - %02d:00", initial_hour, final_hour);
    }

    public static String ToPeriod(int initial_month, int final_month, String[]month, int year){
        if(final_month == 0)
            return String.format("%s %s", month[initial_month -1], year);
        else
            return String.format("%s - %s %s", month[initial_month -1], month[final_month -1], year);
    }

    public static String GetCompleteName(String nombre, String apP, String apM){
        return String.format("%s %s %s", nombre, apP, apM);
    }
}
