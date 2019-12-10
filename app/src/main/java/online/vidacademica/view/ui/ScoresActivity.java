package online.vidacademica.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityScoresBinding;
import online.vidacademica.entities.ScoreBySubject;
import online.vidacademica.entities.SubjectDTO;
import online.vidacademica.entities.TestResultDTO;
import online.vidacademica.entities.weak.SubjectByCourseDTO;
import online.vidacademica.view.adapter.ScoresAdapter;

public class ScoresActivity extends AppCompatActivity {

    List<ScoreBySubject> scoreBySubjectList = new ArrayList<>();
    List<ScoreBySubject> scoreBySubjectListLogistica = new ArrayList<>();
    List<TestResultDTO> testResultDTOList = new ArrayList<>();
    List<SubjectByCourseDTO> subByCourse = new ArrayList<>();
    List<String> spinnerContent = new ArrayList<>();

    ActivityScoresBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_scores);
        binding.setLifecycleOwner(this);

        getCoursesByOwner();
        generateSubjectByCourse();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,spinnerContent);
        binding.layoutContentMyScores.spinner.setAdapter(adapter);

        getScores();

        binding.layoutContentMyScores.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    startRecycler(scoreBySubjectList);
                } else{
                    startRecycler(scoreBySubjectListLogistica);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    private void startRecycler(List<ScoreBySubject> list) {
        ScoresAdapter adapter = new ScoresAdapter(this, list);
        RecyclerView recyclerView = findViewById(R.id.scores_recycler);
        recyclerView.setAdapter(adapter);

        //criar layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getCoursesByOwner() {
        //Carregar os cursos do aluno para preecher o spinner (findOwnerId)
        // Mock ligação course com subject

        spinnerContent.add(0, "Sistemas para internet");
        spinnerContent.add(1, "Logistica");

    }

    private void generateSubjectByCourse() {
        //Mock Carrega materias de acordo com o curso.
        ScoreBySubject sbs1 = new ScoreBySubject("FW2", 0.0);
        ScoreBySubject sbs2 = new ScoreBySubject("PDS1", 0.0);
        ScoreBySubject sbs3 = new ScoreBySubject("LOG1", 20.0);

        //Mock listas e teste para devolver a lista de subjects pro spinner(somente teste)

            scoreBySubjectList.add(sbs1);
            scoreBySubjectList.add(sbs2);
        scoreBySubjectListLogistica.add(sbs3);

    }

    private void getScores() {

        //Carrega a tabela de testes de acordo com o curso(Este DTO combina 2 tabelas, tb_test e tb_test result)
        TestResultDTO score1 = new TestResultDTO(25.0, Instant.now(), "Prova 1", "PDS1");
        TestResultDTO score2 = new TestResultDTO(35.0, Instant.now(), "Prova 2", "PDS1");
        TestResultDTO score3 = new TestResultDTO(70.0, Instant.now(), "Prova 1", "FW2");

        testResultDTOList.add(score1);
        testResultDTOList.add(score2);
        testResultDTOList.add(score3);

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
