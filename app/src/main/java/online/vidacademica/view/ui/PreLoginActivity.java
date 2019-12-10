package online.vidacademica.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityPreLoginBinding;
import online.vidacademica.viewmodel.LoginViewModel;

public class PreLoginActivity extends BaseActivity {
    private ActivityPreLoginBinding binding;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pre_login);

//        showProgressBar(R.id.prelogin_screen);
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
            public void onClick(View v) {
                startActivity(new Intent(PreLoginActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
            }
        });
        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PreLoginActivity.this, RegisterUpdateUserActivity.class));
            }
        });

    }


}