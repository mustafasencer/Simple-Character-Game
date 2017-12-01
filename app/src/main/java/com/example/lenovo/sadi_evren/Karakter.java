package com.example.lenovo.sadi_evren;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovo on 18.11.2017.
 */

public class Karakter implements Parcelable {
    int kilo;
    int HareketSayısı;
    int Saldırıgücü;
    String isim = "Karaktere isim verin";

    public String yemek(){
        if(HareketSayısı > 0) {
            kilo++;
            HareketSayısı--;
            return "Karakter yemek yedi ve kilosu arttı";
        }
        else
            return "Yeterli hareket yok";
    }
    public String uyumak(){
        if(HareketSayısı >0) {
            HareketSayısı--;
            return "Uyudu hıyar";
        }
        else
            return "Yeterli hareket yok";
    }
    public String savaş(){
        if(HareketSayısı >0) {
            HareketSayısı--;
            Saldırıgücü++;
            return "Karakter fena savaştı";
        }
        else
            return "Yeterli hareket yok";
    }

    public String toString(){
        return "Karakterin ismi : " + isim +
                "\nHareket Sayısı: " + HareketSayısı +
                "\nKilo: " + kilo +
                "\nSaldırı Gücü: " + Saldırıgücü;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.kilo);
        dest.writeInt(this.HareketSayısı);
        dest.writeInt(this.Saldırıgücü);
        dest.writeString(this.isim);
    }
    public Karakter(){

    }


    protected Karakter(Parcel in) {
        this.kilo = in.readInt();
        this.HareketSayısı = in.readInt();
        this.Saldırıgücü = in.readInt();
        this.isim = in.readString();
    }

    public static final Parcelable.Creator<Karakter> CREATOR = new Parcelable.Creator<Karakter>() {
        @Override
        public Karakter createFromParcel(Parcel source) {
            return new Karakter(source);
        }

        @Override
        public Karakter[] newArray(int size) {
            return new Karakter[size];
        }
    };
}
