package com.onetan.carlosevalparcial;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.onetan.carlosevalparcial.databinding.ActivityMainRepartidorBinding;

public class MainActivityRepartidor extends AppCompatActivity {

    private ActivityMainRepartidorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        binding = ActivityMainRepartidorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view_re);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio_repartidor, R.id.nav_micuenta_repartidor, R.id.nav_configuracion_repartidor)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main_re);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navViewRe, navController);
    }

}
