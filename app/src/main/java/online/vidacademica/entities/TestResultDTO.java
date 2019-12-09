package online.vidacademica.entities;

import java.io.Serializable;
import java.time.Instant;

public class TestResultDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double score;
    private Instant date;
    private Long testId;
    private String subjectName;


    public TestResultDTO(){}

    public TestResultDTO(Double score, Instant date, Long testId, String subjectName) {
        this.score = score;
        this.date = date;
        this.testId = testId;
        this.subjectName = subjectName;
    }

    public String getsubjectName() {
        return subjectName;
    }

    public void setsubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }


}
