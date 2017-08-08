package monk.com.mx.misalleandroid.presenter;

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

        Usuario usuario = informationManager.getUsuario();
        Feedback feedback = new Feedback(usuario.getMatricula(), texto);

        ScrapperRequest scrapperRequest = new ScrapperRequest();
        scrapperRequest.setFeedbackRequest(feedback, this);
    }

    public void onSuccesfulRequest() {
        fragment.onFeedbackSent();
    }

    public void onErrorRequest() {
        fragment.onErrorRequest();
    }
}
