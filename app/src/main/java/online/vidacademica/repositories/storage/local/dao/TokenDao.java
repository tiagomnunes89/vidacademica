package online.vidacademica.repositories.storage.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import online.vidacademica.entities.TokenEntity;

@Dao
public abstract class TokenDao {

    @Query("SELECT * FROM token LIMIT 1")
    public abstract LiveData<TokenEntity> findOne();

    @Query("SELECT * FROM token LIMIT 1")
    public abstract TokenEntity findOneSync();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insert(TokenEntity tokenEntity);

    @Transaction
    public void insertAndDelete(TokenEntity tokenEntity) {
        deleteAll();
        insert(tokenEntity);
    }

    @Query("DELETE FROM token")
    public abstract void deleteAll();
}
