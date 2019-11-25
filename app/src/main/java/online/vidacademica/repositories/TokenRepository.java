package online.vidacademica.repositories;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.Objects;

import online.vidacademica.core.ErrorMessage;
import online.vidacademica.core.ResponseModel;
import online.vidacademica.entities.TokenEntity;
import online.vidacademica.entities.weak.Email;
import online.vidacademica.repositories.network.vidacademica.VidAcademicaWSClient;
import online.vidacademica.repositories.network.vidacademica.services.TokenService;
import online.vidacademica.repositories.storage.local.VidAcademicaLocalDBClient;
import online.vidacademica.repositories.storage.local.dao.TokenDao;
import online.vidacademica.utils.JsonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TokenRepository {
    private static final String TAG = TokenRepository.class.getSimpleName();

    private static TokenRepository instance;
    private TokenService tokenService;

    private TokenDao dao;

    private LiveData<ResponseModel<TokenEntity>> tokenEntity;

    private TokenRepository(Context context) {
        VidAcademicaLocalDBClient db = VidAcademicaLocalDBClient.getInstance(context);
        dao = db.tokenDao();

        tokenService = VidAcademicaWSClient.buildService(TokenService.class);
    }

    public static TokenRepository getInstance(Context context) {
        if (instance == null) {
            instance = new TokenRepository(context);
        }
        return instance;
    }

    public void login(final TokenEntity tokenEntity) {

        final MutableLiveData<ResponseModel<TokenEntity>> data = new MutableLiveData<>();

        tokenService.auth(tokenEntity).enqueue(new Callback<TokenEntity>() {
            @Override
            public void onResponse(Call<TokenEntity> call, Response<TokenEntity> response) {
                Log.i(TAG, "onResponse: Olha eu aqui");
                ResponseModel<TokenEntity> responseModel = new ResponseModel<>();

                responseModel.setCode(response.code());
                responseModel.setResponse(response.body());

                if (!response.isSuccessful()) {
                    ErrorMessage err = new ErrorMessage(response.code(), JsonUtils.toJson(response.errorBody()));
                    responseModel.setErrorMessage(err);
                }

                data.setValue(responseModel);

                TokenEntity fullTokenEntity = Objects.requireNonNull(data.getValue()).getResponse();
                fullTokenEntity.setPassword(tokenEntity.getPassword());

                insert(fullTokenEntity);

            }

            @Override
            public void onFailure(Call<TokenEntity> call, Throwable t) {
                Log.d(TAG, "onFailure: " + Arrays.toString(t.getStackTrace()));
            }
        });
    }

    public LiveData<TokenEntity> getToken() {
        return dao.findOne();
    }

    private void insert(TokenEntity tokenEntity) {
        new insertAsyncTask(dao).execute(tokenEntity);
    }

    private static class insertAsyncTask extends AsyncTask<TokenEntity, Void, Void> {

        private TokenDao dao;

        insertAsyncTask(TokenDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(TokenEntity... params) {
            dao.deleteAll();
            dao.insert(params[0]);
            return null;
        }
    }
}
