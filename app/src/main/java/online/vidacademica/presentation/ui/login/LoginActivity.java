package online.vidacademica.presentation.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.apache.commons.validator.ValidatorException;

import online.vidacademica.R;
import online.vidacademica.entities.Email;
import online.vidacademica.presentation.ui.RegisterActivity;
import online.vidacademica.repositories.TokenRepository;

public class LoginActivity extends AppCompatActivity {
    private SignInButton signInButton;
    private GoogleSignInClient mSignInClient;
    private TextView textViewRegister;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        colorStatusBar(getWindow());
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mSignInClient = GoogleSignIn.getClient(this, gso);

        setUpListeners();
    }

    private void setUpListeners() {

        findViewById(R.id.button_sign_in_internal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

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
            e.printStackTrace();
            Toast.makeText(this, "Endereço de e-mail inválido, por favor siga o exemplo: email@example.com ", Toast.LENGTH_LONG).show();
            return;
        }
        String pass = ((EditText) findViewById(R.id.edit_password)).getText().toString();

        TokenRepository tokenRepository = new TokenRepository();
        tokenRepository.getToken(user, pass);
    }

    private void colorStatusBar(Window window) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        View view = window.getDecorView();
        view.setSystemUiVisibility(View.GONE);
        window.setStatusBarColor(getResources().getColor(R.color.colorBackground));
    }

    private void signIn() {
        Intent intent = mSignInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task =
                    GoogleSignIn.getSignedInAccountFromIntent(data);
            if (task.isSuccessful()) {
                handleSignInResult(task);
            } else {
                Toast.makeText(this, "LoginActivity não concluído", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Toast.makeText(this, "Deu certo: " + account.getEmail(), Toast.LENGTH_LONG).show();
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    public void openRegisterSccreen(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}