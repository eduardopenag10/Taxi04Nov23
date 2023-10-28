package com.example.multiloginv01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectOptionAuthActivity extends AppCompatActivity {

    Toolbar MiToolbar;

    Button botonTngLogin;
    Button botonRegLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option_auth);

        MiToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(MiToolbar);
        getSupportActionBar().setTitle("Selecionar opcion");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        botonTngLogin = findViewById(R.id.btnTngLogin);
        botonTngLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });


    }

    private void goToLogin() {
        Intent intent =  new Intent(SelectOptionAuthActivity.this, LoginActivity.class);
                startActivity(intent);
    }
}