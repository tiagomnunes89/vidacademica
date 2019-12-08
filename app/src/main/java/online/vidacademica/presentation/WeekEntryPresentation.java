package online.vidacademica.presentation;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Ignore;

import java.io.Serializable;

public class WeekEntryPresentation implements Serializable {

    private static final long serialVersionUID = -6604092636383467611L;

    private Long id;
    private String day;
    private String startTime;
    private String endTime;

    public WeekEntryPresentation() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Ignore
    public WeekEntryPresentation(String day, String startTime, String endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}