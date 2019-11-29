package online.vidacademica.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import online.vidacademica.core.ResponseModel;
import online.vidacademica.entities.UserEntity;
import online.vidacademica.repositories.UserRepository;

public class RegisterViewModel extends AndroidViewModel {

    private static final Integer CREATED = 201;

    private UserRepository userRepository;

    public UserEntity userEntity = new UserEntity();
    public LiveData<ResponseModel<UserEntity>> userEntityResponse;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
    }

    public void register() {
        userEntityResponse = userRepository.registerUser(userEntity);
    }

    public LiveData<Boolean> isRegistred() {
        MutableLiveData<Boolean> response = new MutableLiveData<>();

        response.setValue(Boolean.FALSE);
        if (!(userEntityResponse == null) && !(userEntityResponse.getValue() == null) && (userEntityResponse.getValue().getCode() == CREATED)) {
            response.setValue(Boolean.TRUE);
        }
        return response;
    }
}