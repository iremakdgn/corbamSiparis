package com.corba.corbam;

public class menu {
    private int id, adet;
    private String ad,fiyat,tur;

    public menu() {
        this.id = 0;
        this.ad = null;
        this.adet=0;
        this.fiyat = null;
        this.tur = null;
    }

    public menu(int id, String ad, String fiyat, String tur) {
        this.id = id;
        this.ad = ad;
        this.fiyat = fiyat;
        this.tur = tur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public int getAdet() {return adet;}

    public void setAdet(int adet) {this.adet=adet;}

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }
}
