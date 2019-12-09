package online.vidacademica.entities.weak;

import java.io.Serializable;

public class SubjectByCourseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer courseId;
    private  Integer subjectId;

    public SubjectByCourseDTO(Integer courseId, Integer subjectId) {
        this.courseId = courseId;
        this.subjectId = subjectId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}
