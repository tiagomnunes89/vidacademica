package online.vidacademica.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import online.vidacademica.R;
import online.vidacademica.entities.TestResultDTO;
import online.vidacademica.view.adapter.ScoresAdapter;

public class MyScoresActivity extends AppCompatActivity {

    List<TestResultDTO> testResultDTOList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scores);

        //getScores

        getScores();

        startRecycler();
    }

    private void startRecycler() {
        ScoresAdapter adapter = new ScoresAdapter(this, testResultDTOList);
        RecyclerView recyclerView = findViewById(R.id.scores_recycler);
        recyclerView.setAdapter(adapter);

        //criar layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getScores() {

        TestResultDTO score1 = new TestResultDTO(20.0, Instant.now(), 1L, 1L);
        TestResultDTO score2 = new TestResultDTO(35.0, Instant.now(), 1L, 2L);
        TestResultDTO score3 = new TestResultDTO(45.0, Instant.now(), 1L, 3L);

        testResultDTOList.add(score1);
        testResultDTOList.add(score2);
        testResultDTOList.add(score3);
    }
}
