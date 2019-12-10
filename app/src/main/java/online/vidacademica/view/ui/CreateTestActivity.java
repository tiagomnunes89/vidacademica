package online.vidacademica.view.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityCreateTestBinding;
import online.vidacademica.utils.Util;
import online.vidacademica.viewmodel.TestViewModel;

public class CreateTestActivity extends BaseActivity {
    private DatePickerDialog.OnDateSetListener onDateSetListenerDtTest;
    private TestViewModel testViewModel;
    private ActivityCreateTestBinding binding;
    private static final String TAG = CreateTestActivity.class.getSimpleName();
    private Util util = new Util();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_test);

        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_test);
        binding.setLifecycleOwner(this);

        binding.layoutCreateTestContent.setTestViewModel(testViewModel);

        binding.layoutCreateTestContent.inputDtTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                util.callDatePickerDialog(CreateTestActivity.this, onDateSetListenerDtTest);
            }
        });

        binding.imageViewBack.setOnClickListener(v -> onBackPressed());

        onDateSetListenerDtTest = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + day + "/" + month + "/" + year);
                String date = day + "/" + month + "/" + year;
                binding.layoutCreateTestContent.inputDtTest.setText(date);
            }
        };
        observeFields();
        observeActions();
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

    protected void observeFields() {

    }

    protected void observeActions() {
        binding.btnSaveTest.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                showToast(R.string.create_test_toast_create_loading);
                showProgressBar(R.id.create_test_screen);
                testViewModel.createTest();
            }
        });

        testViewModel.getLastTestCreated().observe(this, TestEntityResponse -> {
            dismissProgressBar();

            boolean testCreated = testViewModel.lastTestcreated();

            if (TestEntityResponse != null) {

                if (testCreated) {
                    showToast(R.string.create_test_toast_create_ok);
                    showAlert(R.string.create_test_alert_title, R.string.create_test_alert_message, 0);
                } else {
                    showToast(R.string.create_course_toast_create_error);
                }

            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
