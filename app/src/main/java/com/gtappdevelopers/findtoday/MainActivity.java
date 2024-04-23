package com.gtappdevelopers.findtoday;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewModal viewmodal;
    private static final int ADD_DESP_REQUEST = 1;
    public static final int EDIT_DESP_REQUEST = 2;
    private static final int SEARCH_DESP_REQUEST = 3;
    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewFinActivity.class);
                startActivityForResult(intent, ADD_DESP_REQUEST);
            }
        });

        FloatingActionButton fab2 = findViewById(R.id.idFABAdd2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BuscarFinActivity.class);
                startActivityForResult(intent, SEARCH_DESP_REQUEST);
            }
        });

        RecyclerView FinRV = findViewById(R.id.idRVFin);
        FinRV.setLayoutManager(new LinearLayoutManager(this));
        FinRV.setHasFixedSize(true);
        final FinRVAdapter adapter = new FinRVAdapter();
        FinRV.setAdapter(adapter);
        viewmodal = new ViewModelProvider(this).get(ViewModal.class);

        viewmodal.getallDesp().observe(this, new Observer<List<FinModal>>() {
            @Override
            public void onChanged(List<FinModal> models) {
                adapter.submitList(models);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewmodal.delete(adapter.getDespAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Registro Deletado.", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(FinRV);

        adapter.setOnItemClickListener(new FinRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(FinModal model) {
                Intent intent = new Intent(MainActivity.this, NewFinActivity.class);
                intent.putExtra(NewFinActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewFinActivity.EXTRA_VALOR_DESP, model.getValorDesp());
                intent.putExtra(NewFinActivity.EXTRA_TIPO_DESP, model.getTipoDesp());
                intent.putExtra(NewFinActivity.EXTRA_FONT_DESP, model.getFontDesp());
                intent.putExtra(NewFinActivity.EXTRA_DESCR_DESP, model.getDespDescr());
                intent.putExtra(NewFinActivity.EXTRA_DURATION, model.getDataDesp());
                startActivityForResult(intent, EDIT_DESP_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_DESP_REQUEST && resultCode == RESULT_OK) {
            String valorDesp = data.getStringExtra(NewFinActivity.EXTRA_VALOR_DESP);
            String tipoDesp = data.getStringExtra(NewFinActivity.EXTRA_TIPO_DESP);
            String despDescr = data.getStringExtra(NewFinActivity.EXTRA_DESCR_DESP);
            String fontDesp = data.getStringExtra(NewFinActivity.EXTRA_FONT_DESP);
            String dataDesp = data.getStringExtra(NewFinActivity.EXTRA_DURATION);

            FinModal model = new FinModal(valorDesp, tipoDesp, fontDesp, despDescr, dataDesp);
            viewmodal.insert(model);
            Toast.makeText(this, "Registro salvo.", Toast.LENGTH_LONG).show();

        } else if (requestCode == EDIT_DESP_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewFinActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Registro não pode ser atualizado.", Toast.LENGTH_LONG).show();
                return;
            }
            String valorDesp = data.getStringExtra(NewFinActivity.EXTRA_VALOR_DESP);
            String tipoDesp = data.getStringExtra(NewFinActivity.EXTRA_TIPO_DESP);
            String fontDesp = data.getStringExtra(NewFinActivity.EXTRA_FONT_DESP);
            String despDescr = data.getStringExtra(NewFinActivity.EXTRA_DESCR_DESP);
            String dataDesp = data.getStringExtra(NewFinActivity.EXTRA_DURATION);

            FinModal model = new FinModal(valorDesp, tipoDesp, fontDesp, despDescr, dataDesp);
            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "Registro atualizado.", Toast.LENGTH_SHORT).show();

        } else if (requestCode == SEARCH_DESP_REQUEST && resultCode == RESULT_OK) {
            // Código para lidar com a resposta da BuscarFinActivity
        } else {
            Toast.makeText(this, "Operação cancelada.", Toast.LENGTH_SHORT).show();
        }

    }


}