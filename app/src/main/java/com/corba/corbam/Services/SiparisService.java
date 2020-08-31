package com.corba.corbam.Services;

import com.corba.corbam.Entities.Siparis;
import com.corba.corbam.Entities.YdkSiparisler;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;

public class SiparisService {
    static RestInterface restInterface;

    public List<Siparis> GetSiparis() {
        try {
            restInterface = APIClient.getClient().create(RestInterface.class);
            Call<List<Siparis>> call = restInterface.getSiparis();
            List<Siparis> siparisler = call.execute().body();
            return siparisler;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Siparis GetSiparisByMasano(int masano, Date tarih, Time saat) {
        try {
            restInterface = APIClient.getClient().create(RestInterface.class);
            Call<Siparis> call = restInterface.getSiparisByMasano(masano, tarih, saat);
            Siparis siparisler = call.execute().body();
            return siparisler;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int PostSiparisler(Siparis siparis) {
        try {
            restInterface = APIClient.getClient().create(RestInterface.class);
            Call<Integer> call = restInterface.postSiparisler(siparis); //int değil Integer oldu. Çünkü int sınıf değildir! Integer sınıftır. Burası da Sınıf İstiyor! Geriye döndürülen değer için!
            return call.execute().body();//Eklenen kaydın id sini geriye dönderdik!
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}