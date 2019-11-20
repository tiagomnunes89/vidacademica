package online.vidacademica.repositories;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import org.apache.commons.validator.ValidatorException;

import java.time.Instant;

import online.vidacademica.api.vidacademica.ApiClient;
import online.vidacademica.api.vidacademica.services.UserService;
import online.vidacademica.core.ResponseModel;
import online.vidacademica.entities.Email;
import online.vidacademica.entities.UserEntity;


public class UserRepository {

    private static final String TAG = "UserRepository";

    private final UserService userService;

    public UserRepository() {
        userService = ApiClient.buildService(UserService.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public MutableLiveData<ResponseModel<UserEntity>> getAllUsers() throws ValidatorException {

        UserEntity userEntity = UserEntity
                .builder(1L)
                .withName("Teste")
                .withBirthday(Instant.now())
                .withMail(new Email("sotero@gmail.com"))
                .withSocialId("15302123")
                .build();

        final MutableLiveData<ResponseModel<UserEntity>> data = new MutableLiveData<>();



        return data;
    }
}