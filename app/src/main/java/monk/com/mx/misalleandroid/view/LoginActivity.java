package monk.com.mx.misalleandroid.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import monk.com.mx.misalleandroid.R;
import monk.com.mx.misalleandroid.presenter.LoginPresenter;

/**
 * Created by edago on 7/2/17.
 */
public class LoginActivity extends AppCompatActivity {

    LoginPresenter loginPresenter;

    EditText txt_matricula, txt_password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginPresenter = new LoginPresenter(this);
        boolean session = loginPresenter.isSessionActive();
        if (session){
            navigateToMainActivity();
            return;
        }


        setContentView(R.layout.activity_login);
        loginPresenter = new LoginPresenter(this);

        txt_matricula = (EditText)findViewById(R.id.txt_matricula_login);
        txt_password = (EditText)findViewById(R.id.txt_password_login);

        String error = getIntent().getStringExtra("error");
        if (error != null)
            onFailedLogin(error);
    }

    private void onFailedLogin(String error) {
        Toast toast = Toast.makeText(this, error, Toast.LENGTH_LONG);
        toast.show();
    }

    public void onLoginClick(View v){
        loginPresenter.CheckFields(txt_matricula.getText().toString(), txt_password.getText().toString());
    }

    public void onEmptyFields(){
        Toast toast = Toast.makeText(this, getResources().getString(R.string.empty_credentials_error), Toast.LENGTH_SHORT);
        toast.show();
    }


    public void navigateToLoadingActivity() {
        Intent intent = new Intent(LoginActivity.this, LoadingActivity.class);
        intent.putExtra("matricula", txt_matricula.getText().toString());
        intent.putExtra("password", txt_password.getText().toString());
        startActivity(intent);
        finish();
    }

    public void navigateToMainActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
