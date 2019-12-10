package online.vidacademica.view.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityScoreDetailsBinding;
import online.vidacademica.entities.WeekDayDTO;

public class SubjectsStudentActivity extends AppCompatActivity {

    List<WeekDayDTO> weekDayDTOList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_student);
    }
}
