package online.vidacademica.entities;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class UserEntity implements Serializable {
    private static final long serialVersionUID = -6604092636383467611L;

    private Long id;
    private String name;
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private LocalDate dateOfBirth;
    private String socialId;
    private String password;

    public UserEntity() {
    }

    @Ignore
    public UserEntity(Long id, String name, String email, LocalDate dateOfBirth, String socialId, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.socialId = socialId;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        String response = "";
        if (dateOfBirth != null) {
            String[] mesAnoDia = dateOfBirth.toString().split("\\-");
            response = String.format("%s/%s/%s", mesAnoDia[2], mesAnoDia[0], mesAnoDia[1]);
        }
        return response;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setDateOfBirth(String dateOfBirth) {
        String[] splittedDate = dateOfBirth.split("\\/");
        LocalDate date = LocalDate.of(Integer.parseInt(splittedDate[2]), Integer.parseInt(splittedDate[1]), Integer.parseInt(splittedDate[0])).atStartOfDay().toLocalDate();
        this.dateOfBirth = date;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
