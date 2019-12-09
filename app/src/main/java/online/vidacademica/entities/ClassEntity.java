package online.vidacademica.entities;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Ignore;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClassEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
    private Instant creationDate;
    private SubjectDTO subject;
    private List<TestEntity> testsList = new ArrayList<>();
    private List<WeekEntryEntity> weekEntries = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Ignore
    public ClassEntity(Long id, String name, LocalDate startDate,
                       LocalDate endDate, boolean active, Instant creationDate, SubjectDTO subject) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
        this.creationDate = creationDate;
        this.subject = subject;
    }

    public ClassEntity() {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public SubjectDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDTO subject) {
        this.subject = subject;
    }

    public void setTest(TestEntity test) {
        testsList.add(test);
    }

    public void addTest(WeekEntryEntity entry) {
        weekEntries.add(entry);
    }

    public List<WeekEntryEntity> getWeekEntries() {
        return weekEntries;
    }

    public void addEntry(WeekEntryEntity entry) {
        weekEntries.add(entry);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ClassEntity other = (ClassEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}