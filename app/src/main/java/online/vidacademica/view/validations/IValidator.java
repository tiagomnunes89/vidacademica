package online.vidacademica.view.validations;

public interface IValidator {

    boolean isValid();

    boolean isValid(Boolean setError);

    boolean validation(Boolean setError);
}
