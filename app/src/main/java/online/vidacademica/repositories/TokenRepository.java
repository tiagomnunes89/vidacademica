package online.vidacademica.repositories;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import online.vidacademica.core.ErrorMessage;
import online.vidacademica.core.ResponseModel;
import online.vidacademica.entities.TokenEntity;
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

    private TokenRepository(Context context) {
        VidAcademicaLocalDBClient db = VidAcademicaLocalDBClient.getInstance(context);
        dao = db.tokenDao();
        tokenService = VidAcademicaWSClient.buildService(TokenService.class);

        reLogin();
    }

    public static TokenRepository getInstance(Context context) {
        if (instance == null) {
            instance = new TokenRepository(context);
        }
        return instance;
    }

    private void reLogin() {

        try {
            TokenEntity tokenEntity = getTokenSync();

            if (tokenEntity != null) {
                login(tokenEntity);
            }

        } catch (Exception e) {
            Log.e(TAG, "reLogin: Erro ao tentar fazer o login novamente.", e);
        }

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

    public TokenEntity getTokenSync() {

        TokenEntity response = new TokenEntity();

        try {
            response = new FindOneAsync(dao).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return response;
    }

    private class FindOneAsync extends AsyncTask<Void, Void, TokenEntity> {

        private TokenDao dao;

        FindOneAsync(TokenDao dao) {
            this.dao = dao;
        }

        @Override
        protected TokenEntity doInBackground(Void... voids) {
            return dao.findOneSync();
        }
    }

    private void insert(TokenEntity tokenEntity) {
        new InsertAsyncTask(dao).execute(tokenEntity);
    }

    private class InsertAsyncTask extends AsyncTask<TokenEntity, Void, Void> {

        private TokenDao dao;

        InsertAsyncTask(TokenDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(TokenEntity... params) {
            dao.insertAndDelete(params[0]);
            return null;
        }
    }

    public void deleteAll() {
        new DeleteAsyncTask(dao).execute();
    }

    private class DeleteAsyncTask extends AsyncTask<Void, Void, Void> {

        private TokenDao dao;

        DeleteAsyncTask(TokenDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... params) {
            dao.deleteAll();
            return null;
        }
    }
}
