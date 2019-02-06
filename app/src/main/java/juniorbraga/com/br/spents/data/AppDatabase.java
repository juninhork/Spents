package juniorbraga.com.br.spents.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import juniorbraga.com.br.spents.dao.SpentsDao;
import juniorbraga.com.br.spents.model.Spent;

@Database(entities = {Spent.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SpentsDao spentsDao();
}