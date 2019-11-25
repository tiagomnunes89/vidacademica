package online.vidacademica.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import online.vidacademica.entities.weak.Email;

@Entity(tableName = "users")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -6604092636383467611L;

    @PrimaryKey
    private Long id;
    private String name;
    private Email email;
    private Date dateOfBirth;
    private String socialId;

    public UserEntity() {
    }

    @Ignore
    public UserEntity(Long id, String name, Email email, Date dateOfBirth, String socialId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.socialId = socialId;
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

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
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
