package online.vidacademica.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityCreateUpdateCourseBinding;
import online.vidacademica.entities.CourseDTO;
import online.vidacademica.view.enums.CrudEnum;
import online.vidacademica.viewmodel.CourseViewModel;

import static online.vidacademica.entities.CourseDTO.POSSIBLE_STATUS;
import static online.vidacademica.view.adapter.CoursesAdapter.CRUD_TYPE;
import static online.vidacademica.view.adapter.CoursesAdapter.SELECTED_OBJECT;

public class CreateUpdateCourseActivity extends BaseActivity {
    private static final String TAG = CreateUpdateCourseActivity.class.getSimpleName();

    private CourseViewModel courseViewModel;

    private ActivityCreateUpdateCourseBinding binding;

    private static CrudEnum ACTIVITY_FLOW;
    private List<CourseDTO> courses = new ArrayList<>();

    /**
     * Previne que a tela tome ação de cadastrar caso a lista seja atualizada
     * por outro usuário no servidor.
     */
    private static boolean userHasSaved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courseViewModel = ViewModelProviders.of(this).get(CourseViewModel.class);

        captureIntent();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_update_course);
        binding.setLifecycleOwner(this);

        binding.layoutCreateCourseContent.setCourseViewModel(courseViewModel);

        // Populando Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, POSSIBLE_STATUS);
        binding.layoutCreateCourseContent.spinnerStatusCourse.setAdapter(adapter);

        observeFields();
        observeActions();

    }

    @Override
    protected void captureIntent() {
        ACTIVITY_FLOW = (CrudEnum) Optional.ofNullable(getIntent().getSerializableExtra(CRUD_TYPE)).orElse(CrudEnum.CREATE);

        if (ACTIVITY_FLOW.equals(CrudEnum.UPDATE)) {
            String selectedCourseJson = (String) getIntent().getSerializableExtra(SELECTED_OBJECT);

            if (selectedCourseJson != null) {
                CourseDTO selectedCourse = new Gson().fromJson(selectedCourseJson, CourseDTO.class);

                if (selectedCourse != null) {
                    courseViewModel.courseDTO = selectedCourse;
                }
            }
        }
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
        switch (actionCustomIdentifier) {
            case 0:
                startActivity(new Intent(CreateUpdateCourseActivity.this, ListMyCoursesActivity.class));
                break;
        }
    }

    @Override
    protected void observeFields() {

    }

    @Override
    protected void observeActions() {
        binding.btnSaveCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast(R.string.create_course_toast_create_loading);
                showProgressBar(R.id.create_course_screen);
                courseViewModel.getAllCourses();
                userHasSaved = true;
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

        courseViewModel.getAllCourses().observe(this, allCourses -> {
            dismissProgressBar();
            if (userHasSaved) {
                if (allCourses != null) {
                    if (allCourses.getCode() == 200) {
                        showToast(R.string.list_courses_toast_ok);
                        courses.addAll(allCourses.getResponse());
                        verifyCoursesDuplicates();
                    } else {
                        showToast(R.string.list_courses_toast_error);
                    }
                }
            }

            userHasSaved = false;
        });
    }

    private void verifyCoursesDuplicates() {
        int qttDuplicates = (int) courses.stream().filter(courseDTO -> courseDTO.getName().equalsIgnoreCase(courseViewModel.courseDTO.getName())).count();

        if (qttDuplicates == 0) {
            courseViewModel.createCourse();
        } else {
            showToast(R.string.create_course_toast_course_duplicate);
        }
    }

}
