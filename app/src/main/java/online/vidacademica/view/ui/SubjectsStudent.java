package online.vidacademica.view.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivitySubjectsStudentBinding;
import online.vidacademica.entities.WeekDayDTO;
import online.vidacademica.view.adapter.WeekDayAdapter;

public class SubjectsStudent extends AppCompatActivity {

    List<WeekDayDTO> weekDayDTOList = new ArrayList<>();
    List<WeekDayDTO> weekDayDTOListLogistica = new ArrayList<>();
    List<String> spinnerContent = new ArrayList<>();

    ActivitySubjectsStudentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_subjects_student);
        binding.setLifecycleOwner(this);

        getWeekDayDTO();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, spinnerContent);
        binding.layoutCreateTestContent.spinnerSubjectStudent.setAdapter(adapter);

        binding.layoutCreateTestContent.spinnerSubjectStudent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){
                    startRecycler(weekDayDTOList);
                } else{
                   startRecycler(weekDayDTOListLogistica);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getWeekDayDTO() {
        spinnerContent.add(0, "Sistemas para internet");
        spinnerContent.add(1, "Logistica");

        WeekDayDTO wdEntry1 = new WeekDayDTO("Lógica de programação",
                "Segunda-feira\nTerça-feira\nQuarta-feira",
                "18:45 - 21:30\n21:15 - 22:00\n19:30 - 21:00",
                "");
        WeekDayDTO wdEntry2 = new WeekDayDTO("Fundamentos de Webdesign 2",
                "Segunda-feira\nSexta-feira",
                "21:15 - 22:00\n18:45 - 22:00",
                "");
        WeekDayDTO wdEntry3 = new WeekDayDTO("Logística for dummies",
                "Segunda-feira\nSexta-feira",
                "21:15 - 22:00\n18:45 - 22:00",
                "");

        weekDayDTOList.add(wdEntry1);
        weekDayDTOList.add(wdEntry2);
        weekDayDTOListLogistica.add(wdEntry3);


    }

    private void startRecycler(List<WeekDayDTO> list) {
        WeekDayAdapter adapter = new WeekDayAdapter(this, list);
        RecyclerView recyclerView = findViewById(R.id.subject_student_recycler);
        recyclerView.setAdapter(adapter);

        //criar layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
}
