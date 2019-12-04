package online.vidacademica.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class SubjectDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private boolean active;
    private String startDate;
    private String endDate;

    public SubjectDTO(String name, boolean active, String startDate, String endDate) {
        this.name = name;
        this.active = active;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        String[] splittedDate = startDate.split("\\/");
        this.startDate = String.format("%s-%-%sT00:00:00z", Integer.parseInt(splittedDate[2]), Integer.parseInt(splittedDate[1]), Integer.parseInt(splittedDate[0]));
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        String[] splittedDate = endDate.split("\\/");
        this.endDate = String.format("%s-%-%sT00:00:00z", Integer.parseInt(splittedDate[2]), Integer.parseInt(splittedDate[1]), Integer.parseInt(splittedDate[0]));
    }
}
