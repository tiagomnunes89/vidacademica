package online.vidacademica.entities;

import java.io.Serializable;
import java.util.Objects;

public class UserLoginEntity implements Serializable {
    private static final long serialVersionUID = 5977833862920711908L;

    private String email;
    private String password;

    public UserLoginEntity() {
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
        UserLoginEntity that = (UserLoginEntity) o;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
