package com.gtappdevelopers.findtoday;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;


import java.util.List;

public class ResumoDialogFragment extends DialogFragment {
    private LiveData<List<FinModal>> data;

    public ResumoDialogFragment(LiveData<List<FinModal>> data) {
        this.data = data;
    }

    private Dao dao;
    private String option;
    private String ano;
    private String mes;

    public ResumoDialogFragment(String option, String ano, String mes) {
        this.dao = dao;
        this.option = option;
        this.ano = ano;
        this.mes = mes;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_dialog, container, false);

        // Consultar o banco de dados com base na opção, ano e mês
        LiveData<List<FinModal>> resultLiveData = dao.buscaDesp(option, option, option, option, ano + "-" + mes);
        resultLiveData.observe(getViewLifecycleOwner(), finModals -> {
            // Atualizar o layout do diálogo com as informações retornadas
            if (finModals != null && !finModals.isEmpty()) {
                FinModal result = finModals.get(0); // Vamos assumir que só haverá um resultado
                TextView valorDespTextView = view.findViewById(R.id.valorDespTextView);
                TextView tipoDespTextView = view.findViewById(R.id.tipoDespTextView);
                TextView fontDespTextView = view.findViewById(R.id.fontDespTextView);
                TextView despDescrTextView = view.findViewById(R.id.despDescrTextView);
                TextView dataDespTextView = view.findViewById(R.id.dataDespTextView);

                valorDespTextView.setText(result.getValorDesp());
                tipoDespTextView.setText(result.getTipoDesp());
                fontDespTextView.setText(result.getFontDesp());
                despDescrTextView.setText(result.getDespDescr());
                dataDespTextView.setText(result.getDataDesp());
            }
        });

        return view;
    }
}
