package online.vidacademica.view.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityCreateSubjectBinding;
import online.vidacademica.entities.ClassEntity;
import online.vidacademica.presentation.SingletonClassEntity;
import online.vidacademica.utils.Util;
import online.vidacademica.view.adapter.WeekEntriesAdapter;

public class CreateSubjectActivity extends AppCompatActivity {
    private static final String TAG = "CreateSubjectActivity";
    private DatePickerDialog.OnDateSetListener onDateSetListenerStart;
    private DatePickerDialog.OnDateSetListener onDateSetListenerFinal;
    private ActivityCreateSubjectBinding binding;
    private Util util = new Util();
    private ClassEntity classEntity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_subject);
        binding.setLifecycleOwner(this);

        classEntity = new ClassEntity();
        if (SingletonClassEntity.INSTANCE.getClassEntity() == null) {
            SingletonClassEntity.createClassEntity(classEntity);
        }

        binding.layoutCreateSubjectContent.inputStartDate.setOnClickListener(view ->
                util.callDatePickerDialog(CreateSubjectActivity.this, onDateSetListenerStart));

        binding.layoutCreateSubjectContent.inputFinalDate.setOnClickListener(view ->
                util.callDatePickerDialog(CreateSubjectActivity.this, onDateSetListenerFinal));

        onDateSetListenerStart = (datePicker, year, month, day) -> {
            Log.d(TAG, "onDateSet: mm/dd/yyy: " + day + "/" + month + "/" + year);
            String date = day + "/" + month + "/" + year;
            binding.layoutCreateSubjectContent.inputStartDate.setText(date);
        };

        onDateSetListenerFinal = (datePicker, year, month, day) -> {
            Log.d(TAG, "onDateSet: mm/dd/yyy: " + day + "/" + month + "/" + year);
            String date = day + "/" + month + "/" + year;
            binding.layoutCreateSubjectContent.inputFinalDate.setText(date);
        };
        binding.imageViewBack.setOnClickListener(v -> onBackPressed());

        binding.layoutCreateSubjectContent.containerAddSchedule.setOnClickListener(v ->
                startActivity(new Intent(CreateSubjectActivity.this, AddHourClassActivity.class)));

        startRecycler();
    }



    @Override
    protected void onPause() {
        super.onPause();
        startRecycler();
    }

    private void startRecycler() {
        if (SingletonClassEntity.INSTANCE.getClassEntity() != null) {
            WeekEntriesAdapter adapter = new WeekEntriesAdapter(
                    this, SingletonClassEntity.INSTANCE.getClassEntity().getWeekEntries());

            binding.layoutCreateSubjectContent.recycler.setAdapter(adapter);

            //Criar layout manager
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            binding.layoutCreateSubjectContent.recycler.setLayoutManager(linearLayoutManager);
        }
    }
}