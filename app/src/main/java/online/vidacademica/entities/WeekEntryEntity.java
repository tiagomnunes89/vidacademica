package online.vidacademica.entities;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Ignore;

import java.io.Serializable;
import java.time.DayOfWeek;

public class WeekEntryEntity implements Serializable {

    private static final long serialVersionUID = -6604092636383467611L;

    private Long id;
    private DayOfWeek day;
    private Long startMillisecond;
    private Long endMillisecond;

    public WeekEntryEntity() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Ignore
    public WeekEntryEntity(Long id, DayOfWeek day, Long startMillisecond, Long endMillisecond) {
        this.id = id;
        this.day = day;
        this.startMillisecond = startMillisecond;
        this.endMillisecond = endMillisecond;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public Long getStartMillisecond() {
        return startMillisecond;
    }

    public void setStartMillisecond(Long startMillisecond) {
        this.startMillisecond = startMillisecond;
    }

    public Long getEndMillisecond() {
        return endMillisecond;
    }

    public void setEndMillisecond(Long endMillisecond) {
        this.endMillisecond = endMillisecond;
    }
}
