package com.example.a6_segundaativprogappsappcalcautonomiaautomoveis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.a6_segundaativprogappsappcalcautonomiaautomoveis.Adaptador.DadosAbastecimentoAdapter;
import com.example.a6_segundaativprogappsappcalcautonomiaautomoveis.Modelo.DadosAbastecimentoDao;

import javax.annotation.Nullable;

public class RVActivity extends AppCompatActivity {

    // 1: Criar essas "variaveis".
    private DadosAbastecimentoAdapter adap;
    private RecyclerView rV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);

        // 2: Pegar o RecyclerV q ta lá no XML.
        rV = findViewById(R.id.rvDadosAbastecimento);

        // 3: Criar uma instancia do Adapter (pois ele vai dizer p/ a interface do Recycler q ele é a classe responsável por colocar objetos no Recycler).
        adap = new DadosAbastecimentoAdapter();

        // 5: Especificar como o Recycler vai exibir os objetos (qro um embaixo do outro!).
        rV.setLayoutManager(new LinearLayoutManager(this));

        // 4: Colocar o Recycler no adaptador.
        rV.setAdapter(adap);

    }

    public void adcCompro(View view) {
        Intent intencaoAbrirFormAbastecimento = new Intent(this, FormAbastecimentoActivity.class);
        startActivityForResult(intencaoAbrirFormAbastecimento, 1);
    }

    public void alterarCompro (View view, String idDadosAbastecimento) {
        Intent intencaoAbrirFormPAlterarCompro = new Intent(this, FormAbastecimentoActivity.class);
        intencaoAbrirFormPAlterarCompro.putExtra("idDadosAbastecimento", idDadosAbastecimento);
        startActivityForResult(intencaoAbrirFormPAlterarCompro, 1);
    }

    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 200) {
                int posi = data.getIntExtra("posiObjEditado", -1);
                adap.notifyItemChanged(posi);
                rV.smoothScrollToPosition(posi);
            }
        } else if (resultCode == 201) {
            Toast.makeText(this, "Abastecimento cadastrado com sucesso", Toast.LENGTH_LONG).show();
            int posicao = DadosAbastecimentoDao.obterInstancia().obterLista().size()-1;
            adap.notifyItemChanged(posicao);
            rV.smoothScrollToPosition(posicao);
        } else if (resultCode == 202) {
            Toast.makeText(this, "Compromisso excluido", Toast.LENGTH_LONG).show();
            int posicao = data.getIntExtra("PosiObjExclu", -1);
            adap.notifyItemRemoved(posicao);
        }
    }
}
