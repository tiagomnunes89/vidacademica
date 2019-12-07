package online.vidacademica.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import online.vidacademica.core.ResponseModel;
import online.vidacademica.entities.CourseDTO;
import online.vidacademica.repositories.CourseRepository;

import static online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants.STATUS_CODE_CREATED;

public class CourseViewModel extends AndroidViewModel {

    private CourseRepository courseRepository;

    public CourseDTO courseDTO = new CourseDTO();

    private MutableLiveData<ResponseModel<CourseDTO>> courseDTOResponse;
    private MutableLiveData<List<ResponseModel<CourseDTO>>> allCoursesResponse;

    public CourseViewModel(@NonNull Application application) {
        super(application);
        courseRepository = CourseRepository.getInstance(application);
    }

    public void createCourse() {
        courseRepository.insert(courseDTO, courseDTOResponse);
    }

    public LiveData<ResponseModel<CourseDTO>> getLastCourseCreated() {
        if (courseDTOResponse == null) courseDTOResponse = new MutableLiveData<>();
        return courseDTOResponse;
    }

    public boolean lastCourseCreated() {
        boolean response = false;

        if (getLastCourseCreated() != null && getLastCourseCreated().getValue() != null && getLastCourseCreated().getValue().getCode() == STATUS_CODE_CREATED) {
            response = true;
        }

        return response;
    }

    public LiveData<List<ResponseModel<CourseDTO>>> getAllCourses() {
        if (allCoursesResponse == null) allCoursesResponse = new MutableLiveData<>();
        return allCoursesResponse;
    }


}
