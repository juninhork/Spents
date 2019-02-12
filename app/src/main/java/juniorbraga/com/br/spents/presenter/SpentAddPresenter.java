package juniorbraga.com.br.spents.presenter;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.example.aplication.utils.base.BasePresenter;

import juniorbraga.com.br.spents.data.AppDatabase;
import juniorbraga.com.br.spents.interfaces.ISpent;
import juniorbraga.com.br.spents.model.Spent;

public class SpentAddPresenter extends BasePresenter<ISpent.AddSpent> {

    private AppDatabase db;
    public SpentAddPresenter(Context context, ISpent.AddSpent view) {
        super(context, view);
        this.db = Room.databaseBuilder(context,
                AppDatabase.class, "database-name").build();
    }

    public void insertSpent(final Spent spent) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.spentsDao().insertAll(spent);
                mView.showSucessAdd();
                return null;
            }
        }.execute();
    }
}
