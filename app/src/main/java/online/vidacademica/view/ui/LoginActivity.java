package online.vidacademica.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.Arrays;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityLoginBinding;
import online.vidacademica.entities.TokenEntity;
import online.vidacademica.presentation.SingletonToken;
import online.vidacademica.view.enums.RoleEnum;
import online.vidacademica.view.validation.ActivityBaseClassValidator;
import online.vidacademica.view.validation.validators.EmailValidation;
import online.vidacademica.view.validation.validators.ValidatorDefaultTextInput;
import online.vidacademica.viewmodel.LoginViewModel;

import static online.vidacademica.view.enums.RoleEnum.STUDENT;
import static online.vidacademica.view.validation.Validator.executeAllValidators;

public class LoginActivity extends ActivityBaseClassValidator {
    private static final String TAG = LoginActivity.class.getSimpleName();

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;

    public static final String ROLE = "ROLE";
    public static RoleEnum USER_ROLE = STUDENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        colorStatusBar(getWindow());

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);

        observeFields();
        observeActions();
        initValidator();
    }

    @Override
    public void onResume() {
        super.onResume();
        enableContinueButton(executeAllValidators(getValidationList()));
    }

    private void initValidator() {
        observeFields();
        initValidationList();
        enableContinueButton(executeAllValidators(getValidationList()));
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

    @Override
    protected void observeFields() {
        binding.textInputEmail.setOnFocusChangeListener((v, hasFocus) -> {
            enableContinueButton(executeAllValidators(getValidationList(), EmailValidation.class));
            if (hasFocus)
                binding.textInputLayoutEmail.setErrorEnabled(false);
        });
        binding.textInputEditPassword.setOnFocusChangeListener((v, hasFocus) -> {
            enableContinueButton(executeAllValidators(getValidationList(), ValidatorDefaultTextInput.class));
            if (hasFocus) {
                binding.textInputLayoutPassword.setErrorEnabled(false);
            }
        });
        binding.textInputEditPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enableContinueButton(executeAllValidators(getValidationList(), ValidatorDefaultTextInput.class));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void observeActions() {
        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressBar(R.id.login_screen);
                showToast(R.string.login_toast_in_loading);
                loginViewModel.login();
            }
        });
        loginViewModel.getToken().observe(this, new Observer<TokenEntity>() {
            @Override
            public void onChanged(@Nullable TokenEntity tokenEntity) {
                dismissProgressBar();
                if (tokenEntity == null && binding.textInputEditPassword.getText() != null
                        && !binding.textInputEditPassword.getText().toString().isEmpty()) {
                    binding.textInputEmail.setError(getString(R.string.login_toast_error));
                }
                if (tokenEntity != null) {
//                    showToast(R.string.login_toast_ok);
                    USER_ROLE = RoleEnum.fromString(tokenEntity.getRole());
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class)
                            .putExtra(ROLE, USER_ROLE));
                    SingletonToken.createTokenEntity(tokenEntity);
                }
            }
        });
        binding.imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void colorStatusBar(Window window) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        View view = window.getDecorView();
        view.setSystemUiVisibility(View.GONE);
        window.setStatusBarColor(getResources().getColor(R.color.colorBackground));
    }

    private void enableContinueButton(Boolean isEnable) {
        binding.buttonLogin.setEnabled(isEnable);
        updateTextColorButton(isEnable);
    }

    private void updateTextColorButton(Boolean isEnable) {
        if (isEnable) {
            binding.buttonLogin.setTextColor(getColor(R.color.colorOrange));
        } else {
            binding.buttonLogin.setTextColor(getColor(R.color.colorGrayDark));
        }
    }

    private void initValidationList() {
        getValidationList().addAll(Arrays.asList(
                new EmailValidation(this, binding.textInputLayoutEmail),
                new ValidatorDefaultTextInput(this, binding.textInputLayoutPassword)));
    }
}