package com.corba.corbam;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Serkan on 05.04.2015.
 */
public class ozelAdaptor extends BaseAdapter {
    LayoutInflater layoutInflater;
    List<masaSiparisAl> list;
    Activity activity;
    ProgressDialog progressDialog;
    ConnectionClass connectionClass;
    int islemid;
    String siparisedilen;
    String glnmasano;


    public ozelAdaptor(Activity activity, List<masaSiparisAl> mList){
        layoutInflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list=mList;
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View satirView;

        satirView=layoutInflater.inflate(R.layout.rowlayout, null);

        TextView msid=(TextView)satirView.findViewById(R.id.msid);
        TextView spad=(TextView)satirView.findViewById(R.id.lblmsad);
        TextView spfyt=(TextView)satirView.findViewById(R.id.lblfiyat);
        Button btsil=(Button)satirView.findViewById(R.id.btnekle);

        MasaSecimiActivity ms=new MasaSecimiActivity();
        final siparis_activity sp=new siparis_activity();
        glnmasano=ms.gndrmasano;

        final masaSiparisAl msiparsal=list.get(position);

        msid.setText(String.valueOf(msiparsal.getId()));
        spad.setText(msiparsal.getAd().toString());
        spfyt.setText(msiparsal.getFiyat());

        btsil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                islemid=msiparsal.getId();
                siparisedilen=msiparsal.getAd();
                siparislistesindencikar doregister = new siparislistesindencikar();
                System.out.println(msiparsal.getId());
                doregister.idal(msiparsal.getId(),msiparsal.getAd());
                System.out.println("1");
                doregister.execute("");





            }
        });


        return satirView;
    }
    private class siparislistesindencikar extends AsyncTask<String, String, String> {
          int idgln;
        boolean isSuccess = false;
          String sedlene;
        public void idal(int id,String sedln){
            System.out.println(id);
            idgln=id;
            sedlene=sedln;
        }
        String z = "";

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                Connection con =connectionClass.connectDatabase();
                System.out.println("6");
                if (con == null) {
                    z = "Lütfen internet bağlantınızı kontrol edin";
                } else {
                    System.out.println("7");
                    String query = " delete  from ydksiparisler where id=" + idgln+ "";
                    System.out.println(glnmasano);
                    System.out.println("ee");
                    Statement stmt = con.createStatement();
                    System.out.println("sdsa");
                    Boolean aa= stmt.execute(query);
                    if (aa) {
                        System.out.println("dsd");
                        isSuccess = true;
                       z=" "+sedlene+" listeden çıkarıldı";

                    }

                }
            } catch (Exception ex) {

                z = "Exceptions " + ex;
            }

            return z;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(activity, z , Toast.LENGTH_SHORT).show();



        }


    }
}