package monk.com.mx.misalleandroid.presenter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import monk.com.mx.misalleandroid.domain.DataHelper;
import monk.com.mx.misalleandroid.model.InformationManager;
import monk.com.mx.misalleandroid.model.dataModels.Clase;
import monk.com.mx.misalleandroid.model.dataModels.Credito;
import monk.com.mx.misalleandroid.model.dataModels.Pago;
import monk.com.mx.misalleandroid.view.HomeFragment;

/**
 * Created by edago on 7/2/17.
 */
public class HomePresenter {
    private HomeFragment homeFragment;
    private InformationManager informationManager;

    public HomePresenter(HomeFragment homefrag){
        this.homeFragment = homefrag;
        informationManager = new InformationManager();
        setNextClass();
        setPagos();
        UpdateCreditos();
    }

    private void UpdateCreditos() {
        informationManager.UpdateCreditos(this);
    }

    private void setPagos() {
        Date today = new Date();
        Pago payment = DataHelper.getNextPayment(informationManager.getPayments(), today);
        String month = DataHelper.getMonthFromDate(payment);
        int daysInBetween = DataHelper.getDaysBetween(today, payment.getFecha());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        homeFragment.setNextPayment(dateFormat.format(payment.getFecha()), month, daysInBetween);
    }

    private void setNextClass(){
        Clase clase = DataHelper.getNextClass(informationManager.getSchedule());
        homeFragment.setNextClass(clase);
    }

    public void setCreditos() {
        ArrayList<Credito> creditos = informationManager.getCreditos();
        homeFragment.setCreditos(creditos);
    }
}
