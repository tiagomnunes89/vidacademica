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
import static online.vidacademica.repositories.network.vidacademica.VidAcademicaWSConstants.STATUS_CODE_OK;

public class CourseViewModel extends AndroidViewModel {

    private CourseRepository courseRepository;

    public CourseDTO courseDTO = new CourseDTO();

    private MutableLiveData<ResponseModel<CourseDTO>> courseDTOResponse;
    private MutableLiveData<ResponseModel<List<CourseDTO>>> allCoursesResponse;

    public CourseViewModel(@NonNull Application application) {
        super(application);
        courseRepository = CourseRepository.getInstance(application);
    }

    public void createCourse() {
        courseRepository.insert(courseDTO, courseDTOResponse);
    }

    public void updateCourse() {
        courseRepository.update(courseDTO, courseDTOResponse);
    }

    public LiveData<ResponseModel<CourseDTO>> getLastCourseCreatedUpdated() {
        if (courseDTOResponse == null) courseDTOResponse = new MutableLiveData<>();
        return courseDTOResponse;
    }

    public boolean lastCourseCreated() {
        boolean response = false;

        if (getLastCourseCreatedUpdated() != null && getLastCourseCreatedUpdated().getValue() != null && getLastCourseCreatedUpdated().getValue().getCode() == STATUS_CODE_CREATED) {
            response = true;
        }

        return response;
    }

    public boolean lastCourseUpdated() {
        boolean response = false;

        if (getLastCourseCreatedUpdated() != null && getLastCourseCreatedUpdated().getValue() != null && getLastCourseCreatedUpdated().getValue().getCode() == STATUS_CODE_OK) {
            response = true;
        }

        return response;
    }

    public MutableLiveData<ResponseModel<List<CourseDTO>>> getAllCourses() {
        if (allCoursesResponse == null) allCoursesResponse = new MutableLiveData<>();
        courseRepository.findAll(allCoursesResponse);
        return allCoursesResponse;
    }


}
