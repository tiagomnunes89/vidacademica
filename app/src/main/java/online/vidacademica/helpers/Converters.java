package online.vidacademica.helpers;

import androidx.room.TypeConverter;

import org.apache.commons.validator.ValidatorException;

import java.util.Date;

import online.vidacademica.entities.weak.Email;

public class Converters {

    // Converte entidade fraca de e-mail usada para forte tipagem no código.

    @TypeConverter
    public static String convert(Email email) {
        return email.getEmail();
    }

    @TypeConverter
    public static Email convert(String email) {
        Email newEmail = null;
        try {
            newEmail = new Email(email);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }

        return newEmail;
    }

    // Converte o tipo date

    @TypeConverter
    public static Long convert(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public static Date convert(Long date) {
        return new Date(date);
    }
}