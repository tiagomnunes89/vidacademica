package online.vidacademica.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityCreateRegistrationBinding;
import online.vidacademica.entities.ClassDTO;
import online.vidacademica.entities.RegistrationDTO;
import online.vidacademica.entities.UserEntity;
import online.vidacademica.view.adapter.StudentsAdapter;
import online.vidacademica.viewmodel.CreateRegistrationViewModel;

import static online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants.STATUS_CODE_OK;

public class CreateRegistrationActivity extends BaseActivity {

    List<UserEntity> registeredStudents = new ArrayList<>();
    List<UserEntity> allUsers = new ArrayList<>();
    List<ClassDTO> allClasses = new ArrayList<>();
    RegistrationDTO registrationDTO = new RegistrationDTO();

    private CreateRegistrationViewModel createRegistrationViewModel;
    private ActivityCreateRegistrationBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_registration);
        createRegistrationViewModel = ViewModelProviders.of(this).get(CreateRegistrationViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_registration);
        binding.setLifecycleOwner(this);
//        binding.setCreateRegistrationViewModel(createRegistrationViewModel);

        binding.imageViewBack.setOnClickListener(v -> onBackPressed());


        //Preencher a lista dos ja matriculados
        getRegisteredStudents();
        observeActions();
        startRecycler();
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

    private void startRecycler() {
        StudentsAdapter adapter = new StudentsAdapter(this, registeredStudents);
        RecyclerView recyclerView = findViewById(R.id.student_recycler);
        recyclerView.setAdapter(adapter);

        //Criar layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private String[] getStudents() {

        //-------------------
        // O adapter utilizado pra preencher a lista do autocomplete é padrao do android e recebe vetor de strings, por isso o codigo abaixo;
        String[] studentsVect = new String[allUsers.size()];
        int x = 0;
        for (UserEntity student : allUsers) {
            studentsVect[x++] = student.getName();
        }
        return studentsVect;
    }

    private void getRegisteredStudents() {

        //Mock Students já registrados na classe.
        UserEntity stu1 = new UserEntity(null, "Carlos", "email", "1978/11/16", "bla", "bla");
        UserEntity stu2 = new UserEntity(null, "DuduzeraComboy", "email", "1978/11/16", "bla", "bla");
        UserEntity stu3 = new UserEntity(null, "SoteroOverflow", "email", "1978/11/16", "bla", "bla");
        UserEntity stu4 = new UserEntity(null, "Tiaguera", "email", "1978/11/16", "bla", "bla");

        registeredStudents.add(stu1);
        registeredStudents.add(stu2);
        registeredStudents.add(stu3);
        registeredStudents.add(stu4);
    }

    protected void observeActions() {
        createRegistrationViewModel.getAllUsers().observe(this, allUsersResponse -> {
            if (allUsersResponse != null) {
                if (allUsersResponse.getCode() == STATUS_CODE_OK) {
                    allUsers.addAll(allUsersResponse.getResponse());
                    String[] studentsVect = new String[allUsers.size()];
                    int x = 0;
                    for (UserEntity student : allUsers) {
                        studentsVect[x++] = student.getName();
                    }
                    String[] students = studentsVect;


                    AutoCompleteTextView autoCompleteTextView = findViewById(R.id.text_input_name_student);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                            (this, android.R.layout.simple_list_item_1, students);
                    autoCompleteTextView.setAdapter(adapter);
                } else {
                    showToast("Erro");

                }

            }
        });

        createRegistrationViewModel.getAllClasses().observe(this, allClasssesResponse -> {
            if (allClasssesResponse != null) {
                if (allClasssesResponse.getCode() == STATUS_CODE_OK) {
                    allClasses.addAll(allClasssesResponse.getResponse());
                    String[] classeVect = new String[allClasses.size()];
                    int x = 0;
                    for (ClassDTO classe : allClasses) {
                        classeVect[x++] = classe.getName();
                    }
                    String[] classes = classeVect;
                    Spinner spinnerclasses = findViewById(R.id.select_class);

                    ArrayAdapter adapterSpinner = new ArrayAdapter(CreateRegistrationActivity.this, android.R.layout.simple_list_item_1, classes);

                    spinnerclasses.setAdapter(adapterSpinner);

                    spinnerclasses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            registrationDTO.setClasse(parent.getItemAtPosition(position).toString());

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                } else {
                    showToast("Erro");

                }

            }
        });


    }

    public void attachStudent(View view) {
        AutoCompleteTextView name_student = findViewById(R.id.text_input_name_student);
        registrationDTO.setUser(name_student.getText().toString());
        createRegistrationViewModel.attachStudent(registrationDTO);

        startActivity(new Intent(CreateRegistrationActivity.this, CreateRegistrationActivity.class));
    }
}
