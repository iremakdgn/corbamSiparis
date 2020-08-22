package com.corba.corbam;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.corba.corbam.Entities.Menu;
import com.corba.corbam.Entities.YdkSiparisler;
import com.corba.corbam.Services.MasaService;
import com.corba.corbam.Services.MenuService;
import com.corba.corbam.Services.YdkSiparislerService;

import java.util.ArrayList;
import java.util.List;

public class siparisAlactivity extends AppCompatActivity {

    private TextView masanoo;
    private ImageButton ibtn;
    String glnmasano;
    ArrayList<menu> menulist = new ArrayList<menu>();
    ProgressDialog progressDialog;
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
                Intent intentbtn = new Intent(siparisAlactivity.this, siparis_activity.class);
                startActivity(intentbtn);
            }
        });
        MasaSecimiActivity ms = new MasaSecimiActivity();
        glnmasano = ms.gndrmasano;
        masanoo.setText(" Masa Numarası: " + glnmasano);

        new GetMenu().execute();
    }

    private void RefreshAdapter() {
        myAdaptor adaptor = new myAdaptor(siparisAlactivity.this, 1, menulist);
        listmenu.setAdapter(adaptor);
    }

    public class GetMenu extends android.os.AsyncTask<Void, Void, List<Menu>> {
        @Override
        protected List<Menu> doInBackground(Void... voids) {
            MenuService m = new MenuService();
            List<Menu> list = m.GetMenu();
            return list;
        }

        @Override
        protected void onPostExecute(List<Menu> menus) {
            for (Menu m : menus) {
                menulist.add(new menu(m.getId(), m.getAd(), m.getFiyat(), m.getTur()));
            }
            RefreshAdapter();
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
            final TextView mnad = (TextView) satirView.findViewById(R.id.lblmsad);
            final TextView mnfyt = (TextView) satirView.findViewById(R.id.lblfiyat);
            ImageButton btekle = (ImageButton) satirView.findViewById(R.id.btnekle);
            final menu mn = arr.get(position);
            msid.setText(String.valueOf(mn.getId()));
            mnad.setText(mn.getAd());
            mnfyt.setText(mn.getFiyat());


            btekle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog builder = new AlertDialog.Builder(siparisAlactivity.this).create();

                    builder.setMessage(mn.getAd() + " seçildi masanın siparis kısmına eklensin mi?");

                    builder.setButton(AlertDialog.BUTTON_NEGATIVE, "HAYIR", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
                    builder.setButton(AlertDialog.BUTTON_POSITIVE, "EVET", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            new AsyncSiparisEkle(mn.getAd(), mn.getId(), mn.getFiyat(), glnmasano).execute();

                        }
                    });
                    builder.show();
                    builder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.aktif));
                    builder.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.pasif));
                }
            });
            return satirView;
        }

        public class AsyncSiparisEkle extends android.os.AsyncTask<Void, Void, Void> {

            String ad;
            int urunid;
            String fiyat;
            String masaNo;

            public AsyncSiparisEkle(String ad, int urunid, String fiyat, String masaNo) {
                this.ad = ad;
                this.urunid = urunid;
                this.fiyat = fiyat;
                this.masaNo = masaNo;
            }

            @Override
            protected Void doInBackground(Void... params) {

                MenuService menuService = new MenuService();
                Menu menu = menuService.GetMenuById(urunid);
                if (menu != null) {
                    YdkSiparislerService ydkSiparislerService = new YdkSiparislerService();
                    YdkSiparisler ydkSiparis = new YdkSiparisler();
                    ydkSiparis.setAd(ad);
                    ydkSiparis.setUrunid(urunid);
                    ydkSiparis.setFiyat(fiyat);
                    ydkSiparis.setMasano(masaNo);
                    ydkSiparislerService.PostYdkSiparisler(ydkSiparis);

                    MasaService masaService = new MasaService();
                    masaService.UpdateMasaDurumByMasaNo(glnmasano, "aktif");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                RefreshAdapter();
                super.onPostExecute(aVoid);
            }
        }
    }
}





