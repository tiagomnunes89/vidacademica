package online.vidacademica.view.ui;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.databinding.DataBindingUtil;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityAddHourClassBinding;
import online.vidacademica.presentation.SingletonClassEntity;
import online.vidacademica.presentation.WeekEntryPresentation;
import online.vidacademica.presentation.mapper.WeekEntryMapper;
import online.vidacademica.utils.Util;
import online.vidacademica.view.validation.ActivityBaseClassValidator;

public class AddHourClassActivity extends ActivityBaseClassValidator {

    private ActivityAddHourClassBinding binding;
    private TimePickerDialog.OnTimeSetListener onTimeSetListenerStart;
    private TimePickerDialog.OnTimeSetListener onTimeSetListenerEnd;
    private Util util = new Util();
    private WeekEntryPresentation weekEntryPresentationScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_hour_class);
        binding.setLifecycleOwner(this);
        weekEntryPresentationScreen = new WeekEntryPresentation();

        binding.layoutContentAddHourClass.setWeekEntryPresentation(new WeekEntryPresentation());

        binding.layoutContentAddHourClass.editTextStartTime.setOnClickListener(v ->
                util.callTimePickerDialog(AddHourClassActivity.this, onTimeSetListenerStart, "Início da aula:"));

        binding.layoutContentAddHourClass.editTextEndTime.setOnClickListener(v ->
                util.callTimePickerDialog(AddHourClassActivity.this, onTimeSetListenerEnd, "Fim da aula:"));

        onTimeSetListenerStart = (view, hourOfDay, minute) -> {
            String hourClass = hourOfDay + getString(R.string.colon) + minute;
            binding.layoutContentAddHourClass.editTextStartTime.setText(hourClass);
            weekEntryPresentationScreen.setStartTime(hourClass);
        };

        onTimeSetListenerEnd = (view, hourOfDay, minute) -> {
            String hourClass = hourOfDay + getString(R.string.colon) + minute;
            binding.layoutContentAddHourClass.editTextEndTime.setText(hourClass);
            weekEntryPresentationScreen.setStartTime(hourClass);
        };

        binding.imageViewBack.setOnClickListener(imageViewBack ->
                startActivity(new Intent(AddHourClassActivity.this, CreateSubjectActivity.class)));

        binding.btnAddHour.setOnClickListener(v -> {
            if (SingletonClassEntity.INSTANCE.getClassEntity() != null) {
                SingletonClassEntity.INSTANCE.getClassEntity().addEntry(
                        WeekEntryMapper.get().convertFrom(weekEntryPresentationScreen));
            }
            startActivity(new Intent(AddHourClassActivity.this, CreateSubjectActivity.class));
        });

        binding.btnCancel.setOnClickListener(btnCancel ->
                startActivity(new Intent(AddHourClassActivity.this, CreateSubjectActivity.class)));

        binding.layoutContentAddHourClass.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                binding.layoutContentAddHourClass.getWeekEntryPresentation().setDay(selectedItemView.toString());
                weekEntryPresentationScreen.setDay(selectedItemView.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
    }

    @Override
    protected void captureIntent() {

    }

    @Override
    protected void alertYes(int actionCustomIdentifier) {

    }

    @Override
    protected void alertNo(int actionCustomIdentifier) {

    }

    @Override
    protected void observeFields() {

    }

    @Override
    protected void observeActions() {

    }
}