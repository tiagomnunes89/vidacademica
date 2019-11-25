package online.vidacademica.presentation;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityCreateCourseBinding;
import online.vidacademica.databinding.ActivityCreateSubjectBinding;

public class CreateCourseActivity extends AppCompatActivity {

    private ActivityCreateCourseBinding binding;

    private String[] list_status_course = new String[]{"Selecione...","Ativo","Inativo"};
    private String status_course;
    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);


        // Populando Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,list_status_course);

        sp = (Spinner) findViewById(R.id.input_status_course);
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

    }

}
