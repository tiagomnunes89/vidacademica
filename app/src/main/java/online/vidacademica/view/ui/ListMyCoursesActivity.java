package online.vidacademica.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import online.vidacademica.R;
import online.vidacademica.entities.CourseDTO;
import online.vidacademica.entities.TestEntity;
import online.vidacademica.view.adapter.CoursesAdapter;

public class ListMyCoursesActivity extends AppCompatActivity {

    List<CourseDTO> courses = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_my_courses);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(ListMyCoursesActivity.this, CreateCourseActivity.class));
            }
        });
        getCourses();

        startRecycler();

    }

    private void startRecycler() {
        CoursesAdapter adapter = new CoursesAdapter(this, courses);
        RecyclerView recyclerView = findViewById(R.id.course_recycler);
        recyclerView.setAdapter(adapter);

        //criar layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    private void getCourses() {
        CourseDTO course1 = new CourseDTO("Sistemas para internet","teste",true);
        CourseDTO course2 = new CourseDTO("Licenciatura","teste",true);

        courses.add(course1);
        courses.add(course2);
    }

}
