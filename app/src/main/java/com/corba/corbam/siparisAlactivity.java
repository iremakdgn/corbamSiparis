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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class siparisAlactivity extends AppCompatActivity {

    private TextView masanoo;
    private ImageButton ibtn;
    String glnmasano;
    ArrayList<menu> menulist = new ArrayList<menu>();
    ProgressDialog progressDialog;
    ConnectionClass connectionClass;

    private ListView listmenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.siparisal_activity);
        masanoo = (TextView) findViewById(R.id.masanoo);
        ibtn = (ImageButton) findViewById(R.id.geributon);
        listmenu = (ListView) findViewById(R.id.listmenu);

        ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtn11 = new Intent(siparisAlactivity.this, siparis_activity.class);
                startActivity(intentbtn11);
            }
        });
        MasaSecimiActivity ms = new MasaSecimiActivity();
        glnmasano = ms.gndrmasano;
        masanoo.setText(" Masa Numarası: " + glnmasano);

        listeleme doregister = new listeleme();
        System.out.println("1");
        doregister.execute("");
        myAdaptor adaptor = new myAdaptor(siparisAlactivity.this, 1, menulist);
        listmenu.setAdapter(adaptor);


    }


    public class listeleme extends AsyncTask<String, String, String> {

        String z = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            menulist.clear();
            try {
                Connection con = connectionClass.connectDatabase();
                if (con == null) {
                    z = "Lütfen internet bağlantınızı kontrol edin";
                } else {
                    String query = " select * from menu where tur='Yiyecek';";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        System.out.println("dene111");
                        menulist.add(new menu(rs.getInt("id"), rs.getString("ad"), rs.getString("fiyat"), rs.getString("tur")));
                        System.out.println("dene111");
                        System.out.println(rs.getString("ad") + "adddddd");
                    }
                    Statement stmt2 = con.createStatement();
                    ResultSet rs2 = stmt2.executeQuery("select * from menu where tur='İçecek';");
                    while (rs2.next()) {

                        menulist.add(new menu(rs2.getInt("id"), rs2.getString("ad"), rs2.getString("fiyat"), rs2.getString("tur")));
                    }
                }
            } catch (Exception ex) {

                z = "Exceptions " + ex;
            }

            return z;
        }

        @Override
        protected void onPostExecute(String s) {


        }


    }


    public class myAdaptor extends ArrayAdapter<menu> {
        ArrayList<menu> arr;
        private TextView text;
        LayoutInflater layoutInflater = getLayoutInflater();

        public myAdaptor(Context context, int textViewResourceId, ArrayList<menu> mList) {
            super(context, textViewResourceId, mList);
            this.arr = mList;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View satirView;
            satirView = layoutInflater.inflate(R.layout.menuliste, null);

            TextView msid = (TextView) satirView.findViewById(R.id.msid);
            TextView mnad = (TextView) satirView.findViewById(R.id.lblmsad);
            TextView mnfyt = (TextView) satirView.findViewById(R.id.lblfiyat);
            ImageButton btekle = (ImageButton) satirView.findViewById(R.id.btnekle);
            final menu mn = arr.get(position);
            msid.setText(String.valueOf(mn.getId()));
            mnad.setText(mn.getAd().toString());
            mnfyt.setText(mn.getFiyat());


            btekle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog builder = new AlertDialog.Builder(siparisAlactivity.this).create();

                    builder.setMessage(mn.getAd() + " seçildi masanın siparis kısmına eklensin mi?");

                    builder.setButton(AlertDialog.BUTTON_NEGATIVE, "HAYIR", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            //İptal butonuna basılınca yapılacaklar.Sadece kapanması isteniyorsa boş bırakılacak

                        }
                    });
                    builder.setButton(AlertDialog.BUTTON_POSITIVE, "EVET", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            sipariseekle doregister = new sipariseekle();
                            System.out.println(mn.getId());
                            doregister.idal(mn.getId(), mn.getAd());
                            doregister.execute("");

                        }
                    });
                    builder.show();
                    builder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.aktif));
                    builder.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.pasif));


                }
            });
            return satirView;
        }

        private class sipariseekle extends AsyncTask<String, String, String> {
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


            @Override
            protected String doInBackground(String... params) {

                try {
                    Connection con = connectionClass.connectDatabase();
                    System.out.println("6");
                    if (con == null) {
                        z = "Lütfen internet bağlantınızı kontrol edin";
                    } else {
                        Statement stmn = con.createStatement();
                        System.out.println(idgln + "idgln");
                        ResultSet rsmn = stmn.executeQuery("select * from menu where id=" + idgln);
                        if (rsmn.next()) {
                            System.out.println(idgln + "idgln");
                            String query = "INSERT INTO ydksiparisler(ad,urunid,fiyat,masano) VALUES ('" + rsmn.getString("ad") + "'," + rsmn.getInt("id") + ",'" + rsmn.getString("fiyat") + "','" + glnmasano + "');";
                            System.out.println(idgln + "iee");
                            Statement stmekle = con.createStatement();
                            stmekle.execute(query);
                            System.out.println(idgln + "idgln2");
                            Statement stgncl = con.createStatement();
                            stgncl.execute("UPDATE masalar SET durum='aktif'  where  masano='" + glnmasano + "';");
                            Toast.makeText(siparisAlactivity.this, "EKLENDİ", Toast.LENGTH_SHORT).show();
                        }

                    }
                } catch (Exception ex) {

                    z = "Exceptions " + ex;
                }

                return z;
            }

            @Override
            protected void onPostExecute(String s) {
                // Toast.makeText(siparisAlactivity.this, z , Toast.LENGTH_SHORT).show();


            }


        }
    }
}





