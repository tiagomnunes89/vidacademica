package online.vidacademica.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import online.vidacademica.core.ResponseModel;
import online.vidacademica.entities.UserDTO;
import online.vidacademica.repositories.UserRepository;

import static online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants.STATUS_CODE_CREATED;

public class RegisterViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public UserDTO userDTO = new UserDTO();

    private MutableLiveData<ResponseModel<UserDTO>> userEntityResponse;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
    }

    public void register() {
        userRepository.registerUser(userDTO, userEntityResponse);
    }

    public LiveData<ResponseModel<UserDTO>> getIsResponseModelLiveData() {
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
}