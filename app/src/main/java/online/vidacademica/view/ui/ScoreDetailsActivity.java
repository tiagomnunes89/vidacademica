package online.vidacademica.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityScoreDetailsBinding;
import online.vidacademica.entities.TestResultDTO;
import online.vidacademica.view.adapter.ScoreDetailsAdapter;

public class ScoreDetailsActivity extends BaseActivity {

    List<TestResultDTO> testResultDTOList = new ArrayList<>();

    ActivityScoreDetailsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_score_details);
        binding.setLifecycleOwner(this);

        getScoresBySubject();
        //enviando o subject name para a score details activity
        binding.layoutContentMyScores.titleMyScoresPerSubject.setText(testResultDTOList.get(0).getSubjectName());

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

        ScoreDetailsAdapter adapter = new ScoreDetailsAdapter(this, testResultDTOList);
        RecyclerView recyclerView = findViewById(R.id.recycler_test_per_subject);
        recyclerView.setAdapter(adapter);

        //criar layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getScoresBySubject() {

        //Moch de provas(testes) por subject (precisa ter o nome do subject o na tabela teste nao tem
        TestResultDTO score1 = new TestResultDTO(25.0, Instant.now(), "Prova 1", "PDS1");
        TestResultDTO score2 = new TestResultDTO(35.0, Instant.now(), "Prova 2", "PDS1");

        testResultDTOList.add(score1);
        testResultDTOList.add(score2);
    }
}
