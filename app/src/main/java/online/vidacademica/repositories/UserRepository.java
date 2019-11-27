package online.vidacademica.repositories;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import org.apache.commons.validator.ValidatorException;

import java.util.Arrays;

import online.vidacademica.repositories.network.vidacademica.VidAcademicaWSClient;
import online.vidacademica.repositories.network.vidacademica.services.UserService;
import online.vidacademica.core.ErrorMessage;
import online.vidacademica.core.ResponseModel;
import online.vidacademica.entities.UserEntity;
import online.vidacademica.utils.JsonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserRepository {
    private static final String TAG = UserRepository.class.getSimpleName();

    private static UserRepository instance;

    private final UserService userService;

    private UserRepository(Context context) {
        userService = VidAcademicaWSClient.buildService(UserService.class);
    }

    public static UserRepository getInstance(Context context) {
        if (instance == null) {
            instance = new UserRepository(context);
        }
        return instance;
    }

    public MutableLiveData<ResponseModel<UserEntity>> getAllUsers() throws ValidatorException {

        final MutableLiveData<ResponseModel<UserEntity>> data = new MutableLiveData<>();

        userService.getAllUsers("").enqueue(new Callback<UserEntity>() {
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

    public MutableLiveData<ResponseModel<UserEntity>> registerUser(UserEntity userEntity) {

        final MutableLiveData<ResponseModel<UserEntity>> data = new MutableLiveData<>();

        userService.registerUser(userEntity).enqueue(new Callback<UserEntity>() {
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
                Log.i(TAG, "onFailure: " + Arrays.toString(t.getStackTrace()));
            }
        });

        return data;
    }
}