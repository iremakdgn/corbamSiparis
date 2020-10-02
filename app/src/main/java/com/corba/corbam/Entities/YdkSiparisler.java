package com.corba.corbam.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YdkSiparisler {

    @SerializedName("id")
    private Integer id;
    @SerializedName("ad")
    private String ad;
    @SerializedName("urunid")
    private Integer urunid;
    @SerializedName("fiyat")
    private String fiyat;
    @SerializedName("masano")
    private String masano;
    @SerializedName("adet")
    private int adet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public Integer getUrunid() {
        return urunid;
    }

    public void setUrunid(Integer urunid) {
        this.urunid = urunid;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public String getMasano() {
        return masano;
    }

    public void setMasano(String masano) {
        this.masano = masano;
    }
    public Integer getAdet() {
        return adet;
    }

    public void setAdet(Integer adet) {
        this.adet = adet;
    }
}

