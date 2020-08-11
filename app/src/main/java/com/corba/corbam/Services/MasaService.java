package com.corba.corbam.Services;

import com.corba.corbam.Entities.Masa;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class MasaService {
    static RestInterface restInterface;
    public List<Masa> GetMasalar() {
        restInterface = APIClient.getClient().create(RestInterface.class);
        Call<List<Masa>> call = restInterface.getMasa();
        try {
            List<Masa> masalar = call.execute().body();
            return masalar;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
