package online.vidacademica.entities.weak;

import org.apache.commons.validator.ValidatorException;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;

public class Email {

    private String email;

    public Email(String email) throws ValidatorException {
        setEmail(email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws ValidatorException {
        if (EmailValidator.getInstance().isValid(email))
            this.email = email;
        else
            throw new ValidatorException(String.format("This %s mail not has a valida sintaxe.", email));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return email.equals(email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
