package online.vidacademica.view.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityProfileBinding;
import online.vidacademica.utils.Util;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private ActivityProfileBinding binding;
    private Util util = new Util();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        binding.layoutCreateSubjectContent.textInputEtBirthDate.setOnClickListener(
                view -> util.callDatePickerDialog(ProfileActivity.this, onDateSetListener));

        onDateSetListener = (datePicker, year, month, day) -> {
            Log.d(TAG, "onDateSet: mm/dd/yyy: " + day + "/" + month + "/" + year);
            String date = day + "/" + month + "/" + year;
            binding.layoutCreateSubjectContent.textInputEtBirthDate.setText(date);
        };
        binding.btnChangeRegister.setOnClickListener(v ->
                startActivity(new Intent(ProfileActivity.this, CreateSubjectActivity.class)));
        binding.imageViewBack.setOnClickListener(v -> onBackPressed());
    }
}