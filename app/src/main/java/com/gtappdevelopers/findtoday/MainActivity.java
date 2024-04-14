package com.gtappdevelopers.findtoday;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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

    //creating a variables for our recycler view.
    private RecyclerView FinRV;
    private static final int ADD_DESP_REQUEST = 1;
    private static final int EDIT_DESP_REQUEST = 2;
    private ViewModal viewmodal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing our variable for our recycler view and fab.
        FinRV = findViewById(R.id.idRVCourses);

        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        //adding on click listner for floating action button.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting a new activity for adding a new course and passing a constant value in it.
                Intent intent = new Intent(MainActivity.this, NewFinActivity.class);
                startActivityForResult(intent, ADD_DESP_REQUEST);
            }
        });

        //setting layout manager to our adapter class.
        FinRV.setLayoutManager(new LinearLayoutManager(this));
        FinRV.setHasFixedSize(true);
        //initializing adapter for recycler view.
        final FinRVAdapter adapter = new FinRVAdapter();
        //setting adapter class for recycler view.
        FinRV.setAdapter(adapter);
        //passing a data from view modal.
        viewmodal = ViewModelProviders.of(this).get(ViewModal.class);
        //below line is use to get all the courses from view modal.
        viewmodal.getallDesp().observe(this, new Observer<List<FinModal>>() {
            @Override
            public void onChanged(List<FinModal> models) {
                //when the data is changed in our models we are adding that list to our adapter class.
                adapter.submitList(models);
            }
        });//fim pega todas desp


        //below method is use to add swipe to delete method for item of recycler view.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //on recycler view item swiped then we are deleting the item of our recycler view.
                viewmodal.delete(adapter.getDespAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Registro Deletado.", Toast.LENGTH_LONG).show();
            }
        }).//deleta pelo movimento


        //below line is use to attact this to recycler view.
        attachToRecyclerView(FinRV);


        //below line is use to set item click listner for our item of recycler view.
        //clicar na tela main RecyclerView, abrea qui
        adapter.setOnItemClickListener(new FinRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(FinModal model) {
                //after clicking on item of recycler view
                //we are opening a new activity and passing a data to our activity.
                Intent intent = new Intent(MainActivity.this, NewFinActivity.class);
                intent.putExtra(NewFinActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewFinActivity.EXTRA_VALOR_DESP, model.getValorDesp());
                intent.putExtra(NewFinActivity.EXTRA_TIPO_DESP, model.getTipoDesp());
                intent.putExtra(NewFinActivity.EXTRA_NAT_DESP, model.getFontDesp());
                intent.putExtra(NewFinActivity.EXTRA_DESCR_DESP, model.getDespDescr());
                intent.putExtra(NewFinActivity.EXTRA_DURATION, model.getDataDesp());
                //below line is to start a new activity and adding a edit course constant.
                startActivityForResult(intent, EDIT_DESP_REQUEST);
            }
        });

    }//fim onCreate, principal do botao flutuante

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_DESP_REQUEST && resultCode == RESULT_OK) {
            float  valorDEsp = data.getFloatExtra(NewFinActivity.EXTRA_VALOR_DESP ,1.00f);//ESTAVA ,-1   FICAVA COMO VALOR
            String tipoDesp = data.getStringExtra(NewFinActivity.EXTRA_TIPO_DESP);
            String despDescr = data.getStringExtra(NewFinActivity.EXTRA_DESCR_DESP);
            String fontDesp = data.getStringExtra(NewFinActivity.EXTRA_NAT_DESP);
            String dataDesp = data.getStringExtra(NewFinActivity.EXTRA_DURATION);
            FinModal model = new FinModal(valorDEsp, tipoDesp, fontDesp, despDescr, dataDesp);
            viewmodal.insert(model);
            Toast.makeText(this, "Registro salvo.", Toast.LENGTH_LONG).show();

        } else if (requestCode == EDIT_DESP_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewFinActivity.EXTRA_ID, -1); //NOVO 1-, SE NAO, RETORNA VALOR DO ID
            if (id == -1) {
                Toast.makeText(this, "Registro não pode ser atualizado.", Toast.LENGTH_LONG).show();
                return;
            }
            float valorDesp = data.getFloatExtra(NewFinActivity.EXTRA_VALOR_DESP,1.00f);
            String tipoDesp = data.getStringExtra(NewFinActivity.EXTRA_TIPO_DESP);
            String fontDesp = data.getStringExtra(NewFinActivity.EXTRA_NAT_DESP);
            String despDescr = data.getStringExtra(NewFinActivity.EXTRA_DESCR_DESP);
            String dataDesp = data.getStringExtra(NewFinActivity.EXTRA_DURATION);
            FinModal model = new FinModal(valorDesp, tipoDesp, fontDesp, despDescr, dataDesp);
            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "Registro salvo.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Registro não salvo.", Toast.LENGTH_LONG).show();
        }

    }
}