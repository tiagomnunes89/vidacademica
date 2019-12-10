package online.vidacademica.repositories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.apache.commons.validator.ValidatorException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import online.vidacademica.core.ErrorMessage;
import online.vidacademica.core.ResponseModel;
import online.vidacademica.entities.CourseDTO;
import online.vidacademica.entities.TokenEntity;
import online.vidacademica.entities.UserEntity;
import online.vidacademica.repositories.network.vidacademica.VidAcademicaWSClient;
import online.vidacademica.repositories.network.vidacademica.services.UserService;
import online.vidacademica.utils.JsonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserRepository {
    private static final String TAG = UserRepository.class.getSimpleName();

    private static UserRepository instance;
    private TokenRepository tokenRepository;

    private final UserService userService;

    private UserRepository(Context context) {
        tokenRepository = TokenRepository.getInstance(context);
        userService = VidAcademicaWSClient.buildService(UserService.class);
    }

    public static UserRepository getInstance(Context context) {
        if (instance == null) {
            instance = new UserRepository(context);
        }
        return instance;
    }

    public MutableLiveData<ResponseModel<UserEntity>> registerUser(UserEntity userEntity, final MutableLiveData<ResponseModel<UserEntity>> mutableLiveDataObject) {

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

                mutableLiveDataObject.setValue(responseModel);
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                Log.i(TAG, "onFailure: " + Arrays.toString(t.getStackTrace()));
            }
        });

        return mutableLiveDataObject;
    }

    public MutableLiveData<ResponseModel<UserEntity>> update(UserEntity userEntity, final MutableLiveData<ResponseModel<UserEntity>> mutableLiveDataObject) {

        TokenEntity tokenEntity = Optional.of(tokenRepository.getTokenSync()).orElse(new TokenEntity());

        String hash = "";
        if (tokenEntity != null) {
            hash = tokenEntity.getToken();
        }

        userService.update(String.format("Bearer %s", hash), userEntity.getId(), userEntity).enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                ResponseModel<UserEntity> responseModel = new ResponseModel<>();

                responseModel.setCode(response.code());
                responseModel.setResponse(response.body());

                if (!response.isSuccessful()) {
                    ErrorMessage err = new ErrorMessage(response.code(), JsonUtils.toJson(response.errorBody()));
                    responseModel.setErrorMessage(err);
                }

                mutableLiveDataObject.setValue(responseModel);
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                Log.i(TAG, "onFailure: " + Arrays.toString(t.getStackTrace()));
            }
        });

        return mutableLiveDataObject;

    }


    public MutableLiveData<ResponseModel<List<UserEntity>>> findAll(final MutableLiveData<ResponseModel<List<UserEntity>>> mutableLiveDataObject) {

        TokenEntity tokenEntity = Optional.of(tokenRepository.getTokenSync()).orElse(new TokenEntity());

        String hash = "";
        if (tokenEntity != null) {
            hash = tokenEntity.getToken();
        }

        userService.getAllUsers(String.format("Bearer %s", hash)).enqueue(new Callback<List<UserEntity>>() {
            @Override
            public void onResponse(Call<List<UserEntity>> call, Response<List<UserEntity>> response) {
                ResponseModel<List<UserEntity>> responseModel = new ResponseModel<>();

                responseModel.setCode(response.code());
                responseModel.setResponse(response.body());

                if (!response.isSuccessful()) {
                    ErrorMessage err = new ErrorMessage(response.code(), JsonUtils.toJson(response.errorBody()));
                    responseModel.setErrorMessage(err);
                }

                mutableLiveDataObject.setValue(responseModel);
            }

            @Override
            public void onFailure(Call<List<UserEntity>> call, Throwable t) {
                Log.i(TAG, "onFailure: " + Arrays.toString(t.getStackTrace()));
            }
        });

        return mutableLiveDataObject;

    }


    public MutableLiveData<ResponseModel<UserEntity>> self(final MutableLiveData<ResponseModel<UserEntity>> mutableLiveDataObject) {

        TokenEntity tokenEntity = Optional.of(tokenRepository.getTokenSync()).orElse(new TokenEntity());

        String hash = "";
        if (tokenEntity != null) {
            hash = tokenEntity.getToken();
        }

        userService.self(String.format("Bearer %s", hash)).enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                ResponseModel<UserEntity> responseModel = new ResponseModel<>();

                responseModel.setCode(response.code());
                responseModel.setResponse(response.body());

                if (!response.isSuccessful()) {
                    ErrorMessage err = new ErrorMessage(response.code(), JsonUtils.toJson(response.errorBody()));
                    responseModel.setErrorMessage(err);
                }

                mutableLiveDataObject.setValue(responseModel);
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                Log.i(TAG, "onFailure: " + Arrays.toString(t.getStackTrace()));
            }
        });

        return mutableLiveDataObject;

    }
}