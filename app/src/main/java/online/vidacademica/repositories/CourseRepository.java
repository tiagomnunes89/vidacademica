package online.vidacademica.repositories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import online.vidacademica.core.ErrorMessage;
import online.vidacademica.core.ResponseModel;
import online.vidacademica.entities.CourseDTO;
import online.vidacademica.entities.TokenEntity;
import online.vidacademica.repositories.network.vidacademica.VidAcademicaWSClient;
import online.vidacademica.repositories.network.vidacademica.services.CourseService;
import online.vidacademica.utils.JsonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseRepository {
    private static final String TAG = CourseRepository.class.getSimpleName();

    private static CourseRepository instance;
    private TokenRepository tokenRepository;

    private final CourseService courseService;

    private CourseRepository(Context context) {
        tokenRepository = TokenRepository.getInstance(context);
        courseService = VidAcademicaWSClient.buildService(CourseService.class);
    }

    public static CourseRepository getInstance(Context context) {
        if (instance == null) {
            instance = new CourseRepository(context);
        }
        return instance;
    }

    public MutableLiveData<ResponseModel<CourseDTO>> insert(CourseDTO courseDTO, final MutableLiveData<ResponseModel<CourseDTO>> mutableLiveDataObject) {

        TokenEntity tokenEntity = Optional.of(tokenRepository.getTokenSync()).orElse(new TokenEntity());

        String hash = "";
        if (tokenEntity != null) {
            hash = tokenEntity.getToken();
        }

        courseService.insert(String.format("Bearer %s", hash), courseDTO).enqueue(new Callback<CourseDTO>() {
            @Override
            public void onResponse(Call<CourseDTO> call, Response<CourseDTO> response) {
                ResponseModel<CourseDTO> responseModel = new ResponseModel<>();

                responseModel.setCode(response.code());
                responseModel.setResponse(response.body());

                if (!response.isSuccessful()) {
                    ErrorMessage err = new ErrorMessage(response.code(), JsonUtils.toJson(response.errorBody()));
                    responseModel.setErrorMessage(err);
                }

                mutableLiveDataObject.setValue(responseModel);
            }

            @Override
            public void onFailure(Call<CourseDTO> call, Throwable t) {
                Log.i(TAG, "onFailure: " + Arrays.toString(t.getStackTrace()));
            }
        });

        return mutableLiveDataObject;

    }

    public MutableLiveData<ResponseModel<List<CourseDTO>>> findAll(final MutableLiveData<ResponseModel<List<CourseDTO>>> mutableLiveDataObject) {

        TokenEntity tokenEntity = Optional.of(tokenRepository.getTokenSync()).orElse(new TokenEntity());

        String hash = "";
        if (tokenEntity != null) {
            hash = tokenEntity.getToken();
        }

        courseService.findAll(String.format("Bearer %s", hash)).enqueue(new Callback<List<CourseDTO>>() {
            @Override
            public void onResponse(Call<List<CourseDTO>> call, Response<List<CourseDTO>> response) {
                ResponseModel<List<CourseDTO>> responseModel = new ResponseModel<>();

                responseModel.setCode(response.code());
                responseModel.setResponse(response.body());

                if (!response.isSuccessful()) {
                    ErrorMessage err = new ErrorMessage(response.code(), JsonUtils.toJson(response.errorBody()));
                    responseModel.setErrorMessage(err);
                }

                mutableLiveDataObject.setValue(responseModel);
            }

            @Override
            public void onFailure(Call<List<CourseDTO>> call, Throwable t) {
                Log.i(TAG, "onFailure: " + Arrays.toString(t.getStackTrace()));
            }
        });

        return mutableLiveDataObject;

    }

}
