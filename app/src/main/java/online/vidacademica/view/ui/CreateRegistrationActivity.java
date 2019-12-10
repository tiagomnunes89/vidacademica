package online.vidacademica.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

import online.vidacademica.R;
import online.vidacademica.databinding.ActivityCreateRegistrationBinding;
import online.vidacademica.entities.TestEntity;
import online.vidacademica.entities.UserEntity;
import online.vidacademica.view.adapter.StudentsAdapter;
import online.vidacademica.viewmodel.CreateRegistrationViewModel;

import static online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants.STATUS_CODE_OK;

public class CreateRegistrationActivity extends BaseActivity {

    List<UserEntity> students = new ArrayList<>();
    List<UserEntity> registeredStudents = new ArrayList<>();
    List<UserEntity> allUsers = new ArrayList<>();

    private CreateRegistrationViewModel createRegistrationViewModel;
//    private ActivityCreateRegistrationBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_registration);
        createRegistrationViewModel = ViewModelProviders.of(this).get(CreateRegistrationViewModel.class);
//        binding = DataBindingUtil.setContentView(this,R.layout.content_create_registration);
//        binding.setLifecycleOwner(this);
//        binding.setCreateRegistrationViewModel(createRegistrationViewModel);

        // Preencher list do autocomplete quando digitar nome dos alunos(carrega a partir de 3 letras)
        String []students = getStudents();

        for (UserEntity student: allUsers) {
            Log.d("Nome",student.getName());
        }



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
        String [] studentsVect = new String[allUsers.size()];
        int x = 0;
        for (UserEntity student: allUsers) {
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
                        String [] studentsVect = new String[allUsers.size()];
                        int x = 0;
                        for (UserEntity student: allUsers) {
                            studentsVect[x++] = student.getName();
                        }
                        String []students = studentsVect;


                        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.text_input_name_student);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (this,android.R.layout.simple_list_item_1,students);
                        autoCompleteTextView.setAdapter(adapter);
                    } else {
                        showToast("Erro");

                    }

            }
        });

    }
    }
