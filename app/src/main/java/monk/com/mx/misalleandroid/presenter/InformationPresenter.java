package monk.com.mx.misalleandroid.presenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import monk.com.mx.misalleandroid.model.InformationManager;
import monk.com.mx.misalleandroid.model.ScrapperRequest;
import monk.com.mx.misalleandroid.model.dataModels.Feedback;
import monk.com.mx.misalleandroid.model.dataModels.Usuario;
import monk.com.mx.misalleandroid.view.InformationFragment;

/**
 * Created by edago on 8/7/17.
 */

public class InformationPresenter {

    private InformationFragment fragment;
    private InformationManager informationManager;

    public InformationPresenter(InformationFragment fragment) {
        this.fragment = fragment;
        informationManager = new InformationManager();
    }

    public void sendFeedback(String texto) {
        if (texto == null || texto.equals("")){
            fragment.onEmptyFeedback();
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        int lastfeedback = informationManager.getLastFeedbackDate();

        if (lastfeedback != 0) {
            Date lastFeedbackDate = new Date(((long) lastfeedback) * 1000L);
            Date today = new Date(new Date().getTime());

            String lastFeedbackString = sdf.format(lastFeedbackDate);
            String todayString = sdf.format(today);

            try {
                Date lastCompare = sdf.parse(lastFeedbackString);
                Date todayCompare = sdf.parse(todayString);
                if (!todayCompare.after(lastCompare)) {
                    onRecentFeedback();
                    return;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Usuario usuario = informationManager.getUsuario();
        Feedback feedback = new Feedback(usuario.getMatricula(), texto);

        ScrapperRequest scrapperRequest = new ScrapperRequest();
        scrapperRequest.setFeedbackRequest(feedback, this);
    }

    private void onRecentFeedback() {
        fragment.OnRecentFeedback();
    }

    public void onSuccesfulRequest() {
        int date = (int) (new Date().getTime()/1000);

        informationManager.setLastFeedbackDate(date);
        fragment.onFeedbackSent();
    }

    public void onErrorRequest() {
        fragment.onErrorRequest();
    }
}
