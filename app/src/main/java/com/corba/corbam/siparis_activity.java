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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.corba.corbam.Entities.Masa;
import com.corba.corbam.Entities.Menu;
import com.corba.corbam.Entities.Siparis;
import com.corba.corbam.Entities.YdkSiparisler;
import com.corba.corbam.Services.MasaService;
import com.corba.corbam.Services.MenuService;
import com.corba.corbam.Services.SiparisListesiService;
import com.corba.corbam.Services.SiparisService;
import com.corba.corbam.Services.YdkSiparislerService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        new GetYdkSiparisler(glnmasano).execute();

        //new GetMasalarByMasano(glnmasano).execute();

        textView.setText(" Masa Numarası: " + glnmasano);

//        siparislistesi doregister = new siparislistesi();
//        doregister.execute("");

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
                            //burada siparişi aktarma async calısacak
//                            siparisiaktar doregisteraktar = new siparisiaktar();
                            new AsyncIslemiTamamla().execute();
//                            doregisteraktar.execute("");
                            Intent intentbtn11 = new Intent(siparis_activity.this, MasaSecimiActivity.class);
                            startActivity(intentbtn11);


                        }
                    });
                    builder.show();
                    builder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.aktif));
                    builder.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.pasif));
                } else if (siparsiler.size() == 0) {
                    new AsyncIslemiTamamla().execute();

//                    siparisiaktar doregisteraktar = new siparisiaktar();
//                    System.out.println("1");
//                    doregisteraktar.execute("");
                    Intent intentbtn11 = new Intent(siparis_activity.this, siparis_activity.class);
                    startActivity(intentbtn11);
                }
            }
        });
    }
    private void RefreshAdapter()
    {
        myAdaptor adaptor = new myAdaptor(this, 1, siparsiler);
        siparisydk.setAdapter(adaptor);
    }

    public class GetYdkSiparisler extends android.os.AsyncTask<Void, Void, Void> {

        String masaNo;
        List<YdkSiparisler> ydkSiparislerList;
        Masa masa;

        public
        GetYdkSiparisler(String masaNo) {
            this.masaNo = masaNo;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            YdkSiparislerService m = new YdkSiparislerService();
            ydkSiparislerList = m.GetYdkSiparisler(masaNo);

            MasaService masaService = new MasaService();
            masa = masaService.GetMasaByMasaNo(masaNo);
            return null;
        }

        @Override
        protected void onPostExecute(Void avoid) {
            siparsiler.clear();
            toplamfiyat=0.0;
            for (YdkSiparisler m : ydkSiparislerList) {

                masaSiparisAl sal = new masaSiparisAl();
                sal.setId(m.getId());
                sal.setAd(m.getAd());
                sal.setFiyat(m.getFiyat());
                toplamfiyat = toplamfiyat + Double.parseDouble(m.getFiyat());
                siparsiler.add(sal);
            }

            if (masa != null) {
                masakapasite = "Masa : " + masa.getKapasite();
                secilimasadurumu = masa.getDurum();
            }
            masakkisi.setText(masakapasite);
            masadurum.setText(secilimasadurumu);
            textView1.setText(" TOPLAM : " + toplamfiyat.toString() + " TL");


            SimpleDateFormat dakika = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat tarih = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date time = new Date();
            dakika.format(time);
            tarih.format(date);
            RefreshAdapter();
            //toplamfiyat=0.0;
        }

    }


    public class AsyncIslemiTamamla extends android.os.AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            Date date = new Date();
            Date time = new Date();
            SimpleDateFormat saat = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat tarih = new SimpleDateFormat("yyyy-MM-dd");
            saat.format(time);
            tarih.format(date);
            SiparisService s = new SiparisService();

            Siparis siparsid = s.PostSiparis(glnmasano, tarih, saat);

            YdkSiparislerService ydkSiparislerService = new YdkSiparislerService();
            List<YdkSiparisler> listYdkSiparis = ydkSiparislerService.GetYdkSiparisler(glnmasano);

            toplamfiyat = 0.0;
            siparsiler.clear();
            MenuService menuService = new MenuService();
            for (YdkSiparisler ydkSiparisler : listYdkSiparis) {
                Menu menu = menuService.GetMenuById(ydkSiparisler.getUrunid());

                SiparisListesiService siparisListesiService = new SiparisListesiService();
                siparisListesiService.PostSiparisListesi(siparsid, menu.getId(), menu.getAd(), menu.getFiyat());
            }

            ydkSiparislerService.DeleteYdkSiparislerByMasaNo(glnmasano);

            MasaService masaService = new MasaService();
            masaService.UpdateMasaDurumByMasaNo(glnmasano, "pasif");

            return null;
        }
    }

    class DeleteYdkSiparisler extends android.os.AsyncTask<Void, Void, YdkSiparisler> {
        boolean isSuccess = false;
        int id;

        public DeleteYdkSiparisler(int id) {
            this.id=id;
        }


        @Override
        protected YdkSiparisler doInBackground(Void... voids) {
            YdkSiparislerService m = new YdkSiparislerService();
            YdkSiparisler list = m.DeleteYdkSiparisler(id);
            return list;
        }

        @Override
        protected void onPostExecute(YdkSiparisler ydkSiparislers) {
                if (ydkSiparislers != null) {
                    isSuccess = true;
                    ydkSiparislers.getId();

                }
        }
    }

    public class myAdaptor extends ArrayAdapter<masaSiparisAl> {
        ArrayList<masaSiparisAl> arr;
        //private TextView text;
        LayoutInflater layoutInflater;
        //String glnmasano;
       // Double toplamfiyat = 0.0;
        //TextView textView1;
        ListView siparisydk;

        public myAdaptor(Context context, int textViewResourceId, ArrayList<masaSiparisAl> mList) {
            super(context, textViewResourceId, mList);
            this.arr = mList;
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View satirView;
            satirView = layoutInflater.inflate(R.layout.rowlayout, null);

            TextView msid = (TextView) satirView.findViewById(R.id.msid);
            TextView spad = (TextView) satirView.findViewById(R.id.lblmsad);
            TextView spfyt = (TextView) satirView.findViewById(R.id.lblfiyat);
            final Button btsil = (Button) satirView.findViewById(R.id.btnekle);

            final MasaSecimiActivity ms = new MasaSecimiActivity();
            glnmasano = ms.gndrmasano;
            final masaSiparisAl msiparsal = arr.get(position);

            msid.setText(String.valueOf(msiparsal.getId()));
            spad.setText(msiparsal.getAd().toString());
            spfyt.setText(msiparsal.getFiyat());

            btsil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DeleteYdkSiparisler(msiparsal.getId()).execute();
                    toplamfiyat = toplamfiyat - Double.parseDouble(msiparsal.getFiyat());
                    textView1.setText(" TOPLAM : " + toplamfiyat.toString() + " TL");
                    arr.remove(position);//bu ekrandan siliyor buna bakma
                    RefreshAdapter();

                }
            });
            return satirView;
        }
    }
}