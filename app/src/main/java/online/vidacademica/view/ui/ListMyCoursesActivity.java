package online.vidacademica.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityListMyCoursesBinding;
import online.vidacademica.entities.CourseDTO;
import online.vidacademica.view.adapter.CoursesAdapter;
import online.vidacademica.viewmodel.CourseViewModel;

public class ListMyCoursesActivity extends BaseActivity {

    List<CourseDTO> courses = new ArrayList<>();

    private CourseViewModel courseViewModel;

    private ActivityListMyCoursesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courseViewModel = ViewModelProviders.of(this).get(CourseViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_my_courses);
        binding.setLifecycleOwner(this);

        showProgressBar(R.id.courses_screen);

        observeActions();
        observeFields();

    }

    @Override
    protected void captureIntent() {

    }

    @Override
    protected void alertYes(int actionCustomIdentifier) {

    }

    @Override
    protected void alertNo(int actionCustomIdentifier) {

    }

    @Override
    protected void observeFields() {

    }

    @Override
    protected void observeActions() {
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListMyCoursesActivity.this, CreateUpdateCourseActivity.class));
            }
        });

        courseViewModel.getAllCourses().observe(this, allCourses -> {
            dismissProgressBar();
            if (allCourses != null) {
                if (allCourses.getCode() == 200) {
                    showToast(R.string.list_courses_toast_ok);
                    courses.addAll(allCourses.getResponse());
                    startRecycler();
                } else {
                    showToast(R.string.list_courses_toast_error);
                }
            }
        });
    }

    private void startRecycler() {
        CoursesAdapter adapter = new CoursesAdapter(this, courses);
        RecyclerView recyclerView = findViewById(R.id.course_recycler);
        recyclerView.setAdapter(adapter);

        //criar layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

}
