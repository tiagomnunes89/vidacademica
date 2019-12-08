package online.vidacademica.view.enums;

public enum RoleEnum {
    STUDENT,
    TEACHER;

    public static RoleEnum fromString(String role) {
        if (role.equalsIgnoreCase("TEACHER")) {
            return RoleEnum.TEACHER;
        }
        return RoleEnum.STUDENT;
    }

}
