package online.vidacademica.repositories.storage.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import online.vidacademica.entities.TokenEntity;
import online.vidacademica.entities.UserEntity;
import online.vidacademica.helpers.Converters;
import online.vidacademica.repositories.storage.local.dao.TokenDao;

import static online.vidacademica.repositories.storage.local.VidAcademicaLocalDBConstants.DATABASE_NAME;

@Database(
        entities = {
                TokenEntity.class,
                UserEntity.class
        },
        version = 1,
        exportSchema = false
)
@TypeConverters({Converters.class})
public abstract class VidAcademicaLocalDBClient extends RoomDatabase {

    private static VidAcademicaLocalDBClient INSTANCE;

    public abstract TokenDao tokenDao();

    public static VidAcademicaLocalDBClient getInstance(final Context context) {
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
