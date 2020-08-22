package com.corba.corbam;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.corba.corbam.Entities.Masa;
import com.corba.corbam.Services.MasaService;

import org.w3c.dom.Text;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class MasaSecimiActivity extends AppCompatActivity {
    private androidx.appcompat.widget.Toolbar actionbarMasa;
    public Button btn1 = null;
    public Button btn2;
    public Button btn3;
    public Button btn4;
    public Button btn7;
    public Button btn10;
    public Button btn5;
    public Button btn6;
    public Button btn8;
    public Button btn9;
    public Button btn11;
    public Button btn12;
    public Button btn13, btn14;
    public Button btn15, btn16;
    public Button btn17, btn18;
    public Button btn19, btn20;
    public ImageButton cksbtn;
    public static String gndrmasano;
    ConnectionClass connectionClass;

    public void init() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn10 = (Button) findViewById(R.id.btn10);
        btn11 = (Button) findViewById(R.id.btn11);
        btn12 = (Button) findViewById(R.id.btn12);
        btn13 = (Button) findViewById(R.id.btn13);
        btn14 = (Button) findViewById(R.id.btn14);
        btn15 = (Button) findViewById(R.id.btn15);
        btn16 = (Button) findViewById(R.id.btn16);
        btn17 = (Button) findViewById(R.id.btn17);
        btn18 = (Button) findViewById(R.id.btn18);
        btn19 = (Button) findViewById(R.id.btn19);
        btn20 = (Button) findViewById(R.id.btn20);
        cksbtn = (ImageButton) findViewById(R.id.cksbtn);
        //renklendir doregister = new renklendir();
        //doregister.execute("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masa_secimi);

        init();
        new GetMasalar().execute();

        cksbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog builder = new AlertDialog.Builder(MasaSecimiActivity.this).create();
                builder.setTitle("! ÇIKIŞ");
                builder.setMessage("Çıkış yapılacaktır .Devam Edilsin mi ?");

                builder.setButton(AlertDialog.BUTTON_NEGATIVE, "VAZGEÇ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //İptal butonuna basılınca yapılacaklar.Sadece kapanması isteniyorsa boş bırakılacak

                    }
                });
                builder.setButton(AlertDialog.BUTTON_POSITIVE, "EVET", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intentbtn11 = new Intent(MasaSecimiActivity.this, GirisActivity.class);
                        startActivity(intentbtn11);

                    }
                });
                builder.show();
                builder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.aktif));
                builder.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.pasif));

            }
        });

        //region ButtonClickler
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn1.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn1.getText().toString();
                    Intent intentbtn1 = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn1);
                }

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn2.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn2.getText().toString();
                    Intent intentbtn2 = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn2);
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if (!btn3.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn3.getText().toString();
                    Intent intentbtn3 = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn3);
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn4.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn4.getText().toString();
                    Intent intentbtn4 = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn4);
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn5.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn5.getText().toString();
                    Intent intentbtn5 = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn5);
                }
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn6.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn6.getText().toString();
                    Intent intentbtn6 = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn6);
                }
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn7.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn7.getText().toString();
                    Intent intentbtn7 = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn7);
                }
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn8.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn8.getText().toString();
                    Intent intentbtn8 = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn8);
                }
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn9.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn9.getText().toString();
                    Intent intentbtn9 = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn9);

                }
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn10.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn10.getText().toString();
                    Intent intentbtn10 = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn10);
                }
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn11.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn11.getText().toString();
                    Intent intentbtn11 = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn11);
                }
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn12.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn12.getText().toString();
                    Intent intentbtn12 = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn12);
                }
            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn13.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn13.getText().toString();
                    Intent intentbtn13 = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn13);
                }
            }
        });
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn14.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn14.getText().toString();
                    Intent intentbtn14 = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn14);
                }
            }
        });
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn15.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn15.getText().toString();
                    Intent intentbtn15 = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn15);
                }
            }
        });
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn16.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn16.getText().toString();
                    Intent intentbtn16 = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn16);
                }
            }
        });
        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn17.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn17.getText().toString();
                    Intent intentbtn17 = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn17);

                }
            }
        });
        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn18.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn18.getText().toString();
                    Intent intentbtn18 = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn18);
                }
            }
        });
        btn19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn19.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn19.getText().toString();
                    Intent intentbtn19 = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn19);

                }
            }
        });
        btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn20.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn20.getText().toString();
                    Intent intentbtn20 = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn20);
                }
            }
        });
        //endregion

        String b1text = (btn1.getText().toString());
    }


    public class GetMasalar extends android.os.AsyncTask<Void, Void, List<Masa>> {
        @Override
        protected List<Masa> doInBackground(Void... voids) {
            MasaService m = new MasaService();
            List<Masa> list = m.GetMasalar();
            return list;
        }

        @Override
        protected void onPostExecute(List<Masa> masas) {
            for (Masa m : masas) {
                /* bu arada yarıda bıraktım sen devam edersin sımdı gelelım anlatmaya equals buna da devam edersin containslerde
                btn1 in name yani id si yine btn1
                2. masanın btn2
                3. masanın btn3
                4. masanın btn4
                5. masanın btn5
                6. masanın btn6
                .
                .
                .
                burdan yaz
                hatanı gordun mu yazdım evet bende değiştim diye sanıyodum
                diye devam eder
                sana gelen apiden değer nedir masano olarak
                1 2 3 4 5 6 7 .... 20 dir
                masa nuumaraları böyledir
                bende dedim ki
                btn2 nin btn den sonraki kısmını al 2 dir
                buda masano2 ye denk gelir
                dolayısıyla 2. masa etkilenmiş olur
                anladın mı sımdı
                evet
                sorun var mı yok bi bak istersen pc den kalkcam cunku ya sorun orda değil de sorun şurda dünden beri masayı kapat alerti gelmiyo gelsede kapanmıyo tamama bastığım halde
                alerti kapatmak için alertadı.dissmiss eklersen yok ama pasif yapmıyo butonu
equalsle olacak dmeiştim
ama hala kapalı yazmıyo onlar vt deki kapalılar
sence neden yazmıyo
r if (getResources().getResourceEntryName(btn1.getId()).substring(3).equals(m.getMasano())) {
                    if (m.getDurum().equals("aktif")) {
                        btn1.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn1.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn1.setText("KAPALI");
                    }
                }
                kod bu bilmiyorum
                SetText yapmamıssın ki hiçbirinde nasıl
                canım settext yapmazsan aktif pasif olmaz ben sana sordum gerek yok dedin
                oyle bişey demedım yanlıs anlamıssındır
                yoo ben bunu daha yaparken kapalıya bile yazmam dedin geek yok
                neyse canım cozuldu suan eğer ekranda butona istiyorsan aktif pasif
                setText yazman lazım hepsıne ama aktif yazmayacak ki zaten aktif renkle geliyo calısıyor o zaman suan ya hayıır en başytada  böyleydi boşuna bunu yaptıkbos
                bosuna yaptıgımız yok ee hani kapalılar
                yazıyor ya kapalı
                ya anlamıyosun onlar vt de olan yaa
                *
                * */
                if (getResources().getResourceEntryName(btn1.getId()).substring(3).equals(m.getMasano())) {
                    btn1.setText("1");
                    if (m.getDurum().equals("aktif")) {
                        btn1.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn1.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn1.setText("KAPALI");
                    }
                }
                if (getResources().getResourceEntryName(btn2.getId()).substring(3).equals(m.getMasano())) {
                    btn2.setText("2");
                    if (m.getDurum().equals("aktif")) {
                        btn2.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn2.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn2.setText("KAPALI");
                    }
                }
                if (getResources().getResourceEntryName(btn3.getId()).substring(3).equals(m.getMasano())) {
                    btn3.setText("3");
                    if (m.getDurum().equals("aktif")) {
                        btn3.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn3.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn3.setText("KAPALI");
                    }
                }
                if (getResources().getResourceEntryName(btn4.getId()).substring(3).equals(m.getMasano())) {
                    btn4.setText("4");
                    if (m.getDurum().equals("aktif")) {
                        btn4.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn4.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn4.setText("KAPALI");
                    }
                }
                if (getResources().getResourceEntryName(btn5.getId()).substring(3).equals(m.getMasano())) {
                    btn5.setText("5");
                    if (m.getDurum().equals("aktif")) {
                        btn5.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn5.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn5.setText("KAPALI");
                    }
                }
                if (getResources().getResourceEntryName(btn6.getId()).substring(3).equals(m.getMasano())) {
                    btn6.setText("6");
                    if (m.getDurum().equals("aktif")) {
                        btn6.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn6.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn6.setText("KAPALI");
                    }
                }
                if (getResources().getResourceEntryName(btn7.getId()).substring(3).equals(m.getMasano())) {
                    btn7.setText("7");
                    if (m.getDurum().equals("aktif")) {
                        btn7.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn7.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn7.setText("KAPALI");
                    }
                }
                if (getResources().getResourceEntryName(btn8.getId()).substring(3).equals(m.getMasano())) {
                    btn8.setText("8");
                    if (m.getDurum().equals("aktif")) {
                        btn8.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn8.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn8.setText("KAPALI");
                    }
                }
                if (getResources().getResourceEntryName(btn9.getId()).substring(3).equals(m.getMasano())) {
                    btn9.setText("9");
                    if (m.getDurum().equals("aktif")) {
                        btn9.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn9.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn9.setText("KAPALI");
                    }
                }
                if (getResources().getResourceEntryName(btn10.getId()).substring(3).equals(m.getMasano())) {
                    btn10.setText("10");
                    if (m.getDurum().equals("aktif")) {
                        btn10.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn10.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn10.setText("KAPALI");
                    }
                }
                if (getResources().getResourceEntryName(btn11.getId()).substring(3).equals(m.getMasano())) {
                    btn11.setText("11");
                    if (m.getDurum().equals("aktif")) {
                        btn11.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn11.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn11.setText("KAPALI");
                    }
                }
                if (getResources().getResourceEntryName(btn12.getId()).substring(3).equals(m.getMasano())) {
                    btn12.setText("12");
                    if (m.getDurum().equals("aktif")) {
                        btn12.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn12.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn12.setText("KAPALI");
                    }
                }
                if (getResources().getResourceEntryName(btn13.getId()).substring(3).equals(m.getMasano())) {
                    btn13.setText("13");
                    if (m.getDurum().equals("aktif")) {
                        btn13.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn13.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn13.setText("KAPALI");
                    }
                }
                if (getResources().getResourceEntryName(btn14.getId()).substring(3).equals(m.getMasano())) {
                    btn14.setText("14");
                    if (m.getDurum().equals("aktif")) {
                        btn14.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn14.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn14.setText("KAPALI");
                    }
                }
                if (getResources().getResourceEntryName(btn15.getId()).substring(3).equals(m.getMasano())) {
                    btn15.setText("15");
                    if (m.getDurum().equals("aktif")) {
                        btn15.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn15.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn15.setText("KAPALI");
                    }
                }
                if (getResources().getResourceEntryName(btn16.getId()).substring(3).equals(m.getMasano())) {
                    btn16.setText("16");
                    if (m.getDurum().equals("aktif")) {
                        btn16.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn16.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn16.setText("KAPALI");
                    }
                }
                if (getResources().getResourceEntryName(btn17.getId()).substring(3).equals(m.getMasano())) {
                    btn17.setText("17");
                    if (m.getDurum().equals("aktif")) {
                        btn17.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn17.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn17.setText("KAPALI");
                    }
                }
                if (getResources().getResourceEntryName(btn18.getId()).substring(3).equals(m.getMasano())) {
                    btn18.setText("18");
                    if (m.getDurum().equals("aktif")) {
                        btn18.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn18.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn18.setText("KAPALI");
                    }
                }
                if (getResources().getResourceEntryName(btn19.getId()).substring(3).equals(m.getMasano())) {
                    btn19.setText("19");
                    if (m.getDurum().equals("aktif")) {
                        btn19.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn19.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn19.setText("KAPALI");
                    }
                }
                if (getResources().getResourceEntryName(btn20.getId()).substring(3).equals(m.getMasano())) {
                    btn20.setText("20");
                    if (m.getDurum().equals("aktif")) {
                        btn20.setBackgroundColor(getResources().getColor(R.color.aktif));
                    } else if (m.getDurum().equals("pasif")) {
                        btn20.setBackgroundColor(getResources().getColor(R.color.pasif));
                    } else {
                        btn20.setText("KAPALI");
                    }
                }

                //line += m.toString() + "\n";
            }
            //Toast.makeText(MasaSecimiActivity.this, line, Toast.LENGTH_LONG).show();
        }


    /*private class renklendir extends AsyncTask<String, String, String> {

        String z = "";

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... params) {

            String masano,durum;
            int id;

            try {
                System.out.println("5m");
                Connection con =connectionClass.connectDatabase();  System.out.println("6m");
                if (con == null) {
                    z = "Lütfen internet bağlantınızı kontrol edin";
                } else {

                    System.out.println("7m");
                    Statement st1= con.createStatement(),st2= con.createStatement(),st3= con.createStatement(),st4= con.createStatement(),
                            st5= con.createStatement(),st6= con.createStatement(),st7= con.createStatement(),st8= con.createStatement(),
                            st9= con.createStatement(),st10= con.createStatement(),st11= con.createStatement(),st12= con.createStatement(),
                            st13= con.createStatement(),st14= con.createStatement(),st15= con.createStatement(),st16= con.createStatement(),
                            st17= con.createStatement(),st18= con.createStatement(),st19= con.createStatement(),st20 = con.createStatement();
                    System.out.println("ilk");
                    ResultSet rs1 = st1.executeQuery("select * from masalar where masano='"+btn1.getText().toString()+"';");
                    System.out.println("ilk2");
                    if (rs1.next()) {
                        System.out.println("8");
                        durum = rs1.getString(4);
                        masano = rs1.getString(3);
                        if(durum.equals("aktif")){
                            btn1.setBackgroundColor(getResources().getColor(R.color.aktif));btn1.setText(masano);}
                        else if(durum.equals("pasif")){ btn1.setBackgroundColor(getResources().getColor(R.color.pasif));btn1.setText(masano);}

                    } else { btn1.setText("KAPALI"); }
                    ResultSet rs2 = st2.executeQuery("select * from masalar where masano='"+String.valueOf(btn2.getText()).toString()+"';");
                    if (rs2.next()) {
                        durum = rs2.getString(4);
                        masano = rs2.getString(3);
                        if(durum.equals("aktif")){ btn2.setBackgroundColor(getResources().getColor(R.color.aktif));btn2.setText(masano);}
                        else if(durum.equals("pasif")){ btn2.setBackgroundColor(getResources().getColor(R.color.pasif));btn2.setText(masano);}

                    }else { btn2.setText("KAPALI"); }
                    ResultSet rs3 = st3.executeQuery("select * from masalar where masano='"+String.valueOf(btn3.getText()).toString()+"';");
                    if (rs3.next()) {
                        durum = rs3.getString(4);
                        masano = rs3.getString(3);
                        if(durum.equals("aktif")){ btn3.setBackgroundColor(getResources().getColor(R.color.aktif));btn3.setText(masano);}
                        else if(durum.equals("pasif")){ btn3.setBackgroundColor(getResources().getColor(R.color.pasif));btn3.setText(masano);}

                    }else { btn3.setText("KAPALI"); }
                    ResultSet rs4 = st4.executeQuery("select * from masalar where masano='"+String.valueOf(btn4.getText()).toString()+"';");
                    if (rs4.next()) {
                        durum = rs4.getString(4);
                        masano = rs4.getString(3);
                        if(durum.equals("aktif")){ btn4.setBackgroundColor(getResources().getColor(R.color.aktif));btn4.setText(masano);}
                        else if(durum.equals("pasif")){ btn4.setBackgroundColor(getResources().getColor(R.color.pasif));btn4.setText(masano);}

                    }else { btn4.setText("KAPALI"); }
                    ResultSet rs5 = st5.executeQuery("select * from masalar where masano='"+String.valueOf(btn5.getText()).toString()+"';");
                    if (rs5.next()) {
                        durum = rs5.getString(4);
                        masano = rs5.getString(3);
                        if(durum.equals("aktif")){ btn5.setBackgroundColor(getResources().getColor(R.color.aktif));btn5.setText(masano);}
                        else if(durum.equals("pasif")){ btn5.setBackgroundColor(getResources().getColor(R.color.pasif));btn5.setText(masano);}

                    }else { btn5.setText("KAPALI"); }
                    ResultSet rs6 = st6.executeQuery("select * from masalar where masano='"+String.valueOf(btn6.getText()).toString()+"';");
                    if (rs6.next()) {
                        durum = rs6.getString(4);
                        masano = rs6.getString(3);
                        if(durum.equals("aktif")){ btn6.setBackgroundColor(getResources().getColor(R.color.aktif));btn6.setText(masano);}
                        else if(durum.equals("pasif")){ btn6.setBackgroundColor(getResources().getColor(R.color.pasif));btn6.setText(masano);}

                    } else { btn6.setText("KAPALI"); }
                    ResultSet rs7 = st7.executeQuery("select * from masalar where masano='"+String.valueOf(btn7.getText()).toString()+"';");
                    if (rs7.next()) {
                        durum = rs7.getString(4);
                        masano = rs7.getString(3);
                        if(durum.equals("aktif")){ btn7.setBackgroundColor(getResources().getColor(R.color.aktif));btn7.setText(masano);}
                        else if(durum.equals("pasif")){ btn7.setBackgroundColor(getResources().getColor(R.color.pasif));btn7.setText(masano);}

                    }else { btn7.setText("KAPALI"); }
                    ResultSet rs8 = st8.executeQuery("select * from masalar where masano='"+String.valueOf(btn8.getText()).toString()+"';");
                    if (rs8.next()) {
                        durum = rs8.getString(4);
                        masano = rs8.getString(3);
                        if(durum.equals("aktif")){ btn8.setBackgroundColor(getResources().getColor(R.color.aktif));btn8.setText(masano);}
                        else if(durum.equals("pasif")){ btn8.setBackgroundColor(getResources().getColor(R.color.pasif));btn8.setText(masano);}

                    } else { btn8.setText("KAPALI"); }
                    ResultSet rs9 = st9.executeQuery("select * from masalar where masano='"+String.valueOf(btn9.getText()).toString()+"';");
                    if (rs9.next()) {
                        durum = rs9.getString(4);
                        masano = rs9.getString(3);
                        if(durum.equals("aktif")){ btn9.setBackgroundColor(getResources().getColor(R.color.aktif));btn9.setText(masano);}
                        else if(durum.equals("pasif")){ btn9.setBackgroundColor(getResources().getColor(R.color.pasif));btn9.setText(masano);}

                    }else { btn9.setText("KAPALI"); }
                    ResultSet rs10 = st10.executeQuery("select * from masalar where masano='"+String.valueOf(btn10.getText()).toString()+"';");
                    if (rs10.next()) {
                        durum = rs10.getString(4);
                        masano = rs10.getString(3);
                        if(durum.equals("aktif")){ btn10.setBackgroundColor(getResources().getColor(R.color.aktif));btn10.setText(masano);}
                        else if(durum.equals("pasif")){ btn10.setBackgroundColor(getResources().getColor(R.color.pasif));btn10.setText(masano);}

                    }   else { btn10.setText("KAPALI"); }
                    ResultSet rs11 = st11.executeQuery("select * from masalar where masano='"+String.valueOf(btn11.getText()).toString()+"';");
                    if (rs11.next()) {
                        durum = rs11.getString(4);
                        masano = rs11.getString(3);
                        if(durum.equals("aktif")){ btn11.setBackgroundColor(getResources().getColor(R.color.aktif));btn11.setText(masano);}
                        else if(durum.equals("pasif")){ btn11.setBackgroundColor(getResources().getColor(R.color.pasif));btn11.setText(masano);}

                    }else { btn11.setText("KAPALI"); }
                    ResultSet rs12 = st12.executeQuery("select * from masalar where masano='"+String.valueOf(btn12.getText()).toString()+"';");
                    if (rs12.next()) {
                        durum = rs12.getString(4);
                        masano = rs12.getString(3);
                        if(durum.equals("aktif")){ btn12.setBackgroundColor(getResources().getColor(R.color.aktif));btn12.setText(masano);}
                        else if(durum.equals("pasif")){ btn12.setBackgroundColor(getResources().getColor(R.color.pasif));btn12.setText(masano);}

                    }else { btn12.setText("KAPALI"); }
                    ResultSet rs13 = st13.executeQuery("select * from masalar where masano='"+String.valueOf(btn13.getText()).toString()+"';");
                    if (rs13.next()) {
                        durum = rs13.getString(4);
                        masano = rs13.getString(3);
                        if(durum.equals("aktif")){ btn13.setBackgroundColor(getResources().getColor(R.color.aktif));btn13.setText(masano);}
                        else if(durum.equals("pasif")){ btn13.setBackgroundColor(getResources().getColor(R.color.pasif));btn13.setText(masano);}

                    } else { btn13.setText("KAPALI"); }
                    ResultSet rs14 = st14.executeQuery("select * from masalar where masano='"+String.valueOf(btn14.getText()).toString()+"';");
                    if (rs14.next()) {
                        durum = rs14.getString(4);
                        masano = rs14.getString(3);
                        if(durum.equals("aktif")){ btn14.setBackgroundColor(getResources().getColor(R.color.aktif));btn14.setText(masano);}
                        else if(durum.equals("pasif")){ btn14.setBackgroundColor(getResources().getColor(R.color.pasif));btn14.setText(masano);}

                    } else { btn14.setText("KAPALI"); }
                    ResultSet rs15 = st15.executeQuery("select * from masalar where masano='"+String.valueOf(btn15.getText()).toString()+"';");
                    if (rs15.next()) {
                        durum = rs15.getString(4);
                        masano = rs15.getString(3);
                        if(durum.equals("aktif")){ btn15.setBackgroundColor(getResources().getColor(R.color.aktif));btn15.setText(masano);}
                        else if(durum.equals("pasif")){ btn15.setBackgroundColor(getResources().getColor(R.color.pasif));btn15.setText(masano);}

                    } else { btn15.setText("KAPALI"); }
                    ResultSet rs16 = st16.executeQuery("select * from masalar where masano='"+String.valueOf(btn16.getText()).toString()+"';");
                    if (rs16.next()) {
                        durum = rs16.getString(4);
                        masano = rs16.getString(3);
                        if(durum.equals("aktif")){ btn16.setBackgroundColor(getResources().getColor(R.color.aktif));btn16.setText(masano);}
                        else if(durum.equals("pasif")){ btn16.setBackgroundColor(getResources().getColor(R.color.pasif));btn16.setText(masano);}

                    } else { btn16.setText("KAPALI"); }
                    ResultSet rs17 = st17.executeQuery("select * from masalar where masano='"+String.valueOf(btn17.getText()).toString()+"';");
                    if (rs17.next()) {
                        durum = rs17.getString(4);
                        masano = rs17.getString(3);
                        if(durum.equals("aktif")){ btn17.setBackgroundColor(getResources().getColor(R.color.aktif));btn17.setText(masano);}
                        else if(durum.equals("pasif")){ btn17.setBackgroundColor(getResources().getColor(R.color.pasif));btn17.setText(masano);}

                    } else { btn17.setText("KAPALI"); }
                    ResultSet rs18 = st18.executeQuery("select * from masalar where masano='"+String.valueOf(btn18.getText()).toString()+"';");
                    if (rs18.next()) {
                        durum = rs18.getString(4);
                        masano = rs18.getString(3);
                        if(durum.equals("aktif")){ btn18.setBackgroundColor(getResources().getColor(R.color.aktif));btn18.setText(masano);}
                        else if(durum.equals("pasif")){ btn18.setBackgroundColor(getResources().getColor(R.color.pasif));btn18.setText(masano);}

                    } else { btn18.setText("KAPALI"); }
                    ResultSet rs19 = st19.executeQuery("select * from masalar where masano='"+String.valueOf(btn19.getText()).toString()+"';");
                    if (rs19.next()) {
                        durum = rs19.getString(4);
                        masano = rs19.getString(3);
                        if(durum.equals("aktif")){ btn19.setBackgroundColor(getResources().getColor(R.color.aktif));btn19.setText(masano);}
                        else if(durum.equals("pasif")){ btn19.setBackgroundColor(getResources().getColor(R.color.pasif));btn19.setText(masano);}

                    } else { btn19.setText("KAPALI"); }
                    ResultSet rs20 = st20.executeQuery("select * from masalar where masano='"+String.valueOf(btn20.getText()).toString()+"';");
                    if (rs20.next()) {
                        durum = rs20.getString(4);
                        masano = rs20.getString(3);
                        if(durum.equals("aktif")){ btn20.setBackgroundColor(getResources().getColor(R.color.aktif));btn20.setText(masano);}
                        else if(durum.equals("pasif")){ btn20.setBackgroundColor(getResources().getColor(R.color.pasif));btn20.setText(masano);}

                    } else { btn20.setText("KAPALI"); }

                }
            } catch (Exception ex) {

                z = "Exceptions " + ex;
            }
            return z;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getBaseContext(), "" + z, Toast.LENGTH_LONG).show();


            }*/

    }


}



