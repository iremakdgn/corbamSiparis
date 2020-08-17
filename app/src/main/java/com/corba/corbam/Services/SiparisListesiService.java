package com.corba.corbam.Services;

import com.corba.corbam.Entities.Siparis;
import com.corba.corbam.Entities.SiparisListesi;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class SiparisListesiService {
    static RestInterface restInterface;
    public List<SiparisListesi> GetSiparisListesi() {
        restInterface = APIClient.getClient().create(RestInterface.class);
        Call<List<SiparisListesi>> call = restInterface.getSiparisListesi();
        try {
            List<SiparisListesi> siparisListesi = call.execute().body();
            return siparisListesi;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public SiparisListesi PostSiparisListesi(Siparis spid, int menuid, String urunad, String urunfiyat) {
        restInterface = APIClient.getClient().create(RestInterface.class);
        Call<SiparisListesi> call = restInterface.postSiparisListesi(spid, menuid, urunad, urunfiyat);
        try {
            SiparisListesi siparisListesi = call.execute().body();
            return siparisListesi;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}