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
        WeekDayDTO wdEntry1 = new WeekDayDTO("FW2", "Segunda-feira","2018-01-01T18:45:00Z", "2018-01-01T19:30:00Z");
        WeekDayDTO wdEntry2 = new WeekDayDTO("FW2", "Segunda-feira", "2018-01-01T19:30:00Z", "2018-01-01T20:15:00Z");
        WeekDayDTO wdEntry3 = new WeekDayDTO("FW2", "Terça-feira", "2018-01-02T18:45:00Z", "2018-01-01T19:30:00Z");
        WeekDayDTO wdEntry4 = new WeekDayDTO("FW2", "Terça-feira", "2018-01-02T19:30:00Z", "2018-01-01T20:15:00Z");
        WeekDayDTO wdEntry5 = new WeekDayDTO("PDS1", "Segunda-feira", "2018-01-01T20:15:00Z", "2018-01-01T21:00:00Z");
        WeekDayDTO wdEntry6 = new WeekDayDTO("PDS1", "Segunda-feira", "2018-01-01T21:15:00Z", "2018-01-01T22:00:00Z");

        List<WeekDayDTO> weekDayDTOListTEMP = new ArrayList<>();

        weekDayDTOListTEMP.add(wdEntry1);
        weekDayDTOListTEMP.add(wdEntry2);
        weekDayDTOListTEMP.add(wdEntry3);
        weekDayDTOListTEMP.add(wdEntry4);
        weekDayDTOListTEMP.add(wdEntry5);
        weekDayDTOListTEMP.add(wdEntry6);
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
