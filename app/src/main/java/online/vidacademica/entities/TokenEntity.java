package online.vidacademica.entities;

import java.io.Serializable;
import java.util.Objects;

public class TokenEntity implements Serializable {
    private static final long serialVersionUID = 1357370471500938070L;

    private String email;
    private String password;
    private String token;
    private String role;

    public TokenEntity() {
    }

    public TokenEntity(String email, String password, String token, String role) {
        this.email = email;
        this.password = password;
        this.token = token;
        this.role = role;
    }

    public TokenEntity(Builder builder) {
        this.email = builder.email;
        this.password = builder.password;
        this.token = builder.token;
        this.role = builder.role;
    }

    public static class Builder {
        private String email;
        private String password;
        private String token;
        private String role;

        public Builder email(Email email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public TokenEntity build() {
            return new TokenEntity(this);
        }
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
