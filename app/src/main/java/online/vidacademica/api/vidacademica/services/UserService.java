package online.vidacademica.api.vidacademica.services;

import online.vidacademica.api.vidacademica.ApiConstants;
import online.vidacademica.entities.UserEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface UserService {

    @GET(ApiConstants.USER_PATH)
    Call<UserEntity> getAllUsers(@Header("Authorization") String bearerToken);

}
