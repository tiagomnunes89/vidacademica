package online.vidacademica.repositories.network.vidacademica.services;

import online.vidacademica.entities.TestEntity;
import online.vidacademica.entities.TokenEntity;
import online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TestService {

    @POST(VidAcademicaWSConstants.TEST_PATH)
    Call<TestEntity> insert(@Header("Authorization") String token, @Body TestEntity testEntity);
}