package online.vidacademica.api.vidacademica.services;

import online.vidacademica.api.vidacademica.ApiConstants;
import online.vidacademica.entities.UserEntity;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {


    @GET(ApiConstants.USER_PATH)
    Call<UserEntity> getAllUsers();
}
