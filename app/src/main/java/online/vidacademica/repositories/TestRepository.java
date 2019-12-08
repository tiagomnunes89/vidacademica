package online.vidacademica.repositories;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import online.vidacademica.core.ErrorMessage;
import online.vidacademica.core.ResponseModel;
import online.vidacademica.entities.CourseDTO;
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
    private TokenRepository tokenRepository;

    private TestService testService;



    private TestRepository(Context context) {
        tokenRepository = TokenRepository.getInstance(context);
        testService = VidAcademicaWSClient.buildService(TestService.class);
    }

    public static TestRepository getInstance(Context context) {
        if (instance == null) {
            instance = new TestRepository(context);
        }
        return instance;
    }

    public MutableLiveData<ResponseModel<TestEntity>> insert(TestEntity testEntity, final MutableLiveData<ResponseModel<TestEntity>> mutableLiveDataObject) {

        TokenEntity tokenEntity = Optional.of(tokenRepository.getTokenSync()).orElse(new TokenEntity());

        String hash = "";
        if (tokenEntity != null) {
            hash = tokenEntity.getToken();
        }

        testService.insert(String.format("Bearer %s", hash), testEntity).enqueue(new Callback<TestEntity>() {
            @Override
            public void onResponse(Call<TestEntity> call, Response<TestEntity> response) {
                ResponseModel<TestEntity> responseModel = new ResponseModel<>();

                responseModel.setCode(response.code());
                responseModel.setResponse(response.body());

                if (!response.isSuccessful()) {
                    ErrorMessage err = new ErrorMessage(response.code(), JsonUtils.toJson(response.errorBody()));
                    responseModel.setErrorMessage(err);
                }

                mutableLiveDataObject.setValue(responseModel);
            }

            @Override
            public void onFailure(Call<TestEntity> call, Throwable t) {
                Log.i(TAG, "onFailure: " + Arrays.toString(t.getStackTrace()));
            }
        });

        return mutableLiveDataObject;

    }


}
