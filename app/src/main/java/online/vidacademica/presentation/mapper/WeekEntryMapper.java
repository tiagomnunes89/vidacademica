package online.vidacademica.presentation.mapper;

import java.time.DayOfWeek;

import online.vidacademica.entities.WeekEntryEntity;
import online.vidacademica.presentation.WeekEntryPresentation;
import online.vidacademica.utils.DateFormatUtils;

public class WeekEntryMapper extends AbstractMapper<WeekEntryEntity, WeekEntryPresentation>{

    private static final WeekEntryMapper INSTANCE = new WeekEntryMapper();

    public static WeekEntryMapper get() {
        return INSTANCE;
    }

    public WeekEntryMapper() {
    }

    @Override
    public WeekEntryPresentation convertTo(WeekEntryEntity value) {
        WeekEntryPresentation presentation = new WeekEntryPresentation();
        if(value != null){
            String day = DateFormatUtils.convertDayOfWeekToString(value.getDay());
            presentation.setDay(day);
            String start = DateFormatUtils.convertMillisToHourAndMinute(value.getStartMillisecond());
            presentation.setStartTime(start);
            String end = DateFormatUtils.convertMillisToHourAndMinute(value.getEndMillisecond());
            presentation.setStartTime(end);
        }
        return presentation;
    }

    @Override
    public WeekEntryEntity convertFrom(WeekEntryPresentation value) {
        WeekEntryEntity entity = new WeekEntryEntity();
        if(value != null){
            DayOfWeek day = DateFormatUtils.convertStringToDayOfWeek(value.getDay());
            if(day != null) entity.setDay(day);
            long start = DateFormatUtils.convertStringHourToMillis(value.getStartTime());
            entity.setStartMillisecond(start);
            long end = DateFormatUtils.convertStringHourToMillis(value.getEndTime());
            entity.setEndMillisecond(end);
        }
        return entity;
    }
}