package online.vidacademica.view.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;

import androidx.appcompat.view.ContextThemeWrapper;
import androidx.databinding.DataBindingUtil;

import java.util.Random;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityHomeNewLayoutBinding;
import online.vidacademica.view.enums.RoleEnum;

import static online.vidacademica.view.enums.RoleEnum.TEACHER;
import static online.vidacademica.view.ui.LoginActivity.ROLE;

public class HomeActivity extends BaseActivity {

    private ActivityHomeNewLayoutBinding binding;

    private static RoleEnum USER_ROLE;

    private HorizontalScrollView bottomIncludeCards;
    private LayoutInflater inflater;

    private static final String RANDOM_PROFILE_IMG = String.format("monster%s", new Random().nextInt(8));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_new_layout);
        binding.setLifecycleOwner(this);

        bottomIncludeCards = findViewById(R.id.layout_content_bottom_cards);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        binding.nameUser.setText("Oi, Tiago Marques");

        //randomProfilePhoto();

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
        binding.profileType.setText("Professor(a)");

    }

    private void setUpStudent() {

        inflateCards(R.layout.content_bottom_cards);
        binding.profileType.setText("Aluno(a)");

        binding.layoutContentBottomCards.cardViewMyNotes.setOnClickListener(v -> startActivity(
                new Intent(this, MyScoresActivity.class)));

        binding.layoutContentBottomCards.cardViewMySubjects.setOnClickListener(v -> startActivity(
                new Intent(this, ListMySubjectsActivity.class)));

        binding.imageViewBack.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.CustomAlertDialog));
            builder.setMessage(R.string.home_alert_close_title).setPositiveButton(getString(R.string.logout), dialogClickListener)
                    .setNegativeButton(getString(R.string.cancel), dialogClickListener).show();
        });
    }

    DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
        switch (which){
            case DialogInterface.BUTTON_POSITIVE:
                startActivity(new Intent(HomeActivity.this,PreLoginActivity.class));
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                dialog.cancel();
                break;
        }
    };

/*    private void randomProfilePhoto() {
        binding.homeProfilePhoto.setImageDrawable(
                getResources().getDrawable(getResourceID(RANDOM_PROFILE_IMG, "drawable", getApplicationContext()))
        );
    }*/


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
        binding.imageViewBack.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.CustomAlertDialog));
            builder.setMessage(R.string.home_alert_close_title).setPositiveButton(getString(R.string.logout), dialogClickListener)
                    .setNegativeButton(getString(R.string.cancel), dialogClickListener).show();
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
        startActivity(new Intent(HomeActivity.this, MyScoresActivity.class));
    }
}
