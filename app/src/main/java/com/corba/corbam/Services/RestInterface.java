package com.corba.corbam.Services;

import com.corba.corbam.Entities.Bildiri;
import com.corba.corbam.Entities.Kullanici;
import com.corba.corbam.Entities.Masa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestInterface {
    @GET("Masalar")
    Call<List<Masa>> getMasa();

    @GET("Bildiriler")
    Call<List<Bildiri>> getBildiri();

    @GET("Kullanici/Login")
    Call<Kullanici> login(@Query("kullaniciadi") String kullaniciadi, @Query("sifre") String sifre);
}
