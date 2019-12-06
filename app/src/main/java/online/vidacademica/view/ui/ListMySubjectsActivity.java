package online.vidacademica.view.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import online.vidacademica.R;
import online.vidacademica.entities.SubjectDTO;
import online.vidacademica.view.adapter.SubjectsAdapter;

public class ListMySubjectsActivity extends AppCompatActivity {

    List<SubjectDTO> subjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_my_subjects);

        // Metodo para buscar Subjects

        getSubjects();

        // inicia recybler view
        startRecycler();
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
        SubjectDTO sub1 = new SubjectDTO("nome1", false,"2019/01/01", "2020/01/01");
        SubjectDTO sub2 = new SubjectDTO("nome2", true,"2019/05/01", "2021/05/01");
        SubjectDTO sub3 = new SubjectDTO("nome3", true,"2019/07/01", "2022/07/01");

        subjects.add(sub1);
        subjects.add(sub2);
        subjects.add(sub3);
    }
}
