package online.vidacademica.repositories;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import online.vidacademica.core.ErrorMessage;
import online.vidacademica.core.ResponseModel;
import online.vidacademica.entities.ClassDTO;
import online.vidacademica.entities.ClassEntity;
import online.vidacademica.entities.RegistrationDTO;
import online.vidacademica.entities.TokenEntity;
import online.vidacademica.entities.UserEntity;
import online.vidacademica.repositories.network.vidacademica.VidAcademicaWSClient;
import online.vidacademica.repositories.network.vidacademica.services.ClassService;
import online.vidacademica.repositories.network.vidacademica.services.CourseService;
import online.vidacademica.repositories.network.vidacademica.services.UserService;
import online.vidacademica.utils.JsonUtils;
import online.vidacademica.view.ui.CreateRegistrationActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateRegistrationRepository {
    private static final String TAG = CreateRegistrationRepository.class.getSimpleName();

    private static CreateRegistrationRepository instance;
    private TokenRepository tokenRepository;

    private final ClassService classService;

    private final UserService userService;

    private CreateRegistrationRepository(Context context) {
        tokenRepository = TokenRepository.getInstance(context);
        classService = VidAcademicaWSClient.buildService(ClassService.class);
        userService = VidAcademicaWSClient.buildService(UserService.class);
    }

    public static CreateRegistrationRepository getInstance(Context context) {
        if (instance == null) {
            instance = new CreateRegistrationRepository(context);
        }
        return instance;
    }

    public MutableLiveData<ResponseModel<List<UserEntity>>> findAllUsers(final MutableLiveData<ResponseModel<List<UserEntity>>> mutableLiveDataObject) {

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

    public MutableLiveData<ResponseModel<List<ClassDTO>>> findAllClasses(final MutableLiveData<ResponseModel<List<ClassDTO>>> mutableLiveDataObject) {

        TokenEntity tokenEntity = Optional.of(tokenRepository.getTokenSync()).orElse(new TokenEntity());

        String hash = "";
        if (tokenEntity != null) {
            hash = tokenEntity.getToken();
        }

        classService.findAll(String.format("Bearer %s", hash)).enqueue(new Callback<List<ClassDTO>>() {
            @Override
            public void onResponse(Call<List<ClassDTO>> call, Response<List<ClassDTO>> response) {
                ResponseModel<List<ClassDTO>> responseModel = new ResponseModel<>();

                responseModel.setCode(response.code());
                responseModel.setResponse(response.body());

                if (!response.isSuccessful()) {
                    ErrorMessage err = new ErrorMessage(response.code(), JsonUtils.toJson(response.errorBody()));
                    responseModel.setErrorMessage(err);
                }

                mutableLiveDataObject.setValue(responseModel);
            }

            @Override
            public void onFailure(Call<List<ClassDTO>> call, Throwable t) {
                Log.i(TAG, "onFailure: " + Arrays.toString(t.getStackTrace()));
            }
        });

        return mutableLiveDataObject;

    }

    public void attachStudent(RegistrationDTO registrationDTO) {

        TokenEntity tokenEntity = Optional.of(tokenRepository.getTokenSync()).orElse(new TokenEntity());

        String hash = "";
        if (tokenEntity != null) {
            hash = tokenEntity.getToken();
        }

        Log.println(Log.DEBUG,"TAG",registrationDTO.getUser());
        Log.println(Log.DEBUG,"TAG",registrationDTO.getClasse());


        classService.attachStudent(String.format("Bearer %s", hash), registrationDTO).enqueue(new Callback<RegistrationDTO>() {
            @Override
            public void onResponse(Call<RegistrationDTO> call, Response<RegistrationDTO> response) {
                Log.i(TAG, "Success: " + response.toString());
            }

            @Override
            public void onFailure(Call<RegistrationDTO> call, Throwable t) {
                Log.i(TAG, "onFailure: " + Arrays.toString(t.getStackTrace()));
            }
        });

    }

}
