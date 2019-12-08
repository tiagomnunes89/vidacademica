package online.vidacademica.entities;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Ignore;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class TestEntity implements Serializable {
    private static final long serialVersionUID = 5977833862920711908L;

    private Long id;
    private String name;
    private String fullScore;
    private String date;
    private String creationDate;

    public TestEntity() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Ignore
    public TestEntity(Long id, String name, String fullScore, String date, String creationDate) {
        super();
        this.id = id;
        this.name = name;
        this.fullScore = fullScore;
        setDate(date);
        setCreationDate(creationDate);
    }

    public TestEntity(String name, String fullScore, String date) {
        super();
        this.name = name;
        this.fullScore = fullScore;
        setDate(date);
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

    public String getFullScore() {
        return fullScore;
    }

    public void setFullScore(String fullScore) {
        this.fullScore = fullScore;
    }

    public String getDate() {
        String response = "";
        if (date != null) {
            String[] mesAnoDia = date.toString().split("\\-");
            response = String.format("%s/%s/%s", mesAnoDia[2], mesAnoDia[0], mesAnoDia[1]);
        }
        return response;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setDate(String date) {
        String[] splittedDate = date.split("\\/");
        this.date = String.format("%s-%s-%sT00:00:00Z", Integer.parseInt(splittedDate[2]), Integer.parseInt(splittedDate[1]), Integer.parseInt(splittedDate[0]));
    }


    public String getCreationDate() {
        String response = "";
        if (creationDate != null) {
            String[] mesAnoDia = creationDate.toString().split("\\-");
            response = String.format("%s/%s/%s", mesAnoDia[2], mesAnoDia[0], mesAnoDia[1]);
        }
        return response;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setCreationDate(String creationDate) {
        String[] splittedDate = creationDate.split("\\/");
        this.creationDate = String.format("%s-%-%sT00:00:00z", Integer.parseInt(splittedDate[2]), Integer.parseInt(splittedDate[1]), Integer.parseInt(splittedDate[0]));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setCreationDateConvert(Date creationDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(creationDate);
        String[] splittedDate = strDate.split("\\/");
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
