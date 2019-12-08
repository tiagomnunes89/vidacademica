package online.vidacademica.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import online.vidacademica.R;
import online.vidacademica.entities.UserEntity;
import online.vidacademica.view.adapter.StudentsAdapter;

public class CreateRegistrationActivity extends AppCompatActivity {

    List<UserEntity> students = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_registration);

        getStudents();

        startRecycler();
    }

    private void startRecycler() {
        StudentsAdapter adapter = new StudentsAdapter(this, students);
        RecyclerView recyclerView = findViewById(R.id.student_recycler);
        recyclerView.setAdapter(adapter);

        //Criar layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getStudents() {
        UserEntity stu1 = new UserEntity(null, "Carlos", "email", "1978/11/16", "bla", "bla");
        UserEntity stu2 = new UserEntity(null, "DuduzeraComboy", "email", "1978/11/16", "bla", "bla");
        UserEntity stu3 = new UserEntity(null, "SoteroOverflow", "email", "1978/11/16", "bla", "bla");
        UserEntity stu4 = new UserEntity(null, "Tiaguera", "email", "1978/11/16", "bla", "bla");

        students.add(stu1);
        students.add(stu2);
        students.add(stu3);
        students.add(stu4);
    }
}
