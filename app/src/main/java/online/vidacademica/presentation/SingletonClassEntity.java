package online.vidacademica.presentation;

import online.vidacademica.entities.ClassEntity;

public enum SingletonClassEntity {

    INSTANCE;

    private ClassEntity classEntity;

    private void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    public static void createClassEntity(ClassEntity classEntity) {
        SingletonClassEntity.INSTANCE.setClassEntity(classEntity);
    }

    public ClassEntity getClassEntity() {
        if (classEntity != null) {
            return classEntity;
        }
        return null;
    }
}