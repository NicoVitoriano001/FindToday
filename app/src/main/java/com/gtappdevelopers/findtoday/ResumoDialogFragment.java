package com.gtappdevelopers.findtoday;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ResumoDialogFragment extends DialogFragment {
    private final LiveData<List<FinModal>> data;
    public ResumoDialogFragment(LiveData<List<FinModal>> data) {
        this.data = data;
    }
    private Dao dao;
    private String option;
    private String ano;
    private String mes;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar o layout do DialogFragment
        View view = inflater.inflate(R.layout.dialog_fragment_layout, container, false);

        // Criar um novo ViewGroup para atuar como contêiner dos itens da lista
        ViewGroup itemListContainer = view.findViewById(R.id.item_list_container);

        // Use o LiveData passado para o construtor
        data.observe(getViewLifecycleOwner(), finModals -> {
            // Atualize a interface com os dados
            if (finModals != null && !finModals.isEmpty()) {
                for (FinModal result : finModals) {
                    // Inflar o layout do item da lista
                    View itemView = inflater.inflate(R.layout.item_list_layout_fragment, itemListContainer, false);

                    // Encontrar os TextViews dentro do itemView
                    TextView valorDespTextView = itemView.findViewById(R.id.valorDespTextView);
                    TextView tipoDespTextView = itemView.findViewById(R.id.tipoDespTextView);
                    TextView fontDespTextView = itemView.findViewById(R.id.fontDespTextView);
                    TextView despDescrTextView = itemView.findViewById(R.id.despDescrTextView);
                    TextView dataDespTextView = itemView.findViewById(R.id.dataDespTextView);

                    // Definir os valores nos TextViews
                    valorDespTextView.setText(String.valueOf(result.getValorDesp()));
                    tipoDespTextView.setText(result.getTipoDesp());
                    fontDespTextView.setText(result.getFontDesp());
                    despDescrTextView.setText(result.getDespDescr());
                    dataDespTextView.setText(result.getDataDesp());

                    // Adicionar o itemView ao contêiner
                    itemListContainer.addView(itemView);
                }
            }
        });

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getDialog() != null && getDialog().getWindow() != null) {
            int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.99);
            getDialog().getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

}
