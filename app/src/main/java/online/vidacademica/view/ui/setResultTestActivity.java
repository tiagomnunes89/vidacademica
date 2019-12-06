package online.vidacademica.view.ui;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import online.vidacademica.R;
import online.vidacademica.entities.UserEntity;
import online.vidacademica.view.adapter.SetResultTestAdapter;

public class setResultTestActivity extends AppCompatActivity {

    List<UserEntity> students = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_result_test);

        getStudents();
        startRecycler();

    }
    private void startRecycler() {
        SetResultTestAdapter adapter = new SetResultTestAdapter(this, students);
        RecyclerView recyclerView = findViewById(R.id.recycler_result_test);
        recyclerView.setAdapter(adapter);

        //criar layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getStudents() {
        UserEntity student1 = new UserEntity("Eduardo");
        UserEntity student2 = new UserEntity("Augusto");

        students.add(student1);
        students.add(student2);
    }

}
