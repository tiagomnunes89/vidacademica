package online.vidacademica.view.ui;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityAddHourClassBinding;
import online.vidacademica.utils.Util;
import online.vidacademica.view.validation.ActivityBaseClassValidator;

public class AddHourClassActivity extends ActivityBaseClassValidator {

    private ActivityAddHourClassBinding binding;
    private TimePickerDialog.OnTimeSetListener onTimeSetListenerStart;
    private TimePickerDialog.OnTimeSetListener onTimeSetListenerEnd;
    private Util util = new Util();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_hour_class);
        binding.setLifecycleOwner(this);

        binding.layoutContentAddHourClass.editTextStartTime.setOnClickListener(v ->
                util.callTimePickerDialog(AddHourClassActivity.this, onTimeSetListenerStart, "Início da aula:"));

        binding.layoutContentAddHourClass.editTextEndTime.setOnClickListener(v ->
                util.callTimePickerDialog(AddHourClassActivity.this, onTimeSetListenerEnd, "Fim da aula:"));

        onTimeSetListenerStart = (view, hourOfDay, minute) -> {
            String hourClass = hourOfDay + getString(R.string.colon) + minute;
            binding.layoutContentAddHourClass.editTextStartTime.setText(hourClass);
        };

        onTimeSetListenerEnd = (view, hourOfDay, minute) -> {
            String hourClass = hourOfDay + getString(R.string.colon) + minute;
            binding.layoutContentAddHourClass.editTextEndTime.setText(hourClass);
        };

        binding.imageViewBack.setOnClickListener(v ->
                startActivity(new Intent(AddHourClassActivity.this,CreateSubjectActivity.class)));

        binding.btnAddHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddHourClassActivity.this,CreateSubjectActivity.class));
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