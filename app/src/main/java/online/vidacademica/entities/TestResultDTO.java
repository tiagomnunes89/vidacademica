package online.vidacademica.entities;

import java.io.Serializable;
import java.time.Instant;

public class TestResultDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double score;
    private Instant date;
    private Long userId;
    private Long testId;


    public TestResultDTO(){}

    public TestResultDTO(Double score, Instant date, Long userId, Long testId) {
        this.score = score;
        this.date = date;
        this.userId = userId;
        this.testId = testId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }


}
