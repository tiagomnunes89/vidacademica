package online.vidacademica.view.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityListMySubjectsBinding;
import online.vidacademica.entities.SubjectDTO;
import online.vidacademica.view.adapter.SubjectsAdapter;

public class ListMySubjectsActivity extends BaseActivity {

    List<SubjectDTO> subjects = new ArrayList<>();
    ActivityListMySubjectsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_my_subjects);
        binding.setLifecycleOwner(this);

        binding.imageViewBack.setOnClickListener(v -> onBackPressed());

        // Metodo para buscar SubjectsStudent

        getSubjects();

        // inicia recybler view
        startRecycler();
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
        SubjectsAdapter adapter = new SubjectsAdapter(this, subjects);
        RecyclerView recyclerView = findViewById(R.id.subject_recycler);
        recyclerView.setAdapter(adapter);

        //criar layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getSubjects() {
        SubjectDTO sub1 = new SubjectDTO("FW1", false, "2019/01/01", "2020/01/01");
        SubjectDTO sub2 = new SubjectDTO("FW2", true, "2019/05/01", "2021/05/01");
        SubjectDTO sub3 = new SubjectDTO("PDS1", true, "2019/07/01", "2022/07/01");

        subjects.add(sub1);
        subjects.add(sub2);
        subjects.add(sub3);
    }
}
