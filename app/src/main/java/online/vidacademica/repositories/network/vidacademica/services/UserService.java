package online.vidacademica.repositories.network.vidacademica.services;

import online.vidacademica.entities.UserEntity;
import online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface UserService {

    @GET(VidAcademicaWSConstants.USER_PATH)
    Call<UserEntity> getAllUsers(@Header("Authorization") String bearerToken);

}
