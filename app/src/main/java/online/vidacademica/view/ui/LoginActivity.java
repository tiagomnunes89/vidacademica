package online.vidacademica.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.validator.ValidatorException;

import online.vidacademica.R;
import online.vidacademica.entities.weak.Email;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "SignInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        colorStatusBar(getWindow());


        setUpListeners();
    }

    private void setUpListeners() {
        findViewById(R.id.button_sign_in_internal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInInternal();
            }
        });
    }

    private void signInInternal() {
        Email user = null;
        try {
            user = new Email(((EditText) findViewById(R.id.edit_user)).getText().toString());
        } catch (ValidatorException e) {
            Log.d(TAG, "signInInternal: ", e);
            Toast.makeText(this, "Endereço de e-mail inválido, por favor siga o exemplo: email@example.com ", Toast.LENGTH_LONG).show();
            return;
        }
        String pass = ((EditText) findViewById(R.id.edit_password)).getText().toString();

//        TokenRepository tokenRepository = new TokenRepository();
//        tokenRepository.getToken(user, pass);
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