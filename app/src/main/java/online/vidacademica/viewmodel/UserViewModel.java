package online.vidacademica.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import online.vidacademica.core.ResponseModel;
import online.vidacademica.entities.UserEntity;
import online.vidacademica.repositories.UserRepository;

import static online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants.STATUS_CODE_CREATED;
import static online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants.STATUS_CODE_OK;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public UserEntity userEntity = new UserEntity();

    private MutableLiveData<ResponseModel<UserEntity>> userEntityResponse;

    public UserViewModel(@NonNull Application application) {
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
                userEntityResponse.getValue().getCode() == STATUS_CODE_CREATED) {
            response = true;
        }
        return response;
    }

    public boolean isUpdated() {
        boolean response = false;
        if (userEntityResponse != null &&
                userEntityResponse.getValue() != null &&
                userEntityResponse.getValue().getCode() == STATUS_CODE_OK) {
            response = true;
        }
        return response;
    }

    public void self() {
        userRepository.self(userEntity, userEntityResponse);
    }

    public void update() {
        userRepository.update(userEntity, userEntityResponse);
    }
}