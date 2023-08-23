package com.onetan.carlosevalparcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {


    TextView txtfooter;
    ImageView imgLogo;
    String tipousuario= "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
////para que no aparesca la barra de accion
        getSupportActionBar().hide();

        imgLogo = findViewById(R.id.SplashLogo);
        txtfooter = findViewById(R.id.txtFooter);

        Animation animacion1 = AnimationUtils.loadAnimation(this,R.anim.desplazamineto_arriba);
        Animation animacion2= AnimationUtils.loadAnimation(this,R.anim.desplazamiento_abajo);

        imgLogo.setAnimation(animacion2);
        txtfooter.setAnimation(animacion1);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this,Loginpaso1.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("claveusuario",tipousuario);
                startActivity(i);
            }
        },3000);
    }
}