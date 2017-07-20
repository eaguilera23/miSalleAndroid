package monk.com.mx.misalleandroid.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

import monk.com.mx.misalleandroid.model.dataModels.Clase;
import monk.com.mx.misalleandroid.model.dataModels.Pago;

/**
 * Created by edago on 7/2/17.
 */
public class DataHelper {
    public static Clase getNextClass(ArrayList<Clase> schedule) {
        Calendar calendar = Calendar.getInstance();
        Clase next_class = null;

        //Get day, hour and classes
        int day = calendar.get(Calendar.DAY_OF_WEEK), hour = calendar.get(Calendar.HOUR_OF_DAY);
        ArrayList<Clase> clases_del_dia;
        //In case of weekend
        if (day == Calendar.SATURDAY || day == Calendar.SUNDAY){
            next_class = GetfirstClass(schedule, Calendar.MONDAY);
        }
        else {
            //Order classes
            clases_del_dia = OrderByHour(schedule, day);
            //Before first class
            if (hour == 0 || hour < clases_del_dia.get(0).getHora_inicio())
                next_class = clases_del_dia.get(0);
            else if (hour >= clases_del_dia.get(clases_del_dia.size() -1).getHora_inicio()){
                //Get next class
                if (day == Calendar.FRIDAY)
                    next_class = GetfirstClass(schedule, Calendar.MONDAY);
                else
                    next_class = GetfirstClass(schedule, day+1);
            }
            else if (hour >= clases_del_dia.get(0).getHora_inicio()
                    && hour < clases_del_dia.get(clases_del_dia.size() -1).getHora_inicio()){
                for (int i = 0; i < clases_del_dia.size(); i++){
                    if (i < clases_del_dia.size()-1)
                        if (hour >= clases_del_dia.get(i).getHora_inicio() && hour < clases_del_dia.get(i+1).getHora_inicio())
                            next_class = clases_del_dia.get(i+1);
                }
            }
        }

        return next_class;
    }

    private static Clase GetfirstClass(ArrayList<Clase> clases, int day){
        ArrayList<Clase> clases_del_dia = OrderByHour(clases, day);
        return clases_del_dia.get(0);
    }

    public static ArrayList<Clase> OrderByHour(ArrayList<Clase> clases, int day){
        //Se
        ArrayList<Clase> clases_del_dia = new ArrayList<>();

        //Obtain classes of the day
        for (Clase clase:clases) {
            if (clase.getDia() == day-1)
                clases_del_dia.add(clase);
        }

        //Sort by hour
        Collections.sort(clases_del_dia, new Comparator<Clase>() {
            @Override
            public int compare(Clase lhs, Clase rhs) {
                return lhs.getHora_inicio() - rhs.getHora_inicio();
            }
        });
        return clases_del_dia;
    }

    public static Pago getNextPayment(ArrayList<Pago> payments, Date today) {
        Pago nextPayment = new Pago();
        for (Pago pago: payments){
            if (!pago.getFecha().before(today)){
                nextPayment = pago;
                break;
            }
        }
        return  nextPayment;
    }

    public static String getMonthFromDate(Pago payment) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(payment.getFecha());
        int monthNumber = cal.get(Calendar.MONTH);
        switch (monthNumber){
            case 0:
                return "Enero";
            case 1:
                return "Febrero";
            case 2:
                return "Marzo";
            case 3:
                return "Abril";
            case 4:
                return "Mayo";
            case 5:
                return "Junio";
            case 6:
                return "Julio";
            case 7:
                return "Agosto";
            case 8:
                return "Septiembre";
            case 9:
                return "Octubre";
            case 10:
                return "Noviembre";
            case 11:
                return "Diciembre";
            default:
                return "--";
        }
    }

    public static int getDaysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
