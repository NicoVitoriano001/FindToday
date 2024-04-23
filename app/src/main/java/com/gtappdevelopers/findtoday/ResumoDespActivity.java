package com.gtappdevelopers.findtoday;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ResumoDespActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_desp);

        FloatingActionButton fabReturn = findViewById(R.id.idFABreturn);
        fabReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResumoDespActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
