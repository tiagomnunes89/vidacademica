package online.vidacademica.utils;

public class StringUtils {

    public static boolean isEmptyOrNull(String str) {
        return (str == null || str.trim().isEmpty());
    }
}
