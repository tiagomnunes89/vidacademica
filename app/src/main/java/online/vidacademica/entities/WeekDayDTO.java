package online.vidacademica.entities.weak;

import java.io.Serializable;

public class WeekDayDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String subjectTitle;
    private String weekDay;
    private String timeStart;
    private String timeEnd;

    public WeekDayDTO(){};

    public WeekDayDTO(String subjectTitle, String weekDay, String timeStart, String timeEnd) {
        this.subjectTitle = subjectTitle;
        this.weekDay = weekDay;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}


