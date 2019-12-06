package online.vidacademica.view.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setLifecycleOwner(this);

        binding.layoutContentBottomCards.cardViewMyNotes.setOnClickListener(v -> startActivity(
                new Intent(this, MyScoresActivity.class)));

        binding.layoutContentBottomCards.cardViewMySubjects.setOnClickListener(v -> startActivity(
                new Intent(this, ListMySubjectsActivity.class)));
    }
}
