package online.vidacademica.repositories.network.vidacademica.services;

import java.util.List;

import online.vidacademica.entities.ClassDTO;
import online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClassService {


    @GET(VidAcademicaWSConstants.CLASS_PATH)
    Call<List<ClassDTO>> findAll(@Header("Authorization") String bearerToken);


}
