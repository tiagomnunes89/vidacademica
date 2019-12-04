package online.vidacademica.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class TestEntity implements Serializable {
    private static final long serialVersionUID = 5977833862920711908L;

    private Long id;
    private String name;
    private Double fullScore;
    private String date;
    private String creationDate;

    public TestEntity() {
    }

    public TestEntity(Long id, String name, Double fullScore,String date, String creationDate) {
        super();
        this.id = id;
        this.name = name;
        this.fullScore = fullScore;
        this.date = date;
        this.creationDate = creationDate;
    }

    public TestEntity(String name, Double fullScore,String date) {
        super();
        this.name = name;
        this.fullScore = fullScore;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFullScore() {
        return fullScore;
    }

    public void setFullScore(Double fullScore) {
        this.fullScore = fullScore;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        String[] splittedDate = date.split("\\/");
        this.date = String.format("%s-%-%sT00:00:00z", Integer.parseInt(splittedDate[2]), Integer.parseInt(splittedDate[1]), Integer.parseInt(splittedDate[0]));

    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        String[] splittedDate = creationDate.split("\\/");
        this.creationDate = String.format("%s-%-%sT00:00:00z", Integer.parseInt(splittedDate[2]), Integer.parseInt(splittedDate[1]), Integer.parseInt(splittedDate[0]));
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestEntity that = (TestEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
