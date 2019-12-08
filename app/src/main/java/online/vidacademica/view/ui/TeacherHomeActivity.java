package online.vidacademica.view.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import online.vidacademica.R;

public class TeacherHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);


    }

    public void openMyCourses(View view) {
        startActivity(new Intent(TeacherHomeActivity.this,ListMyCoursesActivity.class));
    }

    public void addCourses(View view) {
        startActivity(new Intent(TeacherHomeActivity.this, CreateUpdateCourseActivity.class));

    }

    public void openMySubjects(View view) {
        startActivity(new Intent(TeacherHomeActivity.this,ListMySubjectsActivity.class));

    }

    public void addSubject(View view) {
        startActivity(new Intent(TeacherHomeActivity.this,CreateSubjectActivity.class));
    }


    public void openMyTests(View view) {
        startActivity(new Intent(TeacherHomeActivity.this,ListMyTestsActivity.class));

    }

    public void addTest(View view) {
        startActivity(new Intent(TeacherHomeActivity.this,CreateTestActivity.class));

    }
}
