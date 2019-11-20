package online.vidacademica.presentation.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;

import online.vidacademica.R;
import online.vidacademica.core.Util;
import online.vidacademica.databinding.ActivityCreateSubjectBinding;

public class CreateSubjectActivity extends AppCompatActivity {

    private static final String TAG = "CreateSubjectActivity";
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private ActivityCreateSubjectBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_subject);
        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        binding.contentCreateSubjectLayout.inputDtInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.callDatePickerDialog(CreateSubjectActivity.this, onDateSetListener);
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + day + "/" + month + "/" + year);
                String date = day + "/" + month + "/" + year;
                binding.contentCreateSubjectLayout.inputDtInicio.setText(date);
            }
        };
    }

}


