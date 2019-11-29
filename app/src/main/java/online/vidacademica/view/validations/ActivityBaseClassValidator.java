package online.vidacademica.view.validations;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public abstract class ActivityBaseClassValidator extends AppCompatActivity {

    private List<IValidator> validationList = new ArrayList<>();

    public List<IValidator> getValidationList() {
        return validationList;
    }
}
