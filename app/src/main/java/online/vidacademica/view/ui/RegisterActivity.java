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
import online.vidacademica.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private ActivityRegisterBinding binding;
    private Util util = new Util();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        binding.layoutRegisterContent.textInputEtBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                util.callDatePickerDialog(RegisterActivity.this, onDateSetListener);
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + day + "/" + month + "/" + year);
                String date = day + "/" + month + "/" + year;
                binding.layoutRegisterContent.textInputEtBirthDate.setText(date);
            }
        };
        binding.imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, PreLoginActivity.class));
            }
        });
        binding.btnSendRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, ProfileActivity.class));
            }
        });
    }
}
