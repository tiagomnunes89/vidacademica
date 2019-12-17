package online.vidacademica.repositories.network.vidacademica.services;

import java.util.List;

import online.vidacademica.entities.ClassDTO;
import online.vidacademica.entities.RegistrationDTO;
import online.vidacademica.entities.UserEntity;
import online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants.AUTH_HEADER_KEY;

public interface ClassService {


    @GET(VidAcademicaWSConstants.CLASS_PATH)
    Call<List<ClassDTO>> findAll(@Header("Authorization") String bearerToken);

    @POST(VidAcademicaWSConstants.CLASS_PATH_ATTACH_STUDENT)
    Call <RegistrationDTO> attachStudent(@Header("Authorization") String bearerToken, @Body RegistrationDTO registrationDTO);

    @GET(VidAcademicaWSConstants.CLASS_PATH_STUDENTS)
    Call<List<UserEntity>> findUsersByClassId(@Header(AUTH_HEADER_KEY) String bearerToken, @Path("id") Integer id);

}
