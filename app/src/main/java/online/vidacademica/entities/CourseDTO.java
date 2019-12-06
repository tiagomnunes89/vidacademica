package online.vidacademica.entities;

import java.io.Serializable;

public class CourseDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    private String name;
    private String description;
    private boolean active;

    public CourseDTO(String name, String description, boolean active) {
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
