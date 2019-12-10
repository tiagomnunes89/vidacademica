package online.vidacademica.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityLoginBinding;
import online.vidacademica.entities.TokenEntity;
import online.vidacademica.view.enums.RoleEnum;
import online.vidacademica.view.validation.ActivityBaseClassValidator;
import online.vidacademica.viewmodel.LoginViewModel;

import static online.vidacademica.view.enums.RoleEnum.STUDENT;

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
                if (tokenEntity == null) {
                    binding.editUser.setError(getString(R.string.login_toast_error));
                } else {
//                    showToast(R.string.login_toast_ok);
                    USER_ROLE = RoleEnum.fromString(tokenEntity.getRole());
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class)
                            .putExtra(ROLE, USER_ROLE));

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
}