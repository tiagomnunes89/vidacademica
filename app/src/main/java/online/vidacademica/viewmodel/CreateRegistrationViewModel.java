package online.vidacademica.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import online.vidacademica.core.ResponseModel;
import online.vidacademica.databinding.ActivityCreateRegistrationBinding;
import online.vidacademica.entities.UserEntity;
import online.vidacademica.repositories.CreateRegistrationRepository;


public class CreateRegistrationViewModel extends AndroidViewModel {

    private CreateRegistrationRepository createRegistrationRepository;
//    public ActivityCreateRegistrationBinding createRegistrationBinding = new ActivityCreateRegistrationBinding();

    private MutableLiveData<ResponseModel<List<UserEntity>>> allUsersResponse;


    public CreateRegistrationViewModel(@NonNull Application application) {
        super(application);
        createRegistrationRepository = CreateRegistrationRepository.getInstance(application);
    }

    public MutableLiveData<ResponseModel<List<UserEntity>>> getAllUsers() {
        if (allUsersResponse == null) allUsersResponse = new MutableLiveData<>();
        createRegistrationRepository.findAllUsers(allUsersResponse);
        return allUsersResponse;
    }
}

