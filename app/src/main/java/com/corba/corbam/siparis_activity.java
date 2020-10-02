package com.corba.corbam;

import android.app.AlertDialog;
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

import androidx.appcompat.app.AppCompatActivity;

import com.corba.corbam.Entities.Masa;
import com.corba.corbam.Entities.Menu;
import com.corba.corbam.Entities.Siparis;
import com.corba.corbam.Entities.SiparisListesi;
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
    public static Double fiyat = 0.0;
    public static String masakapasite;
    public static String secilimasadurumu;
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

        textView.setText(" Masa Numarası: " + glnmasano);

        geribtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtn = new Intent(siparis_activity.this, MasaSecimiActivity.class);
                startActivity(intentbtn);
            }
        });
        sprseklebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtn = new Intent(siparis_activity.this, siparisAlactivity.class);
                startActivity(intentbtn);
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
                        }
                    });
                    builder.setButton(AlertDialog.BUTTON_POSITIVE, "TAMAM", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            new AsyncIslemiTamamla().execute();
                            Intent intentbtn = new Intent(siparis_activity.this, MasaSecimiActivity.class);
                            startActivity(intentbtn);

                        }
                    });
                    builder.show();
                    builder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.aktif));
                    builder.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.pasif));
                } else if (siparsiler.size() == 0) {
                    new AsyncIslemiTamamla().execute();
                    Intent intentbtn = new Intent(siparis_activity.this, siparis_activity.class);
                    startActivity(intentbtn);
                }
            }
        });
    }

    private void RefreshAdapter() {
        myAdaptor adaptor = new myAdaptor(this, 1, siparsiler);
        siparisydk.setAdapter(adaptor);
    }

    public class GetYdkSiparisler extends android.os.AsyncTask<Void, Void, Void> {

        String masaNo;
        List<YdkSiparisler> ydkSiparislerList;
        Masa masa;

        public GetYdkSiparisler(String masaNo) {
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
            toplamfiyat = 0.0;

            for (YdkSiparisler m : ydkSiparislerList) {
                masaSiparisAl sal = new masaSiparisAl();
                sal.setId(m.getId());
                sal.setAd(m.getAd());
                sal.setAdet(m.getAdet());
                sal.setFiyat(m.getFiyat());
                fiyat =  Double.parseDouble(m.getFiyat())*(m.getAdet());
                toplamfiyat = toplamfiyat + fiyat;
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
        }
    }

    public class AsyncIslemiTamamla extends android.os.AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            toplamfiyat = 0.0;
            siparsiler.clear();

            YdkSiparislerService ydkSiparislerService = new YdkSiparislerService();
            //Burada ydkSiparisler çekildi.
            List<YdkSiparisler> listYdkSiparis = ydkSiparislerService.GetYdkSiparisler(glnmasano);
            SimpleDateFormat saat = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat tarih = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Date time = new Date();
            //saat.format(time);
            //tarih.format(date);

            // Servisler her zaman forların dısında olmalıdır. İstisnalar Hariç
            SiparisService s = new SiparisService();
            MenuService menuService = new MenuService();
            SiparisListesiService siparisListesiService = new SiparisListesiService();
            for (YdkSiparisler ydkSiparisler : listYdkSiparis) {

                Menu menu = menuService.GetMenuById(ydkSiparisler.getUrunid()); //Git o ürüne ait bilgileri çek.
                Siparis yeniSiparis = new Siparis(); //Yeni bir sipariş NESNESİ oluşturduk. Service değildir bu!
                yeniSiparis.setMasano(Integer.parseInt(glnmasano));
                yeniSiparis.setTarih(tarih.format(date));
                yeniSiparis.setSaat(saat.format(time));
                //Oluşturduğumuz siparişi apiye gönderdik. Api de bize eklenen kaydın İD sini geriye dönderdi.
                int siparisid = s.PostSiparisler(yeniSiparis);

                //Daha sonra sipariş listesi tablosuna eklemek için sınıf oluşturduk. Bilgileri set ettik.
                SiparisListesi siparisListesi = new SiparisListesi();
                siparisListesi.setMenuid(menu.getId());
                siparisListesi.setUrunad(menu.getAd());
                siparisListesi.setSpid(siparisid);
                siparisListesi.setUrunfiyat(menu.getFiyat());
                //Post ettik bilgileri.
                siparisListesiService.PostSiparisListesi(siparisListesi);
            }
//Masano daki siparişler silindi
            ydkSiparislerService.DeleteYdkSiparislerByMasaNo(glnmasano);

            //Masa temize çıktığı için durumu pasif e çevrildi!
            MasaService masaService = new MasaService();
            masaService.UpdateMasaDurumByMasaNo(glnmasano, "pasif");

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            RefreshAdapter();
        }
    }

    class DeleteYdkSiparisler extends android.os.AsyncTask<Void, Void, YdkSiparisler> {
        boolean isSuccess = false;
        int id;

        public DeleteYdkSiparisler(int id) {
            this.id = id;
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
        LayoutInflater layoutInflater;

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
            TextView spadet= (TextView) satirView.findViewById(R.id.lbladet);
            TextView spfyt = (TextView) satirView.findViewById(R.id.lblfiyat);
            final Button btsil = (Button) satirView.findViewById(R.id.btnsil);

            final MasaSecimiActivity ms = new MasaSecimiActivity();
            glnmasano = ms.gndrmasano;
            final masaSiparisAl msiparsal = arr.get(position);

            msid.setText(String.valueOf(msiparsal.getId()));
            spad.setText(msiparsal.getAd());
            final Double fyat = Double.parseDouble(msiparsal.getFiyat()) *msiparsal.getAdet();
            spadet.setText(  String.valueOf(msiparsal.getAdet()) + "x" + msiparsal.getFiyat());
            spfyt.setText(fyat.toString());

            btsil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DeleteYdkSiparisler(msiparsal.getId()).execute();
                    toplamfiyat = toplamfiyat - fyat;
                    textView1.setText(" TOPLAM : " + toplamfiyat.toString() + " TL");
                    arr.remove(position);
                    RefreshAdapter();

                }
            });
            return satirView;
        }
    }
}