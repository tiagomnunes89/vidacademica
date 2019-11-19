package online.vidacademica.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;

import online.vidacademica.api.vidacademica.ApiClient;
import online.vidacademica.api.vidacademica.auth.AuthService;
import online.vidacademica.core.ErrorMessage;
import online.vidacademica.core.ResponseModel;
import online.vidacademica.entities.Email;
import online.vidacademica.entities.TokenEntity;
import online.vidacademica.utils.JsonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TokenRepository {

    private static final String TAG = "TokenRepository";

    private final AuthService authService;

    public TokenRepository() {
        authService = ApiClient.buildService(AuthService.class);
    }

    public LiveData<ResponseModel<TokenEntity>> getToken(Email email, String password) {
        TokenEntity tokenEntity = new TokenEntity.Builder().email(email).password(password).build();
        final MutableLiveData<ResponseModel<TokenEntity>> data = new MutableLiveData<>();

        authService.auth(tokenEntity).enqueue(new Callback<TokenEntity>() {
            @Override
            public void onResponse(Call<TokenEntity> call, Response<TokenEntity> response) {

                ResponseModel<TokenEntity> responseModel = new ResponseModel<>();

                responseModel.setCode(response.code());
                responseModel.setResponse(response.body());

                if (!response.isSuccessful()) {
                    ErrorMessage err = new ErrorMessage(response.code(), JsonUtils.toJson(response.errorBody()));
                    responseModel.setErrorMessage(err);
                }

                data.setValue(responseModel);

            }

            @Override
            public void onFailure(Call<TokenEntity> call, Throwable t) {
                Log.d(TAG, "onFailure: " + Arrays.toString(t.getStackTrace()));
            }
        });

        return data;
    }
}
