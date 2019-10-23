package com.example.a6_segundaativprogappsappcalcautonomiaautomoveis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnAbastecimentos(View view) {
        Intent intencaoAbrirListaAbastecimentos = new Intent(this, RVActivity.class);
        startActivity(intencaoAbrirListaAbastecimentos);
    }

}
