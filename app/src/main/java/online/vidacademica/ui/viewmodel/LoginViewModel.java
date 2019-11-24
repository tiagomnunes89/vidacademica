package online.vidacademica.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import online.vidacademica.repositories.TokenRepository;

public class LoginViewModel extends AndroidViewModel {

    private TokenRepository tokenRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);


    }


}
