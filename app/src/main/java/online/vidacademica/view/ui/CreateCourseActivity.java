package online.vidacademica.view.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityCreateCourseBinding;

public class CreateCourseActivity extends AppCompatActivity {

    private ActivityCreateCourseBinding binding;

    private String[] list_status_course = new String[]{"Ativo","Inativo"};
    private Spinner sp;
    private Button btnSaveCourse;
    private String status_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);


        // Populando Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,list_status_course);

        sp = (Spinner) findViewById(R.id.spinner_status_course);
        sp.setAdapter(adapter);


        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        status_course = "Ativo";
                        break;
                    case 2:
                        status_course = "Inativo";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });


//        Alert para levar usuario para a tela de cadastro de materia
            btnSaveCourse = (Button) findViewById(R.id.btn_save_course);
            btnSaveCourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder  alert = new AlertDialog.Builder(CreateCourseActivity.this);
                    alert.setTitle("Aviso");
                    alert
                            .setIcon(R.drawable.ic_info_black_24dp)
                            .setMessage("Deseja cadastrar uma matéria?")
                            .setCancelable(false)
                            .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(getApplicationContext(),"Cancelar escolhido",Toast.LENGTH_LONG).show();
                                }
                            })
                            .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(CreateCourseActivity.this, CreateSubjectActivity.class));
                                }
                            });

                    AlertDialog alertDialog = alert.create();
                    alertDialog.show();


                }
            });

    }

}
