package online.vidacademica.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

import online.vidacademica.R;
import online.vidacademica.entities.UserEntity;
import online.vidacademica.view.adapter.StudentsAdapter;

public class CreateRegistrationActivity extends AppCompatActivity {

    List<UserEntity> students = new ArrayList<>();
    List<UserEntity> registeredStudents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_registration);

        // Preencher list do autocomplete quando digitar nome dos alunos(carrega a partir de 3 letras)
        String students [] = getStudents();

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.text_input_name_student);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,students);
        autoCompleteTextView.setAdapter(adapter);

        //Preencher a lista dos ja matriculados
        getRegisteredStudents();

        startRecycler();
    }

    private void startRecycler() {
        StudentsAdapter adapter = new StudentsAdapter(this, registeredStudents);
        RecyclerView recyclerView = findViewById(R.id.student_recycler);
        recyclerView.setAdapter(adapter);

        //Criar layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    private String[] getStudents() {

        //MockStudentsQuePodemSerMatriculados(criados no sistema)
        UserEntity stu1 = new UserEntity(null, "CarlosADD", "email", "1978/11/16", "bla", "bla");
        UserEntity stu2 = new UserEntity(null, "CarlosComboyADD", "email", "1978/11/16", "bla", "bla");
        UserEntity stu3 = new UserEntity(null, "SoteroOverflowADD", "email", "1978/11/16", "bla", "bla");
        students.add(stu1);
        students.add(stu2);
        students.add(stu3);
        //-------------------
        // O adapter utilizado pra preencher a lista do autocomplete é padrao do android e recebe vetor de strings, por isso o codigo abaixo;
        String [] studentsVect = new String[students.size()];
        int x = 0;
        for (UserEntity student: students) {
            studentsVect[x++] = student.getName();
        }
        return studentsVect;
    }

    private void getRegisteredStudents() {

        //Mock Students já registrados na classe.
        UserEntity stu1 = new UserEntity(null, "Carlos", "email", "1978/11/16", "bla", "bla");
        UserEntity stu2 = new UserEntity(null, "DuduzeraComboy", "email", "1978/11/16", "bla", "bla");
        UserEntity stu3 = new UserEntity(null, "SoteroOverflow", "email", "1978/11/16", "bla", "bla");
        UserEntity stu4 = new UserEntity(null, "Tiaguera", "email", "1978/11/16", "bla", "bla");

        registeredStudents.add(stu1);
        registeredStudents.add(stu2);
        registeredStudents.add(stu3);
        registeredStudents.add(stu4);
    }
}
