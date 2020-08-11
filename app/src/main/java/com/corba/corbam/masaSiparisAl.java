package com.corba.corbam;

public class masaSiparisAl {
    private int id,masano;
    private String ad,adet,fiyat,po_gram,tur;

    public masaSiparisAl() {
        this.id = 0;
        this.masano = 0;
        this.ad = null;
        this.adet = null;
        this.fiyat = null;
        this.po_gram = null;
        this.tur = null;
    }

    public masaSiparisAl(int id, String ad, String fiyat) {
        this.id = id;
        this.ad = ad;
        this.fiyat = fiyat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMasano() {
        return masano;
    }

    public void setMasano(int masano) {
        this.masano = masano;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAdet() {
        return adet;
    }

    public void setAdet(String adet) {
        this.adet = adet;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public String getPo_gram() {
        return po_gram;
    }

    public void setPo_gram(String po_gram) {
        this.po_gram = po_gram;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }
}
