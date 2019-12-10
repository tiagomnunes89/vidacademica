package online.vidacademica.repositories.network.vidacademica;

public final class VidAcademicaWSConstants {
    static final String BASE_URL = "https://vidacademica-services.herokuapp.com";

    public static final String AUTH_HEADER_KEY = "Authorization";

    public static final int STATUS_CODE_OK = 200;
    public static final int STATUS_CODE_CREATED = 201;
    public static final int STATUS_CODE_NO_CONTENT = 204;
    public static final int STATUS_CODE_NOT_FOUND = 404;
    public static final int STATUS_CODE_SERVER_ERROR = 500;

    private static final String AUTH_PATH = "/auth";
    public static final String AUTH_PATH_LOGIN = AUTH_PATH + "/login";

    public static final String USER_PATH = "/users";
    public static final String USER_PATH_SELF = "/users/self";
    public static final String USER_PATH_ID = "/users/{id}";

    public static final String TEST_PATH = "/tests";

    public static final String COURSE_PATH = "/courses";
    public static final String COURSE_PATH_ID = "/courses/{id}";

    public static final String CLASS_PATH = "/class";
}