package online.vidacademica.repositories.storage.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import online.vidacademica.entities.TokenEntity;

@Dao
public interface TokenDao {

    @Query("SELECT * FROM token LIMIT 1")
    LiveData<TokenEntity> findOne();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TokenEntity tokenEntity);

    @Query("DELETE FROM token")
    void deleteAll();
}
