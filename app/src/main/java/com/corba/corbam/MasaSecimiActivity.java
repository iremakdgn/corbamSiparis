package com.corba.corbam;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.corba.corbam.Entities.Masa;
import com.corba.corbam.Services.MasaService;

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
                    }
                });
                builder.setButton(AlertDialog.BUTTON_POSITIVE, "EVET", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intentbtn = new Intent(MasaSecimiActivity.this, GirisActivity.class);
                        startActivity(intentbtn);

                    }
                });
                builder.show();
                builder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.aktif));
                builder.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.pasif));

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn1.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn1.getText().toString();
                    Intent intentbtn = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn);
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn2.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn2.getText().toString();
                    Intent intentbtn = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn);
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if (!btn3.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn3.getText().toString();
                    Intent intentbtn = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn);
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn4.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn4.getText().toString();
                    Intent intentbtn = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn);
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
                    Intent intentbtn = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn);
                }
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn7.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn7.getText().toString();
                    Intent intentbtn = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn);
                }
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn8.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn8.getText().toString();
                    Intent intentbtn = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn);
                }
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn9.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn9.getText().toString();
                    Intent intentbtn = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn);

                }
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn10.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn10.getText().toString();
                    Intent intentbtn = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn);
                }
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn11.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn11.getText().toString();
                    Intent intentbtn = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn);
                }
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn12.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn12.getText().toString();
                    Intent intentbtn = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn);
                }
            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn13.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn13.getText().toString();
                    Intent intentbtn = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn);
                }
            }
        });
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn14.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn14.getText().toString();
                    Intent intentbtn = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn);
                }
            }
        });
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn15.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn15.getText().toString();
                    Intent intentbtn = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn);
                }
            }
        });
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn16.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn16.getText().toString();
                    Intent intentbtn = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn);
                }
            }
        });
        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn17.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn17.getText().toString();
                    Intent intentbtn = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn);

                }
            }
        });
        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn18.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn18.getText().toString();
                    Intent intentbtn = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn);
                }
            }
        });
        btn19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn19.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn19.getText().toString();
                    Intent intentbtn = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn);

                }
            }
        });
        btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn20.getText().toString().equals("KAPALI")) {
                    gndrmasano = btn20.getText().toString();
                    Intent intentbtn = new Intent(MasaSecimiActivity.this, siparis_activity.class);
                    startActivity(intentbtn);
                }
            }
        });
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
            }
        }
    }
}