package online.vidacademica.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import online.vidacademica.R;
import online.vidacademica.entities.CourseDTO;
import online.vidacademica.entities.ScoreBySubject;
import online.vidacademica.entities.SubjectDTO;
import online.vidacademica.entities.TestEntity;
import online.vidacademica.entities.TestResultDTO;
import online.vidacademica.view.adapter.ScoresAdapter;

public class MyScoresActivity extends AppCompatActivity {

    List<ScoreBySubject> scoreBySubjectList = new ArrayList<>();
    List<TestResultDTO> testResultDTOList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scores);

        getScores();

        startRecycler();
    }

    private void startRecycler() {
        ScoresAdapter adapter = new ScoresAdapter(this, scoreBySubjectList);
        RecyclerView recyclerView = findViewById(R.id.scores_recycler);
        recyclerView.setAdapter(adapter);

        //criar layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getScores() {

        //Carrega a tabela de testes de acordo com o curso(Este DTO combina 2 tabelas, tb_test e tb_test result)
        TestResultDTO score1 = new TestResultDTO(25.0, Instant.now(), "Prova 1", "PDS1");
        TestResultDTO score2 = new TestResultDTO(35.0, Instant.now(), "Prova 2", "PDS1");
        TestResultDTO score3 = new TestResultDTO(70.0, Instant.now(), "Prova 1", "FW2");

        testResultDTOList.add(score1);
        testResultDTOList.add(score2);
        testResultDTOList.add(score3);

         //Carrega materias de acordo com o curso.
        ScoreBySubject sbs1 = new ScoreBySubject("FW2", 0.0);
        ScoreBySubject sbs2 = new ScoreBySubject("PDS1", 0.0);

        scoreBySubjectList.add(sbs1);
        scoreBySubjectList.add(sbs2);

        //Calcula nota final de acordo com materia

        for (TestResultDTO score: testResultDTOList) {
            for (ScoreBySubject subject: scoreBySubjectList) {
                if(subject.getSubject().equals(score.getSubjectName())){
                    subject.addToSubjectTotalScore(score.getScore());
                }
            }
        }
    }
}
