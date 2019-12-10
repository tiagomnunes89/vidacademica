package online.vidacademica.view.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import java.util.Arrays;
import java.util.Optional;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityRegisterBinding;
import online.vidacademica.utils.DateFormatUtils;
import online.vidacademica.utils.Util;
import online.vidacademica.view.enums.CrudEnum;
import online.vidacademica.view.validation.ActivityBaseClassValidator;
import online.vidacademica.view.validation.validators.BirthDateValidation;
import online.vidacademica.view.validation.validators.EmailValidation;
import online.vidacademica.view.validation.validators.NameValidation;
import online.vidacademica.view.validation.validators.PasswordValidation;
import online.vidacademica.view.validation.validators.ValidatorDefaultTextInput;
import online.vidacademica.viewmodel.LoginViewModel;
import online.vidacademica.viewmodel.UserViewModel;

import static online.vidacademica.view.adapter.CoursesAdapter.CRUD_TYPE;
import static online.vidacademica.view.validation.Validator.executeAllValidators;

public class RegisterUpdateUserActivity extends ActivityBaseClassValidator {
    private static final String TAG = RegisterUpdateUserActivity.class.getSimpleName();

    private DatePickerDialog.OnDateSetListener onDateSetListener;

    private UserViewModel userViewModel;
    private LoginViewModel loginViewModel;

    private ActivityRegisterBinding binding;

    private Util util = new Util();

    private boolean screenCreated;

    private static CrudEnum ACTIVITY_FLOW;

    @Override
    public void onBackPressed() {
        dismissProgressBar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setLifecycleOwner(this);

        screenCreated = true;
        captureIntent();

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        binding.layoutRegisterContent.setUserViewModel(userViewModel);

        observeActions();

        if (ACTIVITY_FLOW.equals(CrudEnum.UPDATE)) {
            binding.registerScreenTitle.setText(getString(R.string.register_title_update));
            userViewModel.self();
            showProgressBar(R.id.register_screen);
        }

        initValidator();
    }

    @Override
    protected void captureIntent() {
        ACTIVITY_FLOW = (CrudEnum) Optional.ofNullable(getIntent().getSerializableExtra(CRUD_TYPE)).orElse(CrudEnum.CREATE);

//        if (ACTIVITY_FLOW.equals(CrudEnum.UPDATE)) {
//            String selectedCourseJson = (String) getIntent().getSerializableExtra(SELECTED_OBJECT);
//
//            if (selectedCourseJson != null) {
//                CourseDTO selectedCourse = new Gson().fromJson(selectedCourseJson, CourseDTO.class);
//
//                if (selectedCourse != null) {
////                    courseViewModel.courseDTO = selectedCourse;
//                }
//            }
//        }
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

    @Override
    protected void observeFields() {
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
                util.callDatePickerDialog(RegisterUpdateUserActivity.this, onDateSetListener);
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

    @Override
    protected void observeActions() {

        binding.btnSendRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(RegisterUpdateUserActivity.this, ProfileActivity.class));
                showProgressBar(R.id.register_screen);
                if (ACTIVITY_FLOW.equals(CrudEnum.UPDATE)) {
                    userViewModel.update();
                } else {
                    userViewModel.register();
                }
            }
        });

        binding.layoutRegisterContent.editTextBirthDate.setOnClickListener(view ->
                util.callDatePickerDialog(RegisterUpdateUserActivity.this, onDateSetListener));

        onDateSetListener = (datePicker, year, month, day) -> {
            String date = DateFormatUtils.onDateSetResultToString(datePicker);
            binding.layoutRegisterContent.editTextBirthDate.setText(date);
        };
        binding.imageViewBack.setOnClickListener(v -> startActivity(new Intent(RegisterUpdateUserActivity.this, PreLoginActivity.class)));

        userViewModel.getIsResponseModelLiveData().observe(this, userEntityResponseModel -> {
            dismissProgressBar();

            boolean isRegistred = userViewModel.isRegistred();
            boolean isUpdated = userViewModel.isUpdated();

            if (userEntityResponseModel != null) {
                if (isRegistred) {
                    showToast(R.string.register_ficha_criada);
                    loginViewModel.deleteLoginData();
                    Intent openLoginActivity = new Intent(RegisterUpdateUserActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(openLoginActivity);
                    finish();
                } else if (isUpdated && !screenCreated) {
                    showToast(R.string.register_ficha_atualizada);
                    Intent openLoginActivity = new Intent(RegisterUpdateUserActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(openLoginActivity);
                    finish();
                } else if (screenCreated && isUpdated) {
                    binding.layoutRegisterContent.editTextName.setText(userEntityResponseModel.getResponse().getName());
                    userViewModel.userEntity.setId(userEntityResponseModel.getResponse().getId());

                    binding.layoutRegisterContent.textInputEmail.setVisibility(View.INVISIBLE);
                    binding.layoutRegisterContent.textInputRg.setVisibility(View.INVISIBLE);
                    binding.layoutRegisterContent.textInputBirth.setVisibility(View.INVISIBLE);
                    binding.layoutRegisterContent.textInputPassword.setVisibility(View.INVISIBLE);

                    enableContinueButton(true);
                } else {
                    showToast(R.string.register_ficha_error);
                }
            }

            screenCreated = false;
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