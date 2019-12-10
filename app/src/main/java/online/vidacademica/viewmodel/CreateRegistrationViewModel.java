package online.vidacademica.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import online.vidacademica.core.ResponseModel;
import online.vidacademica.databinding.ActivityCreateRegistrationBinding;
import online.vidacademica.entities.ClassDTO;
import online.vidacademica.entities.RegistrationDTO;
import online.vidacademica.entities.UserEntity;
import online.vidacademica.repositories.CreateRegistrationRepository;

import static online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants.STATUS_CODE_CREATED;


public class CreateRegistrationViewModel extends AndroidViewModel {

    private CreateRegistrationRepository createRegistrationRepository;


    int registrationResponse;
    private MutableLiveData<ResponseModel<List<UserEntity>>> allUsersResponse;
    private MutableLiveData<ResponseModel<List<ClassDTO>>> allClassesResponse;


    public CreateRegistrationViewModel(@NonNull Application application) {
        super(application);
        createRegistrationRepository = CreateRegistrationRepository.getInstance(application);
    }

    public MutableLiveData<ResponseModel<List<UserEntity>>> getAllUsers() {
        if (allUsersResponse == null) allUsersResponse = new MutableLiveData<>();
        createRegistrationRepository.findAllUsers(allUsersResponse);
        return allUsersResponse;
    }

    public MutableLiveData<ResponseModel<List<ClassDTO>>> getAllClasses() {
        if (allClassesResponse == null) allClassesResponse = new MutableLiveData<>();
        createRegistrationRepository.findAllClasses(allClassesResponse);
        return allClassesResponse;
    }


    public void attachStudent(RegistrationDTO registrationDTO) {
        createRegistrationRepository.attachStudent(registrationDTO);
    }


}

