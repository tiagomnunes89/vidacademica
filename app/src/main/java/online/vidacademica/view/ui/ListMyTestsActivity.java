package online.vidacademica.view.ui;

import android.content.Intent;
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
import online.vidacademica.entities.TestEntity;
import online.vidacademica.view.adapter.TestsAdapter;

public class ListMyTestsActivity extends AppCompatActivity {

    List<TestEntity> tests = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_my_tests);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListMyTestsActivity.this, CreateTestActivity.class));
            }
        });

        getTests();
        startRecycler();
    }


    private void startRecycler() {
        TestsAdapter adapter = new TestsAdapter(this, tests);
        RecyclerView recyclerView = findViewById(R.id.recycler_test);
        recyclerView.setAdapter(adapter);

        //criar layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getTests() {
        TestEntity test1 = new TestEntity("Prova 2 de Banco de Dados",23.00,"2019/09/06");
        TestEntity test2 = new TestEntity("Prova 1 de Banco de Dados",25.00,"2019/06/06");

        tests.add(test1);
        tests.add(test2);
    }
}
