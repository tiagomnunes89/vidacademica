package online.vidacademica.repositories.storage.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import online.vidacademica.entities.TokenEntity;

import static online.vidacademica.repositories.storage.local.VidAcademicaLocalDBConstants.DATABASE_NAME;

@Database(entities = {TokenEntity.class}, version = 1, exportSchema = false)
public abstract class VidAcademicaLocalDBClient extends RoomDatabase {

    private static VidAcademicaLocalDBClient INSTANCE;

    public static VidAcademicaLocalDBClient getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (VidAcademicaLocalDBClient.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), VidAcademicaLocalDBClient.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
