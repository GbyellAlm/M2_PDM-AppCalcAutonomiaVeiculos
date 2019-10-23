package com.example.a6_segundaativprogappsappcalcautonomiaautomoveis.Adaptador;

import android.content.Context;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a6_segundaativprogappsappcalcautonomiaautomoveis.Modelo.DadosAbastecimento;
import com.example.a6_segundaativprogappsappcalcautonomiaautomoveis.R;

import java.text.DateFormat;

public class DadosAbastecimentoVH extends RecyclerView.ViewHolder {
    private TextView tvTotKmVeic;
    private TextView tvTotLAbaste;
    private TextView tvDta;
    private Spinner spPostos;

    public DadosAbastecimentoVH(@NonNull View itemView) {
        super(itemView);

        tvDta = itemView.findViewById(R.id.tvData);
        tvTotLAbaste = itemView.findViewById(R.id.tvTotLAbastecidos);
        tvTotKmVeic = itemView.findViewById(R.id.tvTotKmVeiculo);
        spPostos = itemView.findViewById(R.id.spPostos);



    }

    // 1: Método q atualiza os tv de acordo c/ o objeto q esta sendo renderizado.
    public void atuaHolderCObjChegou (DadosAbastecimento DA) {

        String castDblTotLitrosAbasteEmStr = String.valueOf(DA.getLitrosAbastecidos());
        tvTotLAbaste.setText(castDblTotLitrosAbasteEmStr);

        // As próximas 6 linhas de código transformam a data em uma data legivel p/ o usu de acordo c/ a sua linguagem.
        // Esse tvTotKmVeic no .getContext, n significa "nada", q eu to pegando o contexto dele, significa q eu to usando um atributo qualquer (tvTotKmVeic) p/ acessar o contexto da aplicação p/ q eu possa obter DateFormat do device.
        DateFormat formatador = android.text.format.DateFormat.getDateFormat(tvTotKmVeic.getContext());
        // O método format vai fazer a transformação citada acima.
        //DA.getData retorna um Calendar e dele se faz um getTime p/ retornar um date.
        String dtaFormatada = formatador.format(DA.getDataAbastecimento().getTime());
        tvDta.setText(dtaFormatada);

        String castIntTotKmVeicEmStr = Integer.toString(DA.getQuilometragemAtual());
        tvTotKmVeic.setText(castIntTotKmVeicEmStr);

    }
}
