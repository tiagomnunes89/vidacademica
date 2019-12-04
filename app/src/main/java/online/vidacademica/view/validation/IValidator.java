package online.vidacademica.view.validation;

public interface IValidator {

    boolean isValid();

    boolean isValid(Boolean setError);

    boolean validation(Boolean setError);
}