package online.vidacademica.view.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityCresteTestBinding;
import online.vidacademica.viewmodel.TestViewModel;

public class CreateTestActivity extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener onDateSetListenerDtTest;
    private TestViewModel testViewModel;
    private ActivityCresteTestBinding binding;
    private Boolean screenCreated;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creste_test);

        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);

        testViewModel.createTest();

        testViewModel.isRegistred().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isRegistred) {
                if (!screenCreated) {
                    if (isRegistred) {
                        Toast.makeText(CreateTestActivity.this, "Registro realizado com sucesso.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(CreateTestActivity.this, "Erro, servidor ocupado, tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                    }
                }
                screenCreated = false;
            }
        });
        binding.btnSaveTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(RegisterActivity.this, ProfileActivity.class));
                testViewModel.createTest();
            }
        });

    }

}
