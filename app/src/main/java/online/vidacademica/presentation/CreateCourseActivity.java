package online.vidacademica.presentation;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityCreateCourseBinding;
import online.vidacademica.databinding.ActivityCreateSubjectBinding;

public class CreateCourseActivity extends AppCompatActivity {

    private ActivityCreateCourseBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);



    }

}
