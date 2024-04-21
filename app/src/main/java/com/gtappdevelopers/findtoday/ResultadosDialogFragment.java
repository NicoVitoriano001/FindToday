package com.gtappdevelopers.findtoday;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.gtappdevelopers.findtoday.FinModal;

import java.util.List;

public class ResultadosDialogFragment extends DialogFragment {

    private List<FinModal> resultados;

    // Método estático para criar uma nova instância do fragmento
    public static ResultadosDialogFragment newInstance(List<FinModal> resultados) {
        ResultadosDialogFragment fragment = new ResultadosDialogFragment();
        fragment.resultados = resultados;
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Use um AlertDialog para exibir os resultados
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Resultados da Busca");

        // Crie uma string com os resultados para exibição no AlertDialog
        StringBuilder stringBuilder = new StringBuilder();
        for (FinModal modal : resultados) {
            stringBuilder.append("Valor: ").append(modal.getValorDesp()).append("\n");
            stringBuilder.append("Tipo: ").append(modal.getTipoDesp()).append("\n");
            stringBuilder.append("Fonte: ").append(modal.getFontDesp()).append("\n");
            stringBuilder.append("Descrição: ").append(modal.getDespDescr()).append("\n");
            stringBuilder.append("Data: ").append(modal.getDataDesp()).append("\n\n");
        }

        builder.setMessage(stringBuilder.toString());

        // Adicione um botão de OK ao AlertDialog
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        // Crie e retorne o AlertDialog
        return builder.create();
    }
}
