package juniorbraga.com.br.spents.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import juniorbraga.com.br.spents.model.Spent;

@Dao
public interface SpentsDao {

    @Query("SELECT * FROM spent")
    List<Spent> getAll();

//    @Query("SELECT * FROM spent WHERE id IN (:id)")
//    List<Spent> loadAllByIds(int[] userIds);

//    @Query("SELECT * FROM spent WHERE descricao" )
//    List<Spent> findByName(String first);

    @Insert
    void insertAll(Spent spents);

    @Delete
    void delete(Spent spents);
}
