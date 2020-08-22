package com.corba.corbam.Services;

import com.corba.corbam.Entities.Siparis;
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
        Call<Siparis> call = restInterface.getSiparisByMasano(masano,tarih,saat);
            Siparis siparisler = call.execute().body();
            return siparisler;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Siparis PostSiparis(String masano, DateFormat tarih, DateFormat saat) {
        try {
        restInterface = APIClient.getClient().create(RestInterface.class);
        Call<Siparis> call = restInterface.postSiparis(masano, tarih, saat);
        Siparis siparisler = call.execute().body();
            return siparisler;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}