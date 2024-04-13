package com.gtappdevelopers.findtoday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewCourseActivity extends AppCompatActivity {

    //creating a variables for our button and edittext.
    private EditText valorDespEdt, tipoDespEdt, natDespEdt, courseDescEdt, dataDespEdt;
    private Button courseBtn;

    //creating a constant string variable for our course name, description and duration.
    public static final String EXTRA_ID =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";

    public static final String EXTRA_VALOR_DESP =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_VALOR_DESP";

    public static final String EXTRA_TIPO_DESP =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_TIPO_DESP";

    public static final String EXTRA_NAT_DESP =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_NAT_DESP";

    public static final String EXTRA_DESCR_DESP =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_DESCRIPTION";
    public static final String EXTRA_DURATION =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_DURATION";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);
        //initializing our variables for each view.
        valorDespEdt = findViewById(R.id.idEdtValorDesp);
        tipoDespEdt = findViewById(R.id.idEdtTipoDesp);
        natDespEdt = findViewById(R.id.idEdtNatDesp);
        courseDescEdt = findViewById(R.id.idEdtDespDescr);
        dataDespEdt = findViewById(R.id.idEdtDataDesp);
        courseBtn = findViewById(R.id.idBtnSaveCourse);
        //below line is to get intent as we are getting data via an intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            //if we get id for our data then we are setting values to our edit text fields.
            valorDespEdt.setText(intent.getStringExtra(String.valueOf(EXTRA_VALOR_DESP)));
            tipoDespEdt.setText(intent.getStringExtra(EXTRA_TIPO_DESP));
            natDespEdt.setText(intent.getStringExtra(EXTRA_NAT_DESP));
            courseDescEdt.setText(intent.getStringExtra(EXTRA_DESCR_DESP));
            dataDespEdt.setText(intent.getStringExtra(EXTRA_DURATION));
        }
        //adding on click listner for our save button.
        courseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting text value from edittext and validating if the text fields are empty or not.
                float valorDesp = Float.parseFloat(valorDespEdt.getText().toString());
             // float valorDesp = valorDespEdt.getText().toString();
                String tipoDesp = tipoDespEdt.getText().toString();
                String natDesp = natDespEdt.getText().toString();

                String courseDesc = courseDescEdt.getText().toString();
                String dataDesp = dataDespEdt.getText().toString();
                if (String.valueOf(valorDesp).isEmpty() || tipoDesp.isEmpty() || courseDesc.isEmpty() || dataDesp.isEmpty()) {
                    Toast.makeText(NewCourseActivity.this, "Please enter the valid course details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                //calling a method to save our course.
                saveCourse(valorDesp, tipoDesp, natDesp, courseDesc, dataDesp);
            }
        });

    }

    private void saveCourse(float valorDesp, String tipoDesp, String natDesp, String despDescr, String dataDesp) {
        //inside this method we are passing all the data via an intent.
        Intent data = new Intent();
        //in below line we are passing all our course detail.
        data.putExtra(String.valueOf(EXTRA_VALOR_DESP), valorDesp);
        data.putExtra(EXTRA_TIPO_DESP, tipoDesp);
        data.putExtra(EXTRA_NAT_DESP, natDesp);
        data.putExtra(EXTRA_DESCR_DESP, despDescr);
        data.putExtra(EXTRA_DURATION, dataDesp);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            //in below line we are passing our id.
            data.putExtra(EXTRA_ID, id);
        }
        //at last we are setting result as data.
        setResult(RESULT_OK, data);
        //displaying a toast message after adding the data
        Toast.makeText(this, "Course has been saved to Room Database. ", Toast.LENGTH_SHORT).show();
    }

}