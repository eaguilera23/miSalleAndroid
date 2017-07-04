package monk.com.mx.misalleandroid.presenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import monk.com.mx.misalleandroid.model.InformationManager;
import monk.com.mx.misalleandroid.model.dataModels.Clase;
import monk.com.mx.misalleandroid.view.ScheduleFragment;

/**
 * Created by edago on 7/3/17.
 */
public class SchedulePresenter {

    ScheduleFragment scheduleFragment;
    InformationManager informationManager;
    ArrayList<Clase> schedule;
    int[] colors;

    public SchedulePresenter(ScheduleFragment fragment){
        this.scheduleFragment = fragment;
        informationManager = new InformationManager();
        setSchedule();
    }

    private void setSchedule(){
        schedule = informationManager.getSchedule();
    }

    public ArrayList<Clase> getScheduleForDay(int day){
        //Se
        ArrayList<Clase> scheduleForDay = new ArrayList<>();

        //Obtain classes of the day
        for (Clase clase: schedule) {
            if (clase.getDia() == day)
                scheduleForDay.add(clase);
        }

        //Sort by hour
        Collections.sort(scheduleForDay, new Comparator<Clase>() {
            @Override
            public int compare(Clase lhs, Clase rhs) {
                return lhs.getHora_inicio() - rhs.getHora_inicio();
            }
        });

        scheduleForDay = setScheduleForDayColors(scheduleForDay);
        return scheduleForDay;
    }

    public ArrayList<Clase> setScheduleForDayColors(ArrayList<Clase> scheduleForDay) {
        for (int i = 0; i < scheduleForDay.size(); i++)
            scheduleForDay.get(i).getMateria().setColor(colors[i]);

        return scheduleForDay;
    }

    public void setColors(int[] pColors){
        colors = pColors;
    }
}
