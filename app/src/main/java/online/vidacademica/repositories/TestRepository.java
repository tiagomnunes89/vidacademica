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
import online.vidacademica.entities.TestEntity;
import online.vidacademica.entities.TokenEntity;
import online.vidacademica.repositories.network.vidacademica.VidAcademicaWSClient;
import online.vidacademica.repositories.network.vidacademica.services.TestService;
import online.vidacademica.repositories.network.vidacademica.services.TokenService;
import online.vidacademica.repositories.storage.local.VidAcademicaLocalDBClient;
import online.vidacademica.repositories.storage.local.dao.TokenDao;
import online.vidacademica.utils.JsonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestRepository {
    private static final String TAG = TestRepository.class.getSimpleName();

    private static TestRepository instance;

    private TestService testService;

    private TokenDao dao;

    private LiveData<ResponseModel<TokenEntity>> tokenEntity;

    private TestRepository(Context context) {
        VidAcademicaLocalDBClient db = VidAcademicaLocalDBClient.getInstance(context);
        dao = db.tokenDao();
        testService = VidAcademicaWSClient.buildService(TestService.class);
    }

    public static TestRepository getInstance(Context context) {
        if (instance == null) {
            instance = new TestRepository(context);
        }
        return instance;
    }

    public MutableLiveData<ResponseModel<TestEntity>>  createTest(final TestEntity testEntity) {

        final MutableLiveData<ResponseModel<TestEntity>> data = new MutableLiveData<>();

        testService.insert("Bearer " + getToken().getValue().getToken(), testEntity).enqueue(new Callback<TestEntity>() {
            @Override
            public void onResponse(Call<TestEntity> call, Response<TestEntity> response) {
                Log.i(TAG, "onResponse: Olha eu aqui");
                ResponseModel<TestEntity> responseModel = new ResponseModel<>();

                responseModel.setCode(response.code());
                responseModel.setResponse(response.body());

                if (!response.isSuccessful()) {
                    ErrorMessage err = new ErrorMessage(response.code(), JsonUtils.toJson(response.errorBody()));
                    responseModel.setErrorMessage(err);
                }

                data.setValue(responseModel);

            }

            @Override
            public void onFailure(Call<TestEntity> call, Throwable t) {
                Log.d(TAG, "onFailure: " + Arrays.toString(t.getStackTrace()));

            }
        });

        return data;
    }

    public LiveData<TokenEntity> getToken() {
        return dao.findOne();
    }

}
