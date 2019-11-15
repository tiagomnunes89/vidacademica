package online.vidacademica.api.vidacademica.auth;

import online.vidacademica.api.vidacademica.ApiConstants;
import online.vidacademica.entities.TokenEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST(ApiConstants.AUTH_PATH)
    Call<TokenEntity> auth(@Body TokenEntity tokenEntity);

}
