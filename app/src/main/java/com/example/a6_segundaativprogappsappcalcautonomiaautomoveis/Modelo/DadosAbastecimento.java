package com.example.a6_segundaativprogappsappcalcautonomiaautomoveis.Modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class DadosAbastecimento extends RealmObject {

    @PrimaryKey // Tem q definir ql a chave primária! a chave primária é o id.
    private String id;
    private int quilometragemAtual;
    private Double litrosAbastecidos;

    @Ignore // Ignorar esse atributo pq não qro guardar ele no banco. N qro guardar ele no banco pq o Realm n tem data do tipo Calendar - tem q criar uma data do tipo Date, isto é, fazer uma ganbiarrinha.
    private Calendar dataAbastecimento;

    private Date dtaQRealmSuporta;
    private String posto;

    public DadosAbastecimento() {id = UUID.randomUUID().toString();
    }

    public String getId() {return id;}

    public int getQuilometragemAtual() {
        return quilometragemAtual;
    }

    public void setQuilometragemAtual(int quilometragemAtual) {
        this.quilometragemAtual = quilometragemAtual;
    }

    public Double getLitrosAbastecidos() {
        return litrosAbastecidos;
    }

    public void setLitrosAbastecidos(Double litrosAbastecidos) {
        this.litrosAbastecidos = litrosAbastecidos;
    }

    public Calendar getDataAbastecimento() {
        if (dtaQRealmSuporta != null) {
            dataAbastecimento = Calendar.getInstance();
            dataAbastecimento.setTime(dtaQRealmSuporta);
        }
        return dataAbastecimento;
    }

    public void setDataAbastecimento(Calendar dataAbastecimento) {
        // getTime() transforma um Calendar p/ date e retorna um Date.
        this.dtaQRealmSuporta = dataAbastecimento.getTime();
    }

    public String getPosto() {
        return posto;
    }

    public void setPosto(String posto) {
        this.posto = posto;
    }
}
