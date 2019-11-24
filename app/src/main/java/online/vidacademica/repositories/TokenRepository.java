package online.vidacademica.repositories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;

import online.vidacademica.core.ErrorMessage;
import online.vidacademica.core.ResponseModel;
import online.vidacademica.entities.TokenEntity;
import online.vidacademica.entities.weak.Email;
import online.vidacademica.repositories.network.vidacademica.VidAcademicaWSClient;
import online.vidacademica.repositories.network.vidacademica.services.TokenService;
import online.vidacademica.utils.JsonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TokenRepository {
    private static final String TAG = TokenRepository.class.getSimpleName();

    private static TokenRepository instance;
    private final TokenService tokenService;

    private TokenRepository(Context context) {
        tokenService = VidAcademicaWSClient.buildService(TokenService.class);
    }

    public static synchronized TokenRepository getInstance(Context context) {
        if (instance == null) {
            instance = new TokenRepository(context);
        }
        return instance;
    }

    public LiveData<ResponseModel<TokenEntity>> getToken(Email email, String password) {

        TokenEntity tokenEntity = new TokenEntity(email, password);

        final MutableLiveData<ResponseModel<TokenEntity>> data = new MutableLiveData<>();

        tokenService.auth(tokenEntity).enqueue(new Callback<TokenEntity>() {
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
