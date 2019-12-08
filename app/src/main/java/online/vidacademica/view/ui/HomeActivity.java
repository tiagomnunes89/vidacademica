package online.vidacademica.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityHomeBinding;
import online.vidacademica.databinding.ActivityTeacherHomeBinding;
import online.vidacademica.view.enums.RoleEnum;

import static online.vidacademica.view.enums.RoleEnum.TEACHER;
import static online.vidacademica.view.ui.LoginActivity.ROLE;

public class HomeActivity extends BaseActivity {

    private ActivityHomeBinding bindingStudent;
    private ActivityTeacherHomeBinding bindingTeacher;

    private static RoleEnum USER_ROLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        captureIntent();

        if (USER_ROLE == TEACHER) {
            setUpTeacher();
        } else {
            setUpStudent();
        }
    }

    private void setUpTeacher() {
        bindingTeacher = DataBindingUtil.setContentView(this, R.layout.activity_teacher_home);
        bindingTeacher.setLifecycleOwner(this);

        bindingTeacher.imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert(R.string.home_alert_close_title, R.string.home_alert_close_message, 0);
            }
        });
    }

    private void setUpStudent() {
        bindingStudent = DataBindingUtil.setContentView(this, R.layout.activity_home);
        bindingStudent.setLifecycleOwner(this);
        bindingStudent.layoutContentBottomCards.cardViewMyNotes.setOnClickListener(v -> startActivity(
                new Intent(this, MyScoresActivity.class)));

        bindingStudent.layoutContentBottomCards.cardViewMySubjects.setOnClickListener(v -> startActivity(
                new Intent(this, ListMySubjectsActivity.class)));

        bindingStudent.imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert(R.string.home_alert_close_title, R.string.home_alert_close_message, 0);
            }
        });
    }

    @Override
    protected void captureIntent() {
        USER_ROLE = (RoleEnum) getIntent().getSerializableExtra(ROLE);
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

    @Override
    protected void observeFields() {

    }

    @Override
    protected void observeActions() {

    }

    public void openMyCourses(View view) {
        startActivity(new Intent(HomeActivity.this, ListMyCoursesActivity.class));
    }

    public void addCourses(View view) {
        startActivity(new Intent(HomeActivity.this, CreateUpdateCourseActivity.class));

    }

    public void openMySubjects(View view) {
        startActivity(new Intent(HomeActivity.this, ListMySubjectsActivity.class));
    }

    public void addSubject(View view) {
        startActivity(new Intent(HomeActivity.this, CreateSubjectActivity.class));
    }


    public void openMyTests(View view) {
        startActivity(new Intent(HomeActivity.this, ListMyTestsActivity.class));

    }

    public void addTest(View view) {
        startActivity(new Intent(HomeActivity.this, CreateTestActivity.class));
    }

    public void assignStudent(View view) {
        startActivity(new Intent(HomeActivity.this, CreateRegistrationActivity.class));
    }
}
