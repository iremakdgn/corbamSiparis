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

    /*
    sen asynctask metotlarını yazmıstın her bir metot için service lerdeki ben sana öyle olmıcak demiştim
    sen o sıra parantezleri silerken hata yapmıssın
    myadaptor sınıfın senın asynctask sınıfının içinde kalmıs ondan dolayı da myadaptor diye erişemezsin direk o ana sınıf adı.myadaptor diye erişebilirsin şimdi
    bu cümle kalsın burada sen o linktekini oku anlıcan canım ben dün parantezlere çok baktım ama sen normal eksik veya
    parantez var mı diye bakmıssın onlarda sorun yoktu zaten
    sayok yok gittim myadapter ü yanlış parantez içimde mi kullandım diye baktım çok
    görememişsin o zaman sımdı o attıgım lınkı okursan iyice oturur konu var mı sorun başka bi çalıştısana
    çalıştırma ile çalışmayabilir o farklı bir şey
    nasıl yanı o taraflarda hata varsa onu bilemem diyorum tamam bi bak
adaptere set edersen gelir onlarda bak sen takılırsan bakarız yıne linki de okumayı unutma ben kactım canım nasıl set
orada anlatıyor tamam bakarım kolay gelsin acanım
    */


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
                            System.out.println("1");
//                            doregisteraktar.execute("");
                            Intent intentbtn11 = new Intent(siparis_activity.this, siparis_activity.class);
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
                Menu menu = menuService.GetMenuByUrunId(ydkSiparisler.getUrunid());

                SiparisListesiService siparisListesiService = new SiparisListesiService();
                siparisListesiService.PostSiparisListesi(siparsid, menu.getId(), menu.getAd(), menu.getFiyat());
            }

            ydkSiparislerService.DeleteYdkSiparislerByMasaNo(glnmasano);

            MasaService masaService = new MasaService();
            masaService.UpdateMasaDurumToPasifByMasaNo(glnmasano);

            return null;
        }
    }

    class DeleteYdkSiparisler extends android.os.AsyncTask<Void, Void, List<YdkSiparisler>> {
        boolean isSuccess = false;
        boolean aa = false;

        @Override
        protected List<YdkSiparisler> doInBackground(Void... voids) {
            YdkSiparislerService m = new YdkSiparislerService();
            List<YdkSiparisler> list = m.DeleteYdkSiparisler();
            return list;
        }

        @Override
        protected void onPostExecute(List<YdkSiparisler> ydkSiparislers) {

            for (YdkSiparisler m : ydkSiparislers) {
                if (aa) {

                    isSuccess = true;
                    m.getId();

                }
            }

        }
    }
        /*nested class pardon subclass değil. O linktekini okuyorsun canım.
        Nested Class kısaca class içinde class olması. Şimdi bu class başka bir classın içinde oldugundan sen o şekilde erişemezsin
        Nedenini ben söylemiyorum o actıgım linkte okuyorsun anlastık mı niye girdin o zaman ne niye girdin diyorum pc me dün
        kızım hatanı cozucem sımdı cozmıcem değil dedigim olay su
        bunu bir daha yapmaman ıcın o linktekini oku diyorum çözümü de çok basit zaten
        aynende öyle bekle izle dikkatlice
        *
        * */

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
                    /*siparislistesindencikar doregister = new siparislistesindencikar();
                    System.out.println(msiparsal.getId());
                    doregister.idal(msiparsal.getId(), msiparsal.getAd());
                    doregister.execute("");*/

                    new DeleteYdkSiparisler().execute();
                    
                    toplamfiyat = toplamfiyat - Double.parseDouble(msiparsal.getFiyat());
                    textView1.setText(" TOPLAM : " + toplamfiyat.toString() + " TL");
                    arr.remove(position);
                    myAdaptor adaptor = new myAdaptor(getContext(), 1, arr);
                    siparisydk.setAdapter(adaptor);
                }
            });
            return satirView;
        }
    }
}
/*public class PostSiparis extends android.os.AsyncTask<Void, Void, Siparis> {
    int masaNo;
    Date tarih;
    Time saat;


    public PostSiparis(int masaNo, Date tarih, Time saat) {
        this.masaNo = masaNo;
        this.tarih = tarih;
        this.saat = saat;
    }

    @Override
    protected Siparis doInBackground(Void... voids) {
        SiparisService m = new SiparisService();
        Siparis list = m.PostSiparis(masaNo, tarih, saat);
        return list;
    }

    @Override
    protected void onPostExecute(Siparis siparis) {
        if (siparis == null) return;
        String line = siparis.toString();
        Toast.makeText(siparis_activity.this, line, Toast.LENGTH_LONG).show();


    }
}


//    public class GetMasalar extends android.os.AsyncTask<String, Void, List<Masa>> {
//        String masaNo;
//
//        public GetMasalar(String masaNo) {
//            this.masaNo = masaNo;
//        }
//
//
//        @Override
//        protected Masa doInBackground(String... voids) {
//            MasaService m = new MasaService();
//            Masa list = m.GetMasaByMasaNo(masaNo);
//            return list;
//        }
//
//        @Override
//        protected void onPostExecute(List<Masa> masas) {
//            for (Masa m : masas) {
//                if (m.getMasano().equals(masaNo)) {
//
//                    masakapasite = "Masa : " + m.getKapasite();
//                    secilimasadurumu = m.getDurum();
//                }
//                masakkisi.setText(masakapasite);
//                masadurum.setText(secilimasadurumu);
//                textView1.setText(" TOPLAM : " + toplamfiyat.toString() + " TL");
//            }
//        }
//    }


public class PostSiparisListesi extends android.os.AsyncTask<Void, Void, SiparisListesi> {
    Siparis spId;
    int menuId;
    String urunAd;
    String urunFiyat;

    public PostSiparisListesi(Siparis spId, int menuId, String urunAd, String urunFiyat) {
        this.spId = spId;
        this.menuId = menuId;
        this.urunAd = urunAd;
        this.urunFiyat = urunFiyat;
    }

    @Override
    protected SiparisListesi doInBackground(Void... voids) {
        SiparisListesiService m = new SiparisListesiService();
        SiparisListesi list = m.PostSiparisListesi(spId, menuId, urunAd, urunFiyat);
        return list;
    }

    @Override
    protected void onPostExecute(SiparisListesi siparisListesi) {
        if (siparisListesi == null) return;
        String line = siparisListesi.toString();
        Toast.makeText(siparis_activity.this, line, Toast.LENGTH_LONG).show();


    }
}

public class DeleteYdkSiparislerByMasaNo extends android.os.AsyncTask<Void, Void, YdkSiparisler> {
    String masano;

    public DeleteYdkSiparislerByMasaNo(String masano) {
        this.masano = masano;
    }

    @Override
    protected YdkSiparisler doInBackground(Void... voids) {
        YdkSiparislerService m = new YdkSiparislerService();
        YdkSiparisler list = m.DeleteYdkSiparislerByMasaNo(masano);
        return list;
    }

    @Override
    protected void onPostExecute(YdkSiparisler ydkSiparisler) {
        if (ydkSiparisler == null) return;
        String line = ydkSiparisler.toString();
        Toast.makeText(siparis_activity.this, line, Toast.LENGTH_LONG).show();


    }
}

public class UpdateMasalar extends android.os.AsyncTask<Void, Void, Masa> {
    String masano;


    public UpdateMasalar(String masano) {
        this.masano = masano;
    }

    @Override
    protected Masa doInBackground(Void... voids) {
        MasaService m = new MasaService();
        Masa list = m.UpdateMasalar(masano);
        return list;
    }

    @Override
    protected void onPostExecute(Masa masa) {
        if (masa == null) return;
        String line = masa.toString();
        Toast.makeText(siparis_activity.this, line, Toast.LENGTH_LONG).show();


    }
}*/



  /*  public class siparislistesi extends AsyncTask<String, String, String> { //
        String z = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

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
                    String query = " select * from ydksiparisler where masano='" + glnmasano + "';"; //1
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        masaSiparisAl sal = new masaSiparisAl();
                        sal.setId(rs.getInt("id"));
                        sal.setAd(rs.getString("ad"));
                        sal.setFiyat(rs.getString("fiyat"));
                        toplamfiyat = toplamfiyat + Double.parseDouble(rs.getString("fiyat"));
                        siparsiler.add(sal);
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
                //z = "Exceptions " + ex;
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

        @Override
        protected String doInBackground(String... params) {
            toplamfiyat = 0.0;
            siparsiler.clear();
            try {
                Connection con = connectionClass.connectDatabase();
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

                    int siparsid = 0;
                    if (rs.next()) {//serviceden gelen null değilse

                        String queryurun = "SELECT * FROM menu WHERE id=" + rs.getInt("urunid") + ";";
                        Statement stmturun = con.createStatement();
                        ResultSet rsurun = stmturun.executeQuery(queryurun);
                        rsurun.next();

                        Statement stmt1 = con.createStatement();
                        stmt1.execute("INSERT INTO siparisler(masano, tarih, saat) VALUES ('" + glnmasano + "','" + tarih.format(date) + "','" + dakika.format(time) + "' );");
                        Statement stsp = con.createStatement();
                        ResultSet rssp = stsp.executeQuery("select * from siparisler where masano= '" + glnmasano + "' and tarih='" + tarih.format(date) + "' and  saat='" + dakika.format(time) + "' ");
                        rssp.next();
                        siparsid = rssp.getInt("id");
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
    }*/









       /* private class siparislistesindencikar extends AsyncTask<String, String, String> {
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
            }
        }
    }*/
//}
//}