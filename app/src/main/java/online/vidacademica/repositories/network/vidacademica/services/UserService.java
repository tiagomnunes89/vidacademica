package online.vidacademica.repositories.network.vidacademica.services;

import java.util.List;

import online.vidacademica.entities.UserDTO;
import online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {

    @GET(VidAcademicaWSConstants.USER_PATH)
    Call<List<UserDTO>> getAllUsers(@Header("Authorization") String bearerToken);

    @POST(VidAcademicaWSConstants.USER_PATH)
    Call<UserDTO> registerUser(@Body UserDTO userDTO);

}
