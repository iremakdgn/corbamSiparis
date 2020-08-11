package com.corba.corbam.Services;

import com.corba.corbam.Entities.Kullanici;
import com.corba.corbam.Entities.Masa;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class KullaniciService {
    static RestInterface restInterface;

    public Kullanici Login(String kullaniciAdi, String sifre) {
        restInterface = APIClient.getClient().create(RestInterface.class);
        Call<Kullanici> call = restInterface.login(kullaniciAdi, sifre);
        try {
            Kullanici kullanici = call.execute().body();
            return kullanici;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
