package online.vidacademica.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import online.vidacademica.entities.TokenEntity;
import online.vidacademica.entities.databind.UserLoginDatabind;
import online.vidacademica.repositories.TokenRepository;

public class LoginViewModel extends AndroidViewModel {

    private TokenRepository tokenRepository;

    public UserLoginDatabind userLoginDatabind = new UserLoginDatabind();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        tokenRepository = TokenRepository.getInstance(application);
    }

    public LiveData<TokenEntity> getToken() {
        return tokenRepository.getToken();
    }

    public void login() {
        String email = userLoginDatabind.getEmail();
        String pass = userLoginDatabind.getPassword();
        tokenRepository.login(new TokenEntity(email, pass));
    }

    public void deleteLoginData() {
        tokenRepository.deleteAll();
    }
}