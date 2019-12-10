package online.vidacademica.view.ui;

import android.os.Bundle;

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

    ActivitySubjectsStudentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_subjects_student);
        binding.setLifecycleOwner(this);

        getWeekDayDTO();
        starRecycler();
    }

    private void getWeekDayDTO() {
        WeekDayDTO wdEntry1 = new WeekDayDTO("Lógica de programação",
                "Segunda-feira\nTerça-feira\nQuarta-feira",
                "18:45 - 21:30\n21:15 - 22:00\n19:30 - 21:00",
                "");
        WeekDayDTO wdEntry2 = new WeekDayDTO("Fundamentos de Webdesign 2",
                "Segunda-feira\nSexta-feira",
                "21:15 - 22:00\n18:45 - 22:00",
                "");

        List<WeekDayDTO> weekDayDTOListTEMP = new ArrayList<>();

        weekDayDTOList.add(wdEntry1);
        weekDayDTOList.add(wdEntry2);
    }

    private void starRecycler() {
        WeekDayAdapter adapter = new WeekDayAdapter(this, weekDayDTOList);
        RecyclerView recyclerView = findViewById(R.id.subject_student_recycler);
        recyclerView.setAdapter(adapter);

        //criar layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
}
