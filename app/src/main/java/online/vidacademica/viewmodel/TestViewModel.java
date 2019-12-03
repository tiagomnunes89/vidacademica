package online.vidacademica.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import online.vidacademica.core.ResponseModel;
import online.vidacademica.entities.TestEntity;
import online.vidacademica.repositories.TestRepository;

public class TestViewModel extends AndroidViewModel {

    private static final Integer CREATED = 201;


    private TestRepository testRepository;

    public TestEntity testEntity = new TestEntity();

    public LiveData<ResponseModel<TestEntity>> testEntityResponse;


    public TestViewModel(@NonNull Application application) {
        super(application);
        testRepository = TestRepository.getInstance(application);
    }

    public void createTest() {
        testEntityResponse = testRepository.createTest(testEntity);

    }

    public LiveData<Boolean> isRegistred() {
        MutableLiveData<Boolean> response = new MutableLiveData<>();
        response.setValue(Boolean.FALSE);
        if (!(testEntityResponse == null) && !(testEntityResponse.getValue() == null) && (testEntityResponse.getValue().getCode() == CREATED)) {
            response.setValue(Boolean.TRUE);
        }
        return response;
    }
}
