package online.vidacademica.entities;

import java.io.Serializable;

public class CourseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String description;
    private Double workload = 0.0;
    private boolean active;

    private static final String STATUS_ACTIVE = "Ativo";
    private static final String STATUS_INACTIVE = "Inativo";

    public static final String[] POSSIBLE_STATUS = new String[]{STATUS_ACTIVE, STATUS_INACTIVE};

    public CourseDTO() {
    }

    public CourseDTO(Integer id, String name, String description, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getWorkload() {
        return workload;
    }

    public void setWorkload(Double workload) {
        this.workload = workload;
    }

    public boolean isActive() {
        return active;
    }

    public String isActiveString() {
        return activeBoolean2String(active);
    }

    public void setActive(String active) {
        this.active = activeString2Boolean(active);
    }

    private boolean activeString2Boolean(String status) {

        boolean response = true;

        if (status.equalsIgnoreCase(STATUS_INACTIVE)) {
            response = false;
        }

        return response;
    }

    private String activeBoolean2String(boolean status) {
        if (status) {
            return STATUS_ACTIVE;
        }

        return STATUS_INACTIVE;
    }
}
