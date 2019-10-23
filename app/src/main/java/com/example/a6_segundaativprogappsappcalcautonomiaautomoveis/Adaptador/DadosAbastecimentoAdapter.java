package com.example.a6_segundaativprogappsappcalcautonomiaautomoveis.Adaptador;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a6_segundaativprogappsappcalcautonomiaautomoveis.Modelo.DadosAbastecimento;
import com.example.a6_segundaativprogappsappcalcautonomiaautomoveis.Modelo.DadosAbastecimentoDao;
import com.example.a6_segundaativprogappsappcalcautonomiaautomoveis.R;

public class DadosAbastecimentoAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    //OnCreate: Cria uma gaveta.
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 1: Inflar XML.
        ConstraintLayout elementoPrinciXML = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(
                // parent: É smp necessário qdo um XML vai ser inflado, pois, o LayoutInflater na hr q vai inflar um XML, ele precisa calcular a dimensão q os objetos vão ter. Isso é smp de acordo c/ o pai.
                // false: Inflar e já acoplar o filho no pai. (a gnt faz isso manualmente pois se por true, ele vai fazer isso 2x e vai crashar).
                R.layout.gavetinhas, parent, false
        );
        // 2: Associar a um novo VH (VH: controla a atualização dos itens de acordo c/ o objeto q ta sendo renderizado).
        DadosAbastecimentoVH gaveta = new DadosAbastecimentoVH(elementoPrinciXML);
        // 3: Retornar o VH criado.
        return gaveta;
    }

    @Override
    //OnBind: Coloca um objeto dentro de uma gaveta.
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Objeto "dados do abastecimento" q devem ser associados a gaveta.
        DadosAbastecimento DA = DadosAbastecimentoDao.obterInstancia().obterLista().get(position);
        // A gaveta.
        DadosAbastecimentoVH gaveta = (DadosAbastecimentoVH) holder;

        gaveta.atuaHolderCObjChegou(DA);
    }

    @Override
    // Nro de itens q tem no Recycler View.
    public int getItemCount() {
        return DadosAbastecimentoDao.obterInstancia().obterLista().size();
    }
}
