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
        schedule = informationManager.getSchedule();
    }

    public ArrayList<Clase> getScheduleForDay(int day){
        ArrayList<Clase> scheduleForDay = informationManager.getScheduleForDay(day, schedule);

        return setScheduleForDayColors(scheduleForDay);
    }

    public ArrayList<Clase> setScheduleForDayColors(ArrayList<Clase> scheduleForDay) {
        for (int i = 0, j = i; i < scheduleForDay.size(); i++, j++) {
            if (j >= colors.length)
                j = j - i;
            scheduleForDay.get(i).getMateria().setColor(colors[j]);
        }

        return scheduleForDay;
    }

    public void setColors(int[] pColors){
        colors = pColors;
    }
}
