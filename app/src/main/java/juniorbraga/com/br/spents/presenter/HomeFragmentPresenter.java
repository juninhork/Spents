package juniorbraga.com.br.spents.presenter;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.example.aplication.utils.base.BasePresenter;

import java.util.List;

import juniorbraga.com.br.spents.data.AppDatabase;
import juniorbraga.com.br.spents.interfaces.ISpent;
import juniorbraga.com.br.spents.model.Spent;

public class HomeFragmentPresenter  extends BasePresenter<ISpent.GetListSpent> {

    private Context context;
    private AppDatabase db;

    public HomeFragmentPresenter(Context context, ISpent.GetListSpent view) {
        super(context, view);
        this.db = Room.databaseBuilder(context,
                AppDatabase.class, "database-name").build();
    }

    public void getAllSpents(){
        mProgresDialogUtil.show("Buscando Dados","Aguarde");
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                List<Spent> list = db.spentsDao().getAll();
                mView.showList(list);
                mProgresDialogUtil.dismiss();
                return null;
            }
        }.execute();
    }
}
