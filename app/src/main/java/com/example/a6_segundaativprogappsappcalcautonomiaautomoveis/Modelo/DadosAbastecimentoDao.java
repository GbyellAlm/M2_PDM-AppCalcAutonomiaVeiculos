package com.example.a6_segundaativprogappsappcalcautonomiaautomoveis.Modelo;

import java.util.ArrayList;
import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmResults;

/*public class DadosAbastecimentoDao {
    // 5
    private ArrayList<DadosAbastecimento> bancoDados;
    // 6
    public ArrayList<DadosAbastecimento> obterLista() { return bancoDados; }
    // 1
    private static DadosAbastecimentoDao INSTANCIA;
    // 2
    public static DadosAbastecimentoDao obterInstancia(){
        // 3
        if (INSTANCIA == null) {
            INSTANCIA = new DadosAbastecimentoDao();
        }
        return INSTANCIA;
    }
    // 4
    private DadosAbastecimentoDao() {
        // 7
        // Garantir q o ArrayList(bancoDados) nunca fique null p/ q se tenha como retorno do msm um ArrayList vazio e não um objeto null.
        bancoDados = new ArrayList<DadosAbastecimento>();

        // Dados p/ teste:
        for (int i = 1; i <= 2; i++){
            DadosAbastecimento DA = new DadosAbastecimento();
            DA.setQuilometragemAtual(34210);
            DA.setLitrosAbastecidos(25.10);
            DA.setDataAbastecimento(Calendar.getInstance());
            bancoDados.add(DA);
        }
    }
}*/

public class DadosAbastecimentoDao {
    private ArrayList<DadosAbastecimento> bancoDados;

    public ArrayList<DadosAbastecimento> obterLista() {
        Realm db = Realm.getDefaultInstance();
        RealmResults lista = db.where(DadosAbastecimento.class).findAll();

        bancoDados.clear();
        bancoDados.addAll(db.copyFromRealm(lista));
        return bancoDados;
    }

    public void adcNaLista (DadosAbastecimento DA) {
        Realm db = Realm.getDefaultInstance();
        db.beginTransaction();
        db.copyToRealm(DA);
        db.commitTransaction();
    }

    public int atualizaNaLista (DadosAbastecimento DA){
        Realm db = Realm.getDefaultInstance();
        db.beginTransaction();
        // VER NA PRATICA DIFERENÇA ENTRE ESSE E O COPYTOREALM!! R: Os 2 fazem a msm coisa (inserir no bd) as difs é q o copy retorna um proxy gerenciado e o insert n, e, o insert ira inserir msm n existindo chave primária.
        db.copyToRealm(DA);
        db.commitTransaction();

        for (int i = 0; i < bancoDados.size(); i++) {
            if (bancoDados.get(i).getId().equals(DA.getId())) {
                bancoDados.set (i, DA);
                return i;
            }
        }
        return -1; // Significa q n encontrou nda p/ atualizar
    }

    public int excluDaLista (DadosAbastecimento DA) {
        Realm db = Realm.getDefaultInstance();
        db.beginTransaction();
        db.where(DadosAbastecimento.class).equalTo("id", DA.getId()).findFirst().deleteFromRealm();
        db.commitTransaction();

        for(int i = 0; i < bancoDados.size(); i++){
            if(bancoDados.get(i).getId().equals(DA.getId())){
                bancoDados.remove(i);
                return i;
            }
        }
        return -1; // Se n encontrou nda p/ excluir.
    }

    public DadosAbastecimento obterObjPeloId (String id) {
        Realm db = Realm.getDefaultInstance();
        db.beginTransaction();
        DadosAbastecimento DA = db.copyFromRealm(db.where(DadosAbastecimento.class).equalTo("id", id).findFirst());
        db.commitTransaction();
        return DA;
    }

    private static DadosAbastecimentoDao INSTANCIA;

    public static DadosAbastecimentoDao obterInstancia(){

        if (INSTANCIA == null) {
            INSTANCIA = new DadosAbastecimentoDao();
        }
        return INSTANCIA;
    }

    private DadosAbastecimentoDao() {
        // Garantir q o ArrayList(bancoDados) nunca fique null p/ q se tenha como retorno do msm um ArrayList vazio e não um objeto null.
        bancoDados = new ArrayList<DadosAbastecimento>();
    }
}


