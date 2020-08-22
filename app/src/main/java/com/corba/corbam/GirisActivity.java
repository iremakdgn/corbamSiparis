package com.corba.corbam;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.corba.corbam.Entities.Kullanici;
import com.corba.corbam.Services.KullaniciService;

public class GirisActivity extends AppCompatActivity {

    private Toolbar actionbar_Login;
    private Button btngarsongiris;
    private EditText mail, sifre;
    ProgressDialog progressDialog;

    public void init() {
        actionbar_Login = findViewById(R.id.actionbar_Login);
        setSupportActionBar(actionbar_Login);
        getSupportActionBar().setTitle("Giriş Yap");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            AlertDialog builder = new AlertDialog.Builder(GirisActivity.this).create();
            builder.setMessage("Çıkmak İstediğinizden Emin Misiniz?");
            builder.setButton(AlertDialog.BUTTON_NEGATIVE, "VAZGEÇ", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            builder.setButton(AlertDialog.BUTTON_POSITIVE, "EVET", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intentbtn = new Intent(GirisActivity.this, MainActivity.class);
                    startActivity(intentbtn);
                }
            });
            builder.show();
            builder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.aktif));
            builder.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.pasif));
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        init();

        btngarsongiris = findViewById(R.id.btnGarsonlogin);
        actionbar_Login = findViewById(R.id.actionbar_Login);
        mail = findViewById(R.id.txtEmailLogin);
        sifre = findViewById(R.id.txtPasswordLogin);
        progressDialog = new ProgressDialog(this);
        btngarsongiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Login(mail.getText().toString(), sifre.getText().toString(), "garson").execute();
            }
        });
    }

    public class Login extends android.os.AsyncTask<String, Void, Kullanici> {
        String kullaniciAdi, sifre, rol;

        public Login(String kullaniciAdi, String sifre, String rol) {
            this.kullaniciAdi = kullaniciAdi;
            this.sifre = sifre;
            this.rol = rol;
        }

        @Override
        protected Kullanici doInBackground(String... voids) {
            KullaniciService m = new KullaniciService();
            Kullanici list = m.Login(kullaniciAdi, sifre, rol);
            return list;
        }

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Yükleniyor...");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Kullanici kullanici) {
            progressDialog.dismiss();
            if (kullanici == null) {
                Toast.makeText(GirisActivity.this, "Hatalı kullanıcı adı şifre", Toast.LENGTH_LONG).show();
                return;
            }
            Intent intent = new Intent(GirisActivity.this, MasaSecimiActivity.class);
            startActivity(intent);
            super.onPostExecute(kullanici);
        }
    }
    public void hideKeyboard(View view) {
        InputMethodManager in = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
