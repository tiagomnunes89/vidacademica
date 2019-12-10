package online.vidacademica.repositories.network.vidacademica.services;

import java.util.List;

import online.vidacademica.entities.UserEntity;
import online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import static online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants.AUTH_HEADER_KEY;

public interface UserService {
    @GET(VidAcademicaWSConstants.USER_PATH)
    Call<List<UserEntity>> getAllUsers(@Header(AUTH_HEADER_KEY) String bearerToken);

    @POST(VidAcademicaWSConstants.USER_PATH)
    Call<UserEntity> registerUser(@Body UserEntity userEntity);

    @PUT(VidAcademicaWSConstants.USER_PATH_ID)
    Call<UserEntity> update(@Header(AUTH_HEADER_KEY) String bearerToken, @Path("id") Long userId, @Body UserEntity userEntity);

    @GET(VidAcademicaWSConstants.USER_PATH)
    Call<UserEntity> self(@Header(AUTH_HEADER_KEY) String bearerToken);
}
