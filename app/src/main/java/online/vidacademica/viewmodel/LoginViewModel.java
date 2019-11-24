package online.vidacademica.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.apache.commons.validator.ValidatorException;

import online.vidacademica.core.ResponseModel;
import online.vidacademica.entities.TokenEntity;
import online.vidacademica.entities.UserLoginEntity;
import online.vidacademica.entities.weak.Email;
import online.vidacademica.repositories.TokenRepository;

public class LoginViewModel extends AndroidViewModel {

    private TokenRepository tokenRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        tokenRepository = TokenRepository.getInstance(application);
    }

    public LiveData<ResponseModel<TokenEntity>> login(UserLoginEntity userLoginEntity) throws ValidatorException {
        return login(userLoginEntity.getEmail(), userLoginEntity.getPassword());
    }

    public LiveData<ResponseModel<TokenEntity>> login(String email, String pass) throws ValidatorException {
        Email newEmail = new Email(email);
        return tokenRepository.getToken(newEmail, pass);
    }



}
