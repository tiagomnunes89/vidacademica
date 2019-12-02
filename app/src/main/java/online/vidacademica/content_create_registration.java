package online.vidacademica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import online.vidacademica.entities.SubjectDTO;
import online.vidacademica.entities.UserEntity;
import online.vidacademica.view.adapter.SubjectsAdapter;
import online.vidacademica.view.adapter.UserAdapter;

public class content_create_registration extends AppCompatActivity {

        List<UserEntity> users = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_create_registration);

            // Metodo para buscar estudantes

            getStudents();
         // getClasses();
            // inicia recybler view
            startRecycler();
        }


    private void startRecycler() {
            UserAdapter adapter = new UserAdapter(this, users);
            RecyclerView recyclerView = findViewById(R.id.student_recycler);
            recyclerView.setAdapter(adapter);

            //criar layout manager
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
        }

        private void getStudents() {

           UserEntity user1 = new UserEntity(null, "Name1", "p@gmail.com", "2000/12/12", "socialId", "password");
           UserEntity user2 = new UserEntity(null, "Name3", "p@gmail.com", "2000/07/13", "socialId", "password");
           UserEntity user3 = new UserEntity(null, "Name5 nanoanoanaoanoa", "p@gmail.com", "2000/03/12", "socialId", "password");

            users.add(user1);
            users.add(user2);
            users.add(user3);
        }

    private void getClasses() {

    }

    }
