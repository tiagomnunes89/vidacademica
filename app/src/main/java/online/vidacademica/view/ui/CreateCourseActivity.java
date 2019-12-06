package online.vidacademica.view.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityCreateCourseBinding;
import online.vidacademica.viewmodel.CourseViewModel;

public class CreateCourseActivity extends BaseActivity {
    private static final String TAG = CreateCourseActivity.class.getSimpleName();

    private CourseViewModel courseViewModel;

    private ActivityCreateCourseBinding binding;

    private String[] list_status_course = new String[]{"Ativo", "Inativo"};
    private String status_course;
    private Spinner sp;
    private Button btnSaveCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        courseViewModel = ViewModelProviders.of(this).get(CourseViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_course);
        binding.setLifecycleOwner(this);

        // Populando Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list_status_course);

        sp = (Spinner) findViewById(R.id.input_status_course);
        sp.setAdapter(adapter);


//        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                switch (position){
//                    case 1:
//                        status_course = "Ativo";
//                        break;
//                    case 2:
//                        status_course = "Inativo";
//                        break;
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                return;
//            }
//        });


//        Alert para levar usuario para a tela de cadastro de materia


    }

    @Override
    protected void alertYes(int actionCustomIdentifier) {
        switch (actionCustomIdentifier) {
            case 0:
                showToast("Oi");
                break;
        }
    }

    @Override
    protected void alertNo(int actionCustomIdentifier) {

    }

    private void observeFields() {

    }

    private void observeActions() {
        binding.btnSaveCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert("Aviso", "Deseja cadastrar uma matéria?", 0);
            }
        });
    }

}
