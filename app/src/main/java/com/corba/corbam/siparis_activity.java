package com.corba.corbam;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class siparis_activity extends AppCompatActivity {
    private TextView textView, textView1, masakkisi, masadurum;
    Button geribtn, msykptbtn, sprseklebtn;
    ListView siparisydk;
    ArrayList<masaSiparisAl> siparsiler = new ArrayList<masaSiparisAl>();
    public static Double toplamfiyat = 0.0;
    public static String masakapasite;
    public static String secilimasadurumu;
    ProgressDialog progressDialog;
    ConnectionClass connectionClass;
    String glnmasano;
    myAdaptor adaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siparis_activity);
        textView = findViewById(R.id.textView);
        textView1 = findViewById(R.id.textView1);
        masakkisi = findViewById(R.id.masakkisi);
        masadurum = findViewById(R.id.masadurum);
        geribtn = findViewById(R.id.geribtn);
        msykptbtn = findViewById(R.id.msykptbtn);
        sprseklebtn = findViewById(R.id.sprseklebtn);
        siparisydk = findViewById(R.id.siparisydk);


        MasaSecimiActivity ms = new MasaSecimiActivity();
        glnmasano = ms.gndrmasano;
        textView.setText(" Masa Numarası: " + glnmasano);

        siparislistesi doregister = new siparislistesi();
        System.out.println("1");
        doregister.execute("");


        myAdaptor adaptor = new myAdaptor(this, 1, siparsiler);
        siparisydk.setAdapter(adaptor);


        geribtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtn11 = new Intent(siparis_activity.this, MasaSecimiActivity.class);
                startActivity(intentbtn11);
            }
        });
        sprseklebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtn11 = new Intent(siparis_activity.this, siparisAlactivity.class);
                startActivity(intentbtn11);
            }
        });


        msykptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (siparsiler.size() != 0) {

                    AlertDialog builder = new AlertDialog.Builder(siparis_activity.this).create();
                    builder.setTitle("MASA KAPAT");
                    builder.setMessage("Masa kapatılıp sipariş bilgileri sisteme kaydedilecektir ,Onaylıyor musunuz?");

                    builder.setButton(AlertDialog.BUTTON_NEGATIVE, "İPTAL", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            //İptal butonuna basılınca yapılacaklar.Sadece kapanması isteniyorsa boş bırakılacak

                        }
                    });
                    builder.setButton(AlertDialog.BUTTON_POSITIVE, "TAMAM", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            siparisiaktar doregisteraktar = new siparisiaktar();
                            System.out.println("1");
                            doregisteraktar.execute("");
                            Intent intentbtn11 = new Intent(siparis_activity.this, siparis_activity.class);
                            startActivity(intentbtn11);

                        }
                    });
                    builder.show();
                    builder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.aktif));
                    builder.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.pasif));
                } else if (siparsiler.size() == 0) {

                    siparisiaktar doregisteraktar = new siparisiaktar();
                    System.out.println("1");
                    doregisteraktar.execute("");
                    Intent intentbtn11 = new Intent(siparis_activity.this, siparis_activity.class);
                    startActivity(intentbtn11);
                }


            }
        });

    }

    public class siparislistesi extends AsyncTask<String, String, String> {


        String z = "";


        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... params) {
            toplamfiyat = 0.0;
            siparsiler.clear();
            try {
                Connection con = connectionClass.connectDatabase();
                System.out.println("6");
                if (con == null) {
                    z = "Lütfen internet bağlantınızı kontrol edin";
                } else {
                    System.out.println("7");
                    System.out.println("71");
                    String query = " select * from ydksiparisler where masano='" + glnmasano + "';";
                    System.out.println("72");
                    Statement stmt = con.createStatement();
                    System.out.println("73");
                    ResultSet rs = stmt.executeQuery(query);
                    System.out.println("6MM");
                    while (rs.next()) {
                        System.out.println("6MS");
                        masaSiparisAl sal = new masaSiparisAl();
                        System.out.println("6MD");
                        sal.setId(rs.getInt("id"));
                        System.out.println("6MÖ");
                        sal.setAd(rs.getString("ad"));
                        sal.setFiyat(rs.getString("fiyat"));
                        toplamfiyat = toplamfiyat + Double.parseDouble(rs.getString("fiyat"));
                        System.out.println(toplamfiyat);
                        siparsiler.add(sal);
                        System.out.println("6CM");


                    }
                    Statement stmt2 = con.createStatement();
                    ResultSet rs2 = stmt2.executeQuery("select * from masalar where masano='" + glnmasano + "'");
                    if (rs2.next()) {
                        masakapasite = "Masa : " + rs2.getString("kapasite");
                        secilimasadurumu = rs2.getString("durum");

                    }
                    masakkisi.setText(masakapasite);
                    masadurum.setText(secilimasadurumu);
                    textView1.setText(" TOPLAM : " + toplamfiyat.toString() + " TL");
                }
            } catch (Exception ex) {

                z = "Exceptions " + ex;
            }

            return z;
        }

        @Override
        protected void onPostExecute(String s) {
            //Toast.makeText(getBaseContext(), "" + z, Toast.LENGTH_LONG).show();

        }


    }

    public class siparisiaktar extends AsyncTask<String, String, String> {
        String z = "";

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... params) {
            toplamfiyat = 0.0;
            siparsiler.clear();
            try {
                Connection con = connectionClass.connectDatabase();
                System.out.println("6");
                if (con == null) {
                    z = "Lütfen internet bağlantınızı kontrol edin";
                } else {
                    String query = " select * from ydksiparisler where masano='" + glnmasano + "';";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    SimpleDateFormat dakika = new SimpleDateFormat("HH:mm:ss");
                    SimpleDateFormat tarih = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    Date time = new Date();
                    dakika.format(time);
                    tarih.format(date);
                    System.out.println(dakika.format(time));
                    System.out.println("tarihh" + tarih.format(date));


                    int siparsid = 0;
                    if (rs.next()) {

                        String queryurun = "SELECT * FROM menu WHERE id=" + rs.getInt("urunid") + ";";
                        Statement stmturun = con.createStatement();
                        ResultSet rsurun = stmturun.executeQuery(queryurun);
                        rsurun.next();

                        Statement stmt1 = con.createStatement();
                        stmt1.execute("INSERT INTO siparisler(masano, tarih, saat) VALUES ('" + glnmasano + "','" + tarih.format(date) + "','" + dakika.format(time) + "' );");
                        Statement stsp = con.createStatement();
                        System.out.println("dd1");
                        ResultSet rssp = stsp.executeQuery("select * from siparisler where masano= '" + glnmasano + "' and tarih='" + tarih.format(date) + "' and  saat='" + dakika.format(time) + "' ");
                        rssp.next();
                        System.out.println("dd2");
                        siparsid = rssp.getInt("id");
                        System.out.println(siparsid);
                        System.out.println("dd3");
                        Statement stmt1d = con.createStatement();
                        stmt1d.execute("INSERT INTO siparislistesi(spid, menuid,urunad,urunfiyat) VALUES (" + siparsid + "," + rs.getInt("urunid") + ",'" + rsurun.getString("ad") + "','" + rsurun.getString("fiyat") + "');");
                    }
                    while (rs.next()) {
                        String queryurun = "SELECT * FROM menu WHERE id=" + rs.getInt("urunid") + ";";
                        Statement stmturun = con.createStatement();
                        ResultSet rsurun = stmturun.executeQuery(queryurun);
                        rsurun.next();

                        System.out.println("dd3");
                        Statement stmt1 = con.createStatement();
                        stmt1.execute("INSERT INTO siparislistesi(spid, menuid,urunad,urunfiyat) VALUES (" + siparsid + "," + rs.getInt("urunid") + ",'" + rsurun.getString("ad") + "','" + rsurun.getString("fiyat") + "');");
                    }
                    System.out.println("dd4");
                    Statement stmtdlt = con.createStatement();
                    stmtdlt.execute(" DELETE FROM ydksiparisler WHERE masano='" + glnmasano + "';");
                    Statement stmtupt = con.createStatement();
                    stmtupt.execute(" update  masalar set durum='pasif'  WHERE masano='" + glnmasano + "';");
                }
            } catch (Exception ex) {

                z = "Exceptions " + ex;
            }

            return z;
        }

        @Override
        protected void onPostExecute(String s) {
            //Toast.makeText(getBaseContext(), "" + z, Toast.LENGTH_LONG).show();

        }


    }


    public class myAdaptor extends ArrayAdapter<masaSiparisAl> {
        ArrayList<masaSiparisAl> arr;
        private TextView text;
        LayoutInflater layoutInflater = getLayoutInflater();

        public myAdaptor(Context context, int textViewResourceId, ArrayList<masaSiparisAl> mList) {
            super(context, textViewResourceId, mList);
            this.arr = mList;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View satirView;
            satirView = layoutInflater.inflate(R.layout.rowlayout, null);

            TextView msid = (TextView) satirView.findViewById(R.id.msid);
            TextView spad = (TextView) satirView.findViewById(R.id.lblmsad);
            TextView spfyt = (TextView) satirView.findViewById(R.id.lblfiyat);
            final Button btsil = (Button) satirView.findViewById(R.id.btnekle);

            MasaSecimiActivity ms = new MasaSecimiActivity();
            glnmasano = ms.gndrmasano;
            final masaSiparisAl msiparsal = arr.get(position);

            msid.setText(String.valueOf(msiparsal.getId()));
            spad.setText(msiparsal.getAd().toString());
            spfyt.setText(msiparsal.getFiyat());

            btsil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    siparislistesindencikar doregister = new siparislistesindencikar();
                    System.out.println(msiparsal.getId());
                    doregister.idal(msiparsal.getId(), msiparsal.getAd());
                    doregister.execute("");
                    toplamfiyat = toplamfiyat - Double.parseDouble(msiparsal.getFiyat());
                    textView1.setText(" TOPLAM : " + toplamfiyat.toString() + " TL");
                    arr.remove(position);
                    myAdaptor adaptor = new myAdaptor(siparis_activity.this, 1, arr);
                    siparisydk.setAdapter(adaptor);


                }
            });


            return satirView;
        }

        private class siparislistesindencikar extends AsyncTask<String, String, String> {
            int idgln;
            boolean isSuccess = false;
            String sedlene;

            public void idal(int id, String sedln) {
                System.out.println(id);
                idgln = id;
                sedlene = sedln;
            }

            String z = "";

            @Override
            protected void onPreExecute() {

                super.onPreExecute();
            }

            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(String... params) {

                try {
                    Connection con = connectionClass.connectDatabase();
                    System.out.println("6");
                    if (con == null) {
                        z = "Lütfen internet bağlantınızı kontrol edin";
                    } else {
                        System.out.println("7");
                        String query = " delete  from ydksiparisler where id=" + idgln + "";
                        System.out.println(glnmasano);
                        System.out.println("ee");
                        Statement stmt = con.createStatement();
                        System.out.println("sdsa");
                        Boolean aa = stmt.execute(query);
                        if (aa) {
                            System.out.println("dsd");
                            isSuccess = true;
                            z = " " + sedlene + " listeden çıkarıldı";


                        }

                    }
                } catch (Exception ex) {

                    z = "Exceptions " + ex;
                }

                return z;
            }

            @Override
            protected void onPostExecute(String s) {
                //Toast.makeText(siparis_activity.this, z , Toast.LENGTH_SHORT).show();


            }


        }
    }


}


