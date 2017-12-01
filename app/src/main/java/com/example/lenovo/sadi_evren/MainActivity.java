package com.example.lenovo.sadi_evren;

import android.app.Activity;
import android.content.Context;
import android.drm.DrmStore;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, EditText.OnEditorActionListener{
    TextView tv;
    TextView tvKarakterÖzellikleri;
    Button bSaldır;
    Button bYemek;
    Button bUyu;
    Karakter k;
    EditText isim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvKarakterÖzellikleri = (TextView) findViewById(R.id.Bilmece);
        isim = (EditText) findViewById(R.id.edittext);

        if(savedInstanceState != null){
            k = savedInstanceState.getParcelable("karakter");
            isim.setVisibility(View.INVISIBLE);
            tvKarakterÖzellikleri.setText(k.toString());
            tvKarakterÖzellikleri.setVisibility(View.VISIBLE);
        }
        else{
            k = new Karakter();
            k.Saldırıgücü = 100;
            k.kilo = 10;
            k.HareketSayısı = 10;

        }

        // Textlerin hangi id den içerik alacaını belirten code;
        tv = (TextView) findViewById(R.id.Cevap);

        // Butonların hangi id den içerik alacağını belirten kod;
        bSaldır = (Button) findViewById(R.id.saldır);
        bUyu = (Button) findViewById(R.id.uyu);
        bYemek = (Button) findViewById(R.id.yemek);

        //Click işlemi gerçekleştiğinde ne olacak;
        bSaldır.setOnClickListener(this);
        bYemek.setOnClickListener(this);
        bUyu.setOnClickListener(this);

        //EditText işlemi gerçekleştiğinde ne olacak;
        isim.setOnEditorActionListener(this);

        //Karakter class ından gereken bilgileri topladık;

        //TextView alanlarına default bir text atıyoruz;
        tv.setText("Oyuna Hoşgeldiniz lütfen bir eylem seçin.");

    }
    @Override
    public boolean onEditorAction(TextView tv2, int x, KeyEvent event){

        if(x == EditorInfo.IME_ACTION_DONE | event.getAction() == KeyEvent.ACTION_DOWN){
            tv.setText("Kullanıcı ismi: " + isim.getText() + " olarak atandı.");
            k.isim = isim.getText().toString();
            isim.setVisibility(View.INVISIBLE);
            tvKarakterÖzellikleri.setText(k.toString());
            tvKarakterÖzellikleri.setVisibility(View.VISIBLE);
            InputMethodManager imm = (InputMethodManager)tv2.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(tv2.getWindowToken(), 0);
            return true;
        }
        return false;
    }
    @Override
    public void onClick(View view){

        if(view.getId() == bSaldır.getId())
            tv.setText(k.savaş());
        else if(view.getId() == bYemek.getId())
            tv.setText(k.yemek());
        else if(view.getId() == bUyu.getId())
            tv.setText(k.uyumak());
        tvKarakterÖzellikleri.setText(k.toString());

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putString("anahtar", "deger");
        outState.putParcelable("karakter", k);
    }


}
