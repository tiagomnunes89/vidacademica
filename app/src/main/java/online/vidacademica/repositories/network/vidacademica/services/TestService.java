package online.vidacademica.repositories.network.vidacademica.services;

import java.util.List;

import online.vidacademica.entities.TestEntity;
import online.vidacademica.entities.TokenEntity;
import online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

import static online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants.AUTH_HEADER_KEY;

public interface TestService {
    @GET(VidAcademicaWSConstants.COURSE_PATH)
    Call<List<TestEntity>> getAllUsers(@Header(AUTH_HEADER_KEY) String bearerToken);

    @POST(VidAcademicaWSConstants.TEST_PATH)
    Call<TestEntity> insert(@Header(AUTH_HEADER_KEY) String token, @Body TestEntity testEntity);
}