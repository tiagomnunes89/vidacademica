package online.vidacademica.entities.weak;

import java.io.Serializable;

public class SubjectByCourseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long courseId;
    private  Long subjectId;

    public SubjectByCourseDTO(Long courseId, Long subjectId) {
        this.courseId = courseId;
        this.subjectId = subjectId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}
