package com.corba.corbam.Services;

import com.corba.corbam.Entities.Masa;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class MasaService {
    static RestInterface restInterface;

    public List<Masa> GetMasalar() {
        try {
            restInterface = APIClient.getClient().create(RestInterface.class);
            Call<List<Masa>> call = restInterface.getMasa();
            List<Masa> masalar = call.execute().body();
            return masalar;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Masa UpdateMasalar(String masano) {
        try{
        restInterface = APIClient.getClient().create(RestInterface.class);
        Call<Masa> call = restInterface.UpdateMasalar(masano);
            Masa masa = call.execute().body();
            return masa;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Masa GetMasaByMasaNo(String masano) {
        try {
        restInterface = APIClient.getClient().create(RestInterface.class);
        Call<Masa> call = restInterface.getMasaByMasaNo(masano);

            Masa masa = call.execute().body();
            return masa;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void UpdateMasaDurumByMasaNo(String glnmasano, String durum) {
        try {
            restInterface = APIClient.getClient().create(RestInterface.class);
            Call<Void> call = restInterface.updateMasaDurumByMasaNo(glnmasano, durum);
            call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
