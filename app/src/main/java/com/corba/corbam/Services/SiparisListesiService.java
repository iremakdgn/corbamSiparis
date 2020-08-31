package com.corba.corbam.Services;

import com.corba.corbam.Entities.Siparis;
import com.corba.corbam.Entities.SiparisListesi;
import com.corba.corbam.Entities.YdkSiparisler;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class SiparisListesiService {
    static RestInterface restInterface;
    public List<SiparisListesi> GetSiparisListesi() {
        try {
        restInterface = APIClient.getClient().create(RestInterface.class);
        Call<List<SiparisListesi>> call = restInterface.getSiparisListesi();
            List<SiparisListesi> siparisListesi = call.execute().body();
            return siparisListesi;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void PostSiparisListesi(SiparisListesi siparisListesi) {
        try {
            restInterface = APIClient.getClient().create(RestInterface.class);
            Call<Void> call = restInterface.postSiparisListesi(siparisListesi);
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
