package online.vidacademica.view.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import online.vidacademica.R;
import online.vidacademica.entities.TestResultDTO;
import online.vidacademica.view.adapter.MyScoresPerSubjectAdapter;

public class MyScoresPerSubjectActivity extends AppCompatActivity {

    List<TestResultDTO> testResultDTOList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scores_per_subject);

        getScoresBySubject();

        startRecycler();
    }

    private void startRecycler() {
        MyScoresPerSubjectAdapter adapter = new MyScoresPerSubjectAdapter(this, testResultDTOList);
        RecyclerView recyclerView = findViewById(R.id.recycler_test_per_subject);
        recyclerView.setAdapter(adapter);

        //criar layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getScoresBySubject() {
        TestResultDTO score1 = new TestResultDTO(25.0, Instant.now(), "Prova 1", "PDS1");
        TestResultDTO score2 = new TestResultDTO(35.0, Instant.now(), "Prova 2", "PDS1");

        testResultDTOList.add(score1);
        testResultDTOList.add(score2);
    }
}
