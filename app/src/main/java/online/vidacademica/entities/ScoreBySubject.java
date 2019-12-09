package online.vidacademica.entities;

import java.io.Serializable;

public class ScoreBySubject implements Serializable {
    private static final long serialVersionUID = 1L;

    private String subject;
    private Double subjectTotalScore;

    public ScoreBySubject(String subject, Double subjectTotalScore) {
        this.subject = subject;
        this.subjectTotalScore = subjectTotalScore;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getSubjectTotalScore() {
        return subjectTotalScore;
    }

    public Double addToSubjectTotalScore(Double subjectTotalScore) {
        return this.subjectTotalScore += subjectTotalScore;
    }
}
