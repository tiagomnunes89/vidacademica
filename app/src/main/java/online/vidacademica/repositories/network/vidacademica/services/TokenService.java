package online.vidacademica.repositories.network.vidacademica.services;

import online.vidacademica.entities.TokenEntity;
import online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TokenService {

    @POST(VidAcademicaWSConstants.AUTH_PATH_LOGIN)
    Call<TokenEntity> auth(@Body TokenEntity tokenEntity);
}