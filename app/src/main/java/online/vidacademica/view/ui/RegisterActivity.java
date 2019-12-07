package online.vidacademica.view.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import java.util.Arrays;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityRegisterBinding;
import online.vidacademica.utils.DateFormatUtils;
import online.vidacademica.utils.Util;
import online.vidacademica.view.validation.ActivityBaseClassValidator;
import online.vidacademica.view.validation.validators.BirthDateValidation;
import online.vidacademica.view.validation.validators.EmailValidation;
import online.vidacademica.view.validation.validators.NameValidation;
import online.vidacademica.view.validation.validators.PasswordValidation;
import online.vidacademica.view.validation.validators.ValidatorDefaultTextInput;
import online.vidacademica.viewmodel.RegisterViewModel;

import static online.vidacademica.view.validation.Validator.executeAllValidators;

public class RegisterActivity extends ActivityBaseClassValidator {
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

        observeActions();
        initValidator();
    }

    @Override
    protected void alertYes(int actionCustomIdentifier) {
    }

    @Override
    protected void alertNo(int actionCustomIdentifier) {
    }

    private void initValidator() {
        observeFields();
        initValidationList();
        enableContinueButton(executeAllValidators(getValidationList()));
    }

    private void observeFields() {
        binding.layoutRegisterContent.editTextName.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus)
                enableContinueButton(executeAllValidators(getValidationList(), NameValidation.class));
            else binding.layoutRegisterContent.textInputName.setErrorEnabled(false);
        });
        binding.layoutRegisterContent.editTextEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus)
                enableContinueButton(executeAllValidators(getValidationList(), EmailValidation.class));
            else binding.layoutRegisterContent.textInputEmail.setErrorEnabled(false);
        });
        binding.layoutRegisterContent.editTextBirthDate.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus)
                enableContinueButton(executeAllValidators(getValidationList(), BirthDateValidation.class));
            else {
                binding.layoutRegisterContent.textInputBirth.setErrorEnabled(false);
                util.callDatePickerDialog(RegisterActivity.this, onDateSetListener);
            }
        });
        binding.layoutRegisterContent.editTextPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus)
                enableContinueButton(executeAllValidators(getValidationList(), PasswordValidation.class));
            else binding.layoutRegisterContent.textInputPassword.setErrorEnabled(false);
        });
        binding.layoutRegisterContent.editTextRg.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus)
                enableContinueButton(executeAllValidators(getValidationList(), ValidatorDefaultTextInput.class));
            else binding.layoutRegisterContent.textInputRg.setErrorEnabled(false);
        });
    }

    private void observeActions() {

        binding.btnSendRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(RegisterActivity.this, ProfileActivity.class));
                showProgressBar(R.id.register_screen);
                registerViewModel.register();
            }
        });

        binding.layoutRegisterContent.editTextBirthDate.setOnClickListener(view ->
                util.callDatePickerDialog(RegisterActivity.this, onDateSetListener));

        onDateSetListener = (datePicker, year, month, day) -> {
            String date = DateFormatUtils.onDateSetResultToString(datePicker);
            binding.layoutRegisterContent.editTextBirthDate.setText(date);
        };
        binding.imageViewBack.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, PreLoginActivity.class)));

        registerViewModel.getIsResponseModelLiveData().observe(this, userEntityResponseModel -> {
            if (screenCreated != null) {
                if (registerViewModel.isRegistred()) {
                    dismissProgressBar();
                    Toast.makeText(RegisterActivity.this, "Registro realizado com sucesso.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Erro, servidor ocupado, tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        enableContinueButton(executeAllValidators(getValidationList()));
    }

    private void enableContinueButton(Boolean isEnable) {
        binding.btnSendRegister.setEnabled(isEnable);
        updateTextColorButton(isEnable);
    }

    private void updateTextColorButton(Boolean isEnable) {
        if (isEnable) {
            binding.btnSendRegister.setTextColor(getColor(R.color.colorBlue));
        } else {
            binding.btnSendRegister.setTextColor(getColor(R.color.colorGrayDark));
        }
    }

    private void initValidationList() {
        getValidationList().addAll(Arrays.asList(new NameValidation(this, binding.layoutRegisterContent.textInputName),
                new EmailValidation(this, binding.layoutRegisterContent.textInputEmail),
                new BirthDateValidation(this, binding.layoutRegisterContent.textInputBirth),
                new ValidatorDefaultTextInput(this, binding.layoutRegisterContent.textInputRg),
                new PasswordValidation(this, binding.layoutRegisterContent.textInputPassword)));
    }
}