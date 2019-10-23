package com.example.a6_segundaativprogappsappcalcautonomiaautomoveis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.example.a6_segundaativprogappsappcalcautonomiaautomoveis.Modelo.DadosAbastecimento;
import com.example.a6_segundaativprogappsappcalcautonomiaautomoveis.Modelo.DadosAbastecimentoDao;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.util.Calendar;

import fr.ganfra.materialspinner.MaterialSpinner;

public class FormAbastecimentoActivity extends AppCompatActivity {

    private DadosAbastecimento objDadosAbaste;
    private String idDadosAbaste;
    private TextInputEditText etKMAtuaVeic;
    private TextInputEditText etLAbastecidos;
    private TextInputEditText etDtaAbas;
    private MaterialSpinner spPostos;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_abastecimento);

        etKMAtuaVeic = findViewById(R.id.etKMAtuaVeic);
        etLAbastecidos = findViewById(R.id.etLAbastecidos);
        etDtaAbas = findViewById(R.id.etDtaAbastecimento);
        etDtaAbas.setKeyListener(null);
        spPostos = findViewById(R.id.spPostos);

        String[] opcoesPostos = getResources().getStringArray(R.array.opcoes_postos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcoesPostos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPostos.setAdapter(adapter);

        idDadosAbaste = getIntent().getStringExtra("idDadosAbastecimento");
        if (idDadosAbaste == null) {
            objDadosAbaste = new DadosAbastecimento();

            Button btnExcluDadosAbaste = findViewById(R.id.btnExcluirDadosAbastecimento);
            btnExcluDadosAbaste.setVisibility(View.INVISIBLE);
        } else {
            objDadosAbaste = DadosAbastecimentoDao.obterInstancia().obterObjPeloId(idDadosAbaste);

            etKMAtuaVeic.setText(objDadosAbaste.getQuilometragemAtual());
            etLAbastecidos.setText(String.valueOf(objDadosAbaste.getLitrosAbastecidos()));
            DateFormat formatadorDta = android.text.format.DateFormat.getDateFormat(getApplicationContext());
            String dtaFormatadaSele = formatadorDta.format(objDadosAbaste.getDataAbastecimento().getTime());
            etDtaAbas.setText(dtaFormatadaSele);

            for (int i = 0; i < spPostos.getAdapter().getCount(); i++) {
                if (spPostos.getAdapter().getItem(i).toString() == objDadosAbaste.getPosto()) {
                    spPostos.setSelection(i+1);
                    break;
                }
            }
        }
    }

    public void salvarAbastecimento (View view) {

        objDadosAbaste.setQuilometragemAtual(Integer.parseInt(etKMAtuaVeic.getText().toString()));
        objDadosAbaste.setLitrosAbastecidos(Double.parseDouble(etLAbastecidos.getText().toString()));
        objDadosAbaste.setPosto(spPostos.getSelectedItem().toString());

        if (idDadosAbaste == null) {
            // salvando
            DadosAbastecimentoDao.obterInstancia().adcNaLista(objDadosAbaste);
            setResult(201);
        } else {
            // editando
            int posicaoObj = DadosAbastecimentoDao.obterInstancia().atualizaNaLista(objDadosAbaste);
            Intent intencaoFecharActivityForm = new Intent();
            intencaoFecharActivityForm.putExtra("posiObjEditado", posicaoObj);
            setResult(200, intencaoFecharActivityForm);
        }
        finish();
    }

    public void selecionarDta (View view) {
        Calendar dtaPadrao = objDadosAbaste.getDataAbastecimento();
        if (dtaPadrao == null) {
            dtaPadrao = Calendar.getInstance();
        }

        DatePickerDialog dialogoPPegarData = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    // onDateSet é um método chamado qdo a pessoa seleciona uma data
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar dtaSelecio = Calendar.getInstance();
                        dtaSelecio.set(year, month, dayOfMonth);
                        objDadosAbaste.setDataAbastecimento(dtaSelecio);
                        // o DateFormat formata datas de acordo c/ a localização da pessoa.
                        DateFormat formatador = android.text.format.DateFormat.getDateFormat(getApplicationContext());
                        String dtaSelecioFormatada = formatador.format(dtaSelecio.getTime());
                        etDtaAbas.setText(dtaSelecioFormatada);
                    }
                },
                dtaPadrao.get(Calendar.YEAR),
                dtaPadrao.get(Calendar.MONTH),
                dtaPadrao.get(Calendar.DAY_OF_MONTH)

        );
    dialogoPPegarData.show();
    }

    public void excluir (View view) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmação")
                .setMessage("Confirma a exclusão dos dados desse abastecimento?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int posiObj = DadosAbastecimentoDao.obterInstancia().excluDaLista(objDadosAbaste);
                        Intent intencaoFecharActiForm = new Intent();
                        intencaoFecharActiForm.putExtra("posiObjExcluido", posiObj);
                        setResult(202, intencaoFecharActiForm);

                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }
}

