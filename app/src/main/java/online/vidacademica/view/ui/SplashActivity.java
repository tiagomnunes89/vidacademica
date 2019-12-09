package online.vidacademica.view.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import online.vidacademica.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent openMainActivity =  new Intent(SplashActivity.this, PreLoginActivity.class);
                startActivity(openMainActivity);
                finish();
            }
        }, 3000 );
    }
}
