package online.vidacademica.view.validation;

import java.util.ArrayList;
import java.util.List;

import online.vidacademica.view.ui.BaseActivity;

public abstract class ActivityBaseClassValidator extends BaseActivity {

    private List<IValidator> validationList = new ArrayList<>();

    public List<IValidator> getValidationList() {
        return validationList;
    }
}