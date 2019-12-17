package online.vidacademica.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import online.vidacademica.core.ResponseModel;
import online.vidacademica.entities.ClassDTO;
import online.vidacademica.entities.RegistrationDTO;
import online.vidacademica.entities.UserEntity;
import online.vidacademica.repositories.CreateRegistrationRepository;


public class CreateRegistrationViewModel extends AndroidViewModel {

    private CreateRegistrationRepository createRegistrationRepository;


    int registrationResponse;
    private MutableLiveData<ResponseModel<List<UserEntity>>> allUsersResponse;
    private MutableLiveData<ResponseModel<List<UserEntity>>> allUsersClassResponse = new MutableLiveData<>();
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

    public MutableLiveData<ResponseModel<List<UserEntity>>> findUsersByClassId(int id) {
        createRegistrationRepository.findUsersByClassId(allUsersClassResponse,id);
        return allUsersClassResponse;
    }

    public MutableLiveData<ResponseModel<List<UserEntity>>> getAllUsersClassResponse() {
        return allUsersClassResponse;
    }
}

