package online.vidacademica.view.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import java.util.Random;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityHomeBinding;
import online.vidacademica.view.enums.RoleEnum;
import online.vidacademica.viewmodel.LoginViewModel;

import static online.vidacademica.view.enums.RoleEnum.TEACHER;
import static online.vidacademica.view.ui.LoginActivity.ROLE;

public class HomeActivity extends BaseActivity {

    private ActivityHomeBinding binding;

    private static RoleEnum USER_ROLE;

    private HorizontalScrollView bottomIncludeCards;
    private LayoutInflater inflater;

    private LoginViewModel loginViewModel;

    private static final String RANDOM_PROFILE_IMG = String.format("monster%s", new Random().nextInt(8));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setLifecycleOwner(this);

        bottomIncludeCards = findViewById(R.id.layout_content_bottom_cards);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        randomProfilePhoto();

        captureIntent();
        if (USER_ROLE == TEACHER) {
            setUpTeacher();
        } else {
            setUpStudent();
        }

        observeActions();
    }

    private void inflateCards(Integer layoutViewId) {
        View layout = inflater.inflate(layoutViewId, null);

        // Clear & set new views:
        bottomIncludeCards.removeAllViews();
        bottomIncludeCards.addView(layout);
    }

    private void setUpTeacher() {
        inflateCards(R.layout.content_bottom_cards_teacher);

    }

    private void setUpStudent() {

        inflateCards(R.layout.content_bottom_cards);

        binding.layoutContentBottomCards.cardViewMyNotes.setOnClickListener(v -> startActivity(
                new Intent(this, ScoresActivity.class)));

        binding.layoutContentBottomCards.cardViewMySubjects.setOnClickListener(v -> startActivity(
                new Intent(this, ListMySubjectsActivity.class)));

        binding.imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert(R.string.home_alert_close_title, R.string.home_alert_close_message, 0);
            }
        });


    }

    private void randomProfilePhoto() {
        binding.homeProfilePhoto.setImageDrawable(
                getResources().getDrawable(getResourceID(RANDOM_PROFILE_IMG, "drawable", getApplicationContext()))
        );
    }


    protected static int getResourceID(final String resName, final String resType, final Context ctx) {
        final int ResourceID = ctx.getResources().getIdentifier(resName, resType, ctx.getApplicationInfo().packageName);
        if (ResourceID == 0) {
            throw new IllegalArgumentException("No resource string found with name " + resName);
        } else {
            return ResourceID;
        }
    }

    @Override
    protected void captureIntent() {
        USER_ROLE = (RoleEnum) getIntent().getSerializableExtra(ROLE);
    }

    @Override
    protected void alertYes(int actionCustomIdentifier) {
        switch (actionCustomIdentifier) {
            case 0:
                loginViewModel.deleteLoginData();
                Intent openSplashActivity = new Intent(HomeActivity.this, SplashActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(openSplashActivity);
                finish();
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
        binding.imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert(R.string.home_alert_close_title, R.string.home_alert_close_message, 0);
            }
        });
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

    public void openMyScores(View view) {
        startActivity(new Intent(HomeActivity.this, ScoresActivity.class));
    }
}
