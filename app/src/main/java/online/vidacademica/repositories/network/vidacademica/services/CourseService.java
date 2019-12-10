package online.vidacademica.repositories.network.vidacademica.services;

import java.util.List;

import online.vidacademica.entities.CourseDTO;
import online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import static online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants.AUTH_HEADER_KEY;

public interface CourseService {

    @GET(VidAcademicaWSConstants.COURSE_PATH)
    Call<List<CourseDTO>> findAll(@Header(AUTH_HEADER_KEY) String bearerToken);

    @POST(VidAcademicaWSConstants.COURSE_PATH)
    Call<CourseDTO> insert(@Header(AUTH_HEADER_KEY) String bearerToken, @Body CourseDTO couseDTO);

    @PUT(VidAcademicaWSConstants.COURSE_PATH_ID)
    Call<CourseDTO> update(@Header(AUTH_HEADER_KEY) String bearerToken, @Path("id") Integer id, @Body CourseDTO couseDTO);

}
