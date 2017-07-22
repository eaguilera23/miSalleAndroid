package monk.com.mx.misalleandroid.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import monk.com.mx.misalleandroid.R;
import monk.com.mx.misalleandroid.model.dataModels.AlumnoInfo;
import monk.com.mx.misalleandroid.presenter.MainPresenter;
import monk.com.mx.misalleandroid.view.helpers.MainNavigationViewListener;

public class MainActivity extends AppCompatActivity {

    MainPresenter mainPresenter;
    ImageButton _btn_change_img;
    TextView _txv_enrollment_header, _txv_name_header, _txv_career_header;
    AlumnoInfo alumnoInfo;
    public CircleImageView _img_profile;

    public void setAlumnoInfo(AlumnoInfo info) {
        this.alumnoInfo = info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar _nav_bar = (Toolbar) findViewById(R.id.nav_bar);
        setSupportActionBar(_nav_bar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, _nav_bar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        MainNavigationViewListener navigationViewListener = new MainNavigationViewListener(this);

        navigationView.setNavigationItemSelectedListener(navigationViewListener);

        MenuItem item = navigationView.getMenu().findItem(R.id.nav_home);
        item.setChecked(true);

        navigationViewListener.onNavigationItemSelected(item);

        mainPresenter = new MainPresenter(this);

        //Populate profile
        View headerView = navigationView.getHeaderView(0);
        _img_profile = (CircleImageView)headerView.findViewById(R.id.img_profile);
        _btn_change_img = (ImageButton)headerView.findViewById(R.id.btn_change_img);
        _txv_enrollment_header = (TextView)headerView.findViewById(R.id.txv_enrollment_header);
        _txv_name_header = (TextView)headerView.findViewById(R.id.txv_name_header);
        _txv_career_header = (TextView)headerView.findViewById(R.id.txv_career_header);

        mainPresenter.setAlumnoInfo();
        mainPresenter.setProfilePicture();

        _txv_enrollment_header.setText(alumnoInfo.getMatricula());
        _txv_name_header.setText(mainPresenter.getCompleteName(alumnoInfo));
        _txv_career_header.setText(alumnoInfo.getPrograma().getNombre());

        _btn_change_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PictureMenuFragment pictureMenuFragment = new PictureMenuFragment();
                pictureMenuFragment.setMainActivity(MainActivity.this);
                FragmentManager fm = getSupportFragmentManager();
                pictureMenuFragment.show(fm, "pictureMenuFragment");
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            mainPresenter.getPhoto(requestCode, resultCode, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setProfilePicture(Bitmap picture) {
        _img_profile.setImageBitmap(picture);
    }

    public void Logout(){
        mainPresenter.Logout();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
