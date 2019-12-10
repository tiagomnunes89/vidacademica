package online.vidacademica.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivitySetResultTestBinding;
import online.vidacademica.entities.UserEntity;
import online.vidacademica.view.adapter.SetResultTestAdapter;

public class SetResultTestActivity extends BaseActivity {

    private List<UserEntity> students = new ArrayList<>();
    private ActivitySetResultTestBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_set_result_test);
        binding.setLifecycleOwner(this);

        getStudents();
        startRecycler();

        binding.imageViewBack.setOnClickListener(v -> onBackPressed());
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

    }

    private void startRecycler() {
        SetResultTestAdapter adapter = new SetResultTestAdapter(this, students);
        RecyclerView recyclerView = findViewById(R.id.recycler_result_test);
        recyclerView.setAdapter(adapter);

        //criar layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getStudents() {
        UserEntity student1 = new UserEntity("Eduardo");
        UserEntity student2 = new UserEntity("Augusto");

        students.add(student1);
        students.add(student2);
    }

}
