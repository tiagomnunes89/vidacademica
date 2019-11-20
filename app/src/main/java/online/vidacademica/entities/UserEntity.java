package online.vidacademica.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class UserEntity implements Serializable {
    private static final long serialVersionUID = -6604092636383467611L;

    private Long id;
    private String name;
    private Email email;
    private Instant dateOfBirth;
    private String socialId;

    private UserEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.dateOfBirth = builder.dateOfBirth;
        this.socialId = builder.socialId;
    }


    public static FullNameStep builder(Long id) {
        return new Builder(id);
    }

    public interface FullNameStep {
        BirthdayStep withName(String dateOfBirth);
    }

    public interface BirthdayStep {
        EmailStep withBirthday(Instant dateOfBirth);
    }

    public interface EmailStep {
        SocialIdStep withMail(Email email);
    }

    public interface SocialIdStep {
        BuildStep withSocialId(String socialId);
    }

    public interface BuildStep {
        UserEntity build();
    }


    public static class Builder implements FullNameStep, BirthdayStep, EmailStep, SocialIdStep, BuildStep {

        private Long id;
        private String name;
        private Email email;
        private Instant dateOfBirth;
        private String socialId;

        private Builder(Long id) {
            this.id = id;
        }

        @Override
        public BirthdayStep withName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public EmailStep withBirthday(Instant dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        @Override
        public SocialIdStep withMail(Email email) {
            this.email = email;
            return this;
        }

        @Override
        public BuildStep withSocialId(String socialId) {
            this.socialId = socialId;
            return this;
        }

        @Override
        public UserEntity build() {
            return new UserEntity(this);
        }
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
