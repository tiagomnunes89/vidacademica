package online.vidacademica.repositories.network.vidacademica.services;

import java.util.List;

import online.vidacademica.entities.UserEntity;
import online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {

    @GET(VidAcademicaWSConstants.USER_PATH)
    Call<List<UserEntity>> getAllUsers(@Header("Authorization") String bearerToken);

    @POST(VidAcademicaWSConstants.USER_PATH)
    Call<UserEntity> registerUser(@Body UserEntity userEntity);

}
