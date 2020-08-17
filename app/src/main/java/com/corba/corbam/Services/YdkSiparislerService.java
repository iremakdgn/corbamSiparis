package com.corba.corbam.Services;

import com.corba.corbam.Entities.Kullanici;
import com.corba.corbam.Entities.Siparis;
import com.corba.corbam.Entities.YdkSiparisler;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class YdkSiparislerService {
    static RestInterface restInterface;

    public List<YdkSiparisler> GetYdkSiparisler(String masano) {
        restInterface = APIClient.getClient().create(RestInterface.class);
        try {
            Call<List<YdkSiparisler>> call = restInterface.getYdkSiparisler(masano);
            List<YdkSiparisler> ydkSiparisler = call.execute().body();
            return ydkSiparisler;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public YdkSiparisler DeleteYdkSiparislerByMasaNo(String masano) {
        restInterface = APIClient.getClient().create(RestInterface.class);
        Call<YdkSiparisler> call = restInterface.deleteYdkSiparislerByMasaNo(masano);
        try {
            YdkSiparisler ydkSiparisler = call.execute().body();
            return ydkSiparisler;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<YdkSiparisler> DeleteYdkSiparisler() {
        restInterface = APIClient.getClient().create(RestInterface.class);
        Call<List<YdkSiparisler>> call = restInterface.deleteYdkSiparisler();
        try {
            List<YdkSiparisler> ydkSiparisler = call.execute().body();
            return ydkSiparisler;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
