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
        restInterface = APIClient.getClient().create(RestInterface.class);
        Call<Masa> call = restInterface.UpdateMasalar(masano);
        try {
            Masa masa = call.execute().body();
            return masa;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Masa GetMasaByMasaNo(String masano) {
        restInterface = APIClient.getClient().create(RestInterface.class);
        Call<Masa> call = restInterface.getMasaByMasaNo(masano);
        try {
            Masa masa = call.execute().body();
            return masa;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Masa UpdateMasaDurumToPasifByMasaNo(String glnmasano) {
        restInterface = APIClient.getClient().create(RestInterface.class);
        Call<Masa> call = restInterface.updateMasaDurumToPasifByMasaNo(glnmasano);
        try {
            Masa masa = call.execute().body();
            return masa;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
