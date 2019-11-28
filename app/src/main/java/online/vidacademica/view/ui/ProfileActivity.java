package online.vidacademica.view.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import online.vidacademica.R;
import online.vidacademica.core.Util;
import online.vidacademica.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private ActivityProfileBinding binding;
    private Util util = new Util();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        binding.layoutCreateSubjectContent.textInputEtBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                util.callDatePickerDialog(ProfileActivity.this, onDateSetListener);
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + day + "/" + month + "/" + year);
                String date = day + "/" + month + "/" + year;
                binding.layoutCreateSubjectContent.textInputEtBirthDate.setText(date);
            }
        };
        binding.btnChangeRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, CreateSubjectActivity.class));
            }
        });
        binding.imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
