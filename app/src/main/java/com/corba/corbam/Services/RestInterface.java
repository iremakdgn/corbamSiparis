package com.corba.corbam.Services;

import com.corba.corbam.Entities.Bildiri;
import com.corba.corbam.Entities.Kullanici;
import com.corba.corbam.Entities.Masa;
import com.corba.corbam.Entities.Menu;
import com.corba.corbam.Entities.Siparis;
import com.corba.corbam.Entities.SiparisListesi;
import com.corba.corbam.Entities.YdkSiparisler;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RestInterface {
    @GET("Masalar")
    Call<List<Masa>> getMasa();

    @GET("Masalar")
    Call<Masa> getMasaByMasaNo(@Query("masano") String masano);

    @PUT("Masalar")
    Call<Masa> UpdateMasalar(@Query("masano") String masano);

    @GET("Bildiriler")
    Call<List<Bildiri>> getBildiri();

    @GET("Kullanici/Login")
    Call<Kullanici> login(@Query("kullaniciadi") String kullaniciadi, @Query("sifre") String sifre, @Query("rol") String rol);

    @GET("Siparisler")
    Call<List<Siparis>> getSiparis();

    @POST("Siparisler")
    Call<Siparis> postSiparis(@Query("masano") String masano, @Query("tarih") DateFormat tarih, @Query("saat") DateFormat saat);

    @GET("Siparisler")
    Call<Siparis> getSiparisByMasano(@Query("masano") int masano, @Query("tarih") Date tarih, @Query("saat") Time saat);

    @GET("YdkSiparisler")
    Call<List<YdkSiparisler>> getYdkSiparisler(@Query("masano") String masano);

    @DELETE("YdkSiparisler")
    Call<YdkSiparisler> deleteYdkSiparislerByMasaNo(@Query("masano") String masano);

    @DELETE("YdkSiparisler")
    Call <List<YdkSiparisler>> deleteYdkSiparisler();


    @GET("Menu")
    Call<List<Menu>> getMenu();

    @GET("Menu")
    Call<Menu> getMenuByUrunId(@Query("urunid") int urunid);

    @GET("SiparisListesi")
    Call<List<SiparisListesi>> getSiparisListesi();

    @POST("SiparisListesi")
    Call<SiparisListesi> postSiparisListesi(@Query("spid") Siparis spid, @Query("menuid") int menuid, @Query("urunad") String urunad, @Query("urunfiyat") String urunfiyat);

    @PUT("Masa")
    Call<Masa> updateMasaDurumToPasifByMasaNo(@Query("glnmasano") String glnmasano);
}
