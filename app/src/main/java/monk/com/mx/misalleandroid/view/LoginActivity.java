package monk.com.mx.misalleandroid.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import monk.com.mx.misalleandroid.MyApplication;
import monk.com.mx.misalleandroid.R;
import monk.com.mx.misalleandroid.model.dataModels.SistemaObject;
import monk.com.mx.misalleandroid.presenter.LoginPresenter;
import monk.com.mx.misalleandroid.view.helpers.LoginSpinnerAdapter;

/**
 * Created by edago on 7/2/17.
 */
public class LoginActivity extends AppCompatActivity {

    LoginPresenter loginPresenter;

    EditText txt_matricula, txt_password;
    TextView txv_privacidad;
    Spinner spinner_campus, spinner_sistema;

    @Override
    protected void onStart() {
        super.onStart();

        loginPresenter = new LoginPresenter(this);
        loginPresenter.CheckSession();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        loginPresenter = new LoginPresenter(this);

        txt_matricula = (EditText)findViewById(R.id.txt_matricula_login);
        txt_password = (EditText)findViewById(R.id.txt_password_login);
        spinner_campus = (Spinner)findViewById(R.id.spinner_campus_login);
        spinner_sistema = (Spinner)findViewById(R.id.spinner_sistema_login);

        txv_privacidad = (TextView)findViewById(R.id.txv_privacidad_login);
        txv_privacidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.misalle.com.mx/privacidad.pdf"));
                startActivity(browserIntent);
            }
        });

        inflateSpinnerCampus();

        String error = getIntent().getStringExtra("error");
        if (error != null)
            onFailedLogin(error);
    }

    private void inflateSpinnerCampus() {
        SistemaObject[] campus = new SistemaObject[4];
        campus[0] = new SistemaObject(0, "Lomas del campestre");
        campus[1] = new SistemaObject(1, "Juan Alonso de Torres");
        campus[2] = new SistemaObject(2, "Américas");
        campus[3] = new SistemaObject(3, "Salamanca");

        final LoginSpinnerAdapter spinnerAdapter = new LoginSpinnerAdapter(LoginActivity.this, R.layout.spinner_item_login, campus);
        spinner_campus.setAdapter(spinnerAdapter);
        spinner_campus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                SistemaObject seleccionado = spinnerAdapter.getItem(position);
                inflateSpinnerSistema(seleccionado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void inflateSpinnerSistema(SistemaObject seleccionado) {
        SistemaObject[] sistemas;
        switch (seleccionado.getSistema()){
            case 0:
                sistemas = new SistemaObject[1];
                sistemas[0] = new SistemaObject(1, "Licenciatura");
                break;
            case 1:
                sistemas = new SistemaObject[1];
                sistemas[0] = new SistemaObject(43, "Preparatoria");
                break;
            case 2:
                sistemas = new SistemaObject[1];
                sistemas[0] = new SistemaObject(33, "Preparatoria");
                break;
            case 3:
                sistemas = new SistemaObject[2];
                sistemas[0] = new SistemaObject(21, "Licenciatura 1");
                sistemas[1] = new SistemaObject(20, "Licenciatura 2");
                break;
            default:
                sistemas = new SistemaObject[0];
                break;
        }

        LoginSpinnerAdapter spinnerAdapter = new LoginSpinnerAdapter(LoginActivity.this, R.layout.spinner_item_login, sistemas);
        spinner_sistema.setAdapter(spinnerAdapter);
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
        SistemaObject seleccionado = (SistemaObject)spinner_sistema.getSelectedItem();
        Intent intent = new Intent(LoginActivity.this, LoadingActivity.class);
        intent.putExtra("matricula", txt_matricula.getText().toString());
        intent.putExtra("password", txt_password.getText().toString());
        intent.putExtra("sistema", seleccionado.getSistema());
        startActivity(intent);
        finish();
    }

    public void navigateToMainActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void NoNetworkAvailable() {
        Toast toast = Toast.makeText(this, "Necesitas una conexión a internet para el primer ingreso.", Toast.LENGTH_SHORT);
        toast.show();
    }
}
