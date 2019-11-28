package online.vidacademica.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "token")
public class TokenEntity implements Serializable {
    private static final long serialVersionUID = 1357370471500938070L;

    @PrimaryKey
    @NonNull
    private String email;
    private String password;
    private String token;
    private String role;

    public TokenEntity() {
    }

    @Ignore
    public TokenEntity(String email, String password, String token, String role) {
        this.email = email;
        this.password = password;
        this.token = token;
        this.role = role;
    }

    @Ignore
    public TokenEntity(String email, String password) {
        this.email = email;
        this.password = password;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenEntity that = (TokenEntity) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(token, that.token) &&
                role.equals(that.role);
    }


    @Override
    public int hashCode() {
        return Objects.hash(email, password, token, role);
    }
}