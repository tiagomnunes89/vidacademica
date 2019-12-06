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

    private MutableLiveData<ResponseModel<UserEntity>> userEntityResponse;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
    }

    public void register() {
        userRepository.registerUser(userEntity, userEntityResponse);
    }

    public LiveData<ResponseModel<UserEntity>> getIsResponseModelLiveData() {
        if (userEntityResponse == null) userEntityResponse = new MutableLiveData<>();
        return userEntityResponse;
    }

    public boolean isRegistred() {
        boolean response = false;
        if (userEntityResponse != null &&
                userEntityResponse.getValue() != null &&
                userEntityResponse.getValue().getCode() == CREATED) {
            response = true;
        }
        return response;
    }
}