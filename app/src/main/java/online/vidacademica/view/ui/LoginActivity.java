package online.vidacademica.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityLoginBinding;
import online.vidacademica.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        colorStatusBar(getWindow());

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLifecycleOwner(this);




        setUpClickHandlers();
    }

    private void setUpClickHandlers() {

//        findViewById(R.id.button_sign_in_internal).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loginViewModel.login();
//            }
//        });
    }


    private void colorStatusBar(Window window) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        View view = window.getDecorView();
        view.setSystemUiVisibility(View.GONE);
        window.setStatusBarColor(getResources().getColor(R.color.colorBackground));
    }


    public void openRegisterSccreen(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}