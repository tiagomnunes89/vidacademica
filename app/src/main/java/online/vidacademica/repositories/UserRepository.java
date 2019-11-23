package online.vidacademica.repositories;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import org.apache.commons.validator.ValidatorException;

import online.vidacademica.api.vidacademica.ApiClient;
import online.vidacademica.api.vidacademica.services.UserService;
import online.vidacademica.core.ErrorMessage;
import online.vidacademica.core.ResponseModel;
import online.vidacademica.entities.UserEntity;
import online.vidacademica.utils.JsonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserRepository {

    private static final String TAG = "UserRepository";

    private final UserService userService;

    public UserRepository() {
        userService = ApiClient.buildService(UserService.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public MutableLiveData<ResponseModel<UserEntity>> getAllUsers() throws ValidatorException {

        final MutableLiveData<ResponseModel<UserEntity>> data = new MutableLiveData<>();

        userService.getAllUsers().enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {

                ResponseModel<UserEntity> responseModel = new ResponseModel<>();

                responseModel.setCode(response.code());
                responseModel.setResponse(response.body());

                if (!response.isSuccessful()) {
                    ErrorMessage err = new ErrorMessage(response.code(), JsonUtils.toJson(response.errorBody()));
                    responseModel.setErrorMessage(err);
                }

                data.setValue(responseModel);

            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {

            }
        });

        return data;
    }
}