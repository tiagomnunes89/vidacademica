package online.vidacademica.entities;

import java.io.Serializable;
import java.time.Instant;

public class TestResultDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double score;
    private Instant date;
    private String testName;
    private String subjectName;

    public TestResultDTO(){}

    public TestResultDTO(Double score, Instant date, String testName, String subjectName) {
        this.score = score;
        this.date = date;
        this.testName = testName;
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
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

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }


}
