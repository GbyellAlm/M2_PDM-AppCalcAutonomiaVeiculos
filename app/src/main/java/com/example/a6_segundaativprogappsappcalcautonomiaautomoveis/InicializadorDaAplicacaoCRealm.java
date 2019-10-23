package com.example.a6_segundaativprogappsappcalcautonomiaautomoveis;

import android.app.Application;

import io.realm.Realm;

public class InicializadorDaAplicacaoCRealm extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
