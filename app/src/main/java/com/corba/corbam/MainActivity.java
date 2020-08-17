package com.corba.corbam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.corba.corbam.Entities.Kullanici;
import com.corba.corbam.Entities.Masa;
import com.corba.corbam.Services.APIClient;
import com.corba.corbam.Services.KullaniciService;
import com.corba.corbam.Services.MasaService;
import com.corba.corbam.Services.RestInterface;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    private Button btnclick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        new GetMasalar().execute();

        //new Login().execute();

        btnclick = (Button) findViewById(R.id.btnclick);

        btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtn1 = new Intent(MainActivity.this, GirisActivity.class);
                startActivity(intentbtn1);
            }
        });
    }

    /*public class GetMasalar extends android.os.AsyncTask<Void, Void, List<Masa>> {
        @Override
        protected List<Masa> doInBackground(Void... voids) {
            MasaService m = new MasaService();
            List<Masa> list = m.GetMasalar();
            return list;
        }

        @Override
        protected void onPostExecute(List<Masa> masas) {
            String line = "";
            for (Masa m : masas) {
                line += m.toString() + "\n";
            }
            Toast.makeText(MainActivity.this, line, Toast.LENGTH_LONG).show();
        }
    }*/

    /*public class Login extends android.os.AsyncTask<Void, Void, Kullanici> {
        @Override
        protected Kullanici doInBackground(Void... voids) {
            KullaniciService m = new KullaniciService();
            Kullanici list = m.Login("admin", "12345");
            return list;
        }

        @Override
        protected void onPostExecute(Kullanici kullanici) {
            if (kullanici == null) return;
            String line = kullanici.toString();
            Toast.makeText(MainActivity.this, line, Toast.LENGTH_LONG).show();
        }
    }*/
}
