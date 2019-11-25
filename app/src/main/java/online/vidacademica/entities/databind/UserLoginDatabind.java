package online.vidacademica.entities.databind;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import java.io.Serializable;
import java.util.Objects;

public class UserLoginDatabind implements Serializable {
    private static final long serialVersionUID = 5977833862920711908L;

    private String email;
    private String password;

    public UserLoginDatabind() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginDatabind that = (UserLoginDatabind) o;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
