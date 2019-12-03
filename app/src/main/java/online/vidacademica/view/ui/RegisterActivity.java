package online.vidacademica.view.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import online.vidacademica.R;
import online.vidacademica.core.ResponseModel;
import online.vidacademica.core.Util;
import online.vidacademica.databinding.ActivityRegisterBinding;
import online.vidacademica.entities.UserEntity;
import online.vidacademica.viewmodel.RegisterViewModel;

public class RegisterActivity extends BaseActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    private RegisterViewModel registerViewModel;
    private ActivityRegisterBinding binding;

    private Util util = new Util();

    private Boolean screenCreated;

    @Override
    public void onBackPressed() {
        dismissProgressBar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        screenCreated = true;

        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setLifecycleOwner(this);

        binding.layoutRegisterContent.setRegisterViewModel(registerViewModel);

        registerViewModel.getUsResponseModelLiveData().observe(this, new Observer<ResponseModel<UserEntity>>() {
            @Override
            public void onChanged(ResponseModel<UserEntity> userEntityResponseModel) {
                if (screenCreated != null) {
                    if (registerViewModel.isRegistred()) {
                        Toast.makeText(RegisterActivity.this, "Registro realizado com sucesso.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Erro, servidor ocupado, tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


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
//                startActivity(new Intent(RegisterActivity.this, ProfileActivity.class));
                showProgressBar(R.id.register_screen);
                registerViewModel.register();
            }
        });



    }
}