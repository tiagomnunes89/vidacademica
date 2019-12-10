package online.vidacademica.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivitySplashBinding;
import online.vidacademica.entities.TokenEntity;
import online.vidacademica.viewmodel.LoginViewModel;

public class SplashActivity extends BaseActivity {

    private ActivitySplashBinding binding;
    private LoginViewModel loginViewModel;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        handler = new Handler();

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
        loginViewModel.getToken().observe(this, new Observer<TokenEntity>() {
            @Override
            public void onChanged(@Nullable TokenEntity tokenEntity) {
                dismissProgressBar();

                if (tokenEntity != null) {
                    if (hasAValidUser(tokenEntity)) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent openMainActivity = new Intent(SplashActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(openMainActivity);
                                finish();
                            }
                        }, 2000);
                    } else {
                        openPreLoginActivity();
                    }
                } else {
                    openPreLoginActivity();
                }
            }
        });
    }

    private void openPreLoginActivity() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent openMainActivity = new Intent(SplashActivity.this, PreLoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(openMainActivity);
                finish();
            }
        }, 2000);
    }

    private boolean hasAValidUser(TokenEntity tokenEntity) {
        if (tokenEntity != null) {
            if (tokenEntity.getEmail() != null && tokenEntity.getPassword() != null) {
                return true;
            }
        }
        return false;
    }
}
