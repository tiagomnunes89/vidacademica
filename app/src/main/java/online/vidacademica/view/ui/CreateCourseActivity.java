package online.vidacademica.view.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityCreateCourseBinding;
import online.vidacademica.viewmodel.CourseViewModel;

import static online.vidacademica.entities.CourseDTO.POSSIBLE_STATUS;

public class CreateCourseActivity extends BaseActivity {
    private static final String TAG = CreateCourseActivity.class.getSimpleName();

    private CourseViewModel courseViewModel;

    private ActivityCreateCourseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        courseViewModel = ViewModelProviders.of(this).get(CourseViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_course);
        binding.setLifecycleOwner(this);

        binding.layoutCreateCourseContent.setCourseViewModel(courseViewModel);

        // Populando Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, POSSIBLE_STATUS);
        binding.layoutCreateCourseContent.spinnerStatusCourse.setAdapter(adapter);

        observeFields();
        observeActions();

    }

    @Override
    protected void alertYes(int actionCustomIdentifier) {
        switch (actionCustomIdentifier) {
            case 0:
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
                showToast(R.string.create_course_toast_create_loading);
                showProgressBar(R.id.create_course_screen);
                courseViewModel.createCourse();
            }
        });

        binding.layoutCreateCourseContent.spinnerStatusCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseViewModel.courseDTO.setActive(POSSIBLE_STATUS[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        courseViewModel.getLastCourseCreated().observe(this, courseDTOResponse -> {
            dismissProgressBar();

            boolean courseCreated = courseViewModel.lastCourseCreated();

            if (courseDTOResponse != null) {

                if (courseCreated) {
                    showToast(R.string.create_course_toast_create_ok);
                    showAlert(R.string.create_course_alert_title, R.string.create_course_alert_message, 0);
                } else {
                    showToast(R.string.create_course_toast_create_error);
                }

            }
        });
    }

}
