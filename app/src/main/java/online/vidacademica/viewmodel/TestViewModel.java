package online.vidacademica.viewmodel;

import android.app.Application;
import android.icu.text.SimpleDateFormat;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import online.vidacademica.core.ResponseModel;
import online.vidacademica.entities.TestEntity;
import online.vidacademica.repositories.TestRepository;

import static online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants.STATUS_CODE_CREATED;

public class TestViewModel extends AndroidViewModel {



    private TestRepository testRepository;

    public TestEntity testEntity = new TestEntity();

    private MutableLiveData<ResponseModel<TestEntity>> TestEntityResponse;
    private MutableLiveData<List<ResponseModel<TestEntity>>> allTestsResponse;


    public TestViewModel(@NonNull Application application) {
        super(application);
        testRepository = TestRepository.getInstance(application);
    }

    public void createTest() {
        testRepository.insert(testEntity,TestEntityResponse);
    }

    public LiveData<ResponseModel<TestEntity>> getLastTestCreated() {
        if (TestEntityResponse == null) TestEntityResponse = new MutableLiveData<>();
        return TestEntityResponse;
    }

    public boolean lastTestcreated() {
        boolean response = false;

        if (getLastTestCreated() != null && getLastTestCreated().getValue() != null && getLastTestCreated().getValue().getCode() == STATUS_CODE_CREATED) {
            response = true;
        }

        return response;
    }

    public LiveData<List<ResponseModel<TestEntity>>> getAllTests() {
        if (allTestsResponse == null) allTestsResponse = new MutableLiveData<>();
        return allTestsResponse;
    }
}
