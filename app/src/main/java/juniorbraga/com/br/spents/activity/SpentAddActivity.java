package juniorbraga.com.br.spents.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import juniorbraga.com.br.spents.R;
import juniorbraga.com.br.spents.interfaces.ISpent;
import juniorbraga.com.br.spents.model.Spent;
import juniorbraga.com.br.spents.presenter.SpentAddPresenter;
import juniorbraga.com.br.spents.util.UtilsActivity;

public class SpentAddActivity extends CustomToolbarActivity implements ISpent.AddSpent , View.OnClickListener {

    private SpentAddPresenter spentAddPresenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.setResLayoutId(R.layout.activity_spent_add);
        super.onCreate(savedInstanceState);
        super.hideAdd();
        this.spentAddPresenter = new SpentAddPresenter(this,this);
    }


    private void newSpent(){




















        Spent spent = new Spent();
        spent.setDescription("Janta Caldo");
        spent.setFormOfPayment(0);
        spent.setPaidOut(true);
        spent.setPayday("2018-02-05");
        spent.setScheduled(false);
        spent.setTypeSpend(0);
        spent.setValuer(32.3);

        this.spentAddPresenter.insertSpent(spent);
    }

    @Override
    public void showSucessAdd() {

    }

    @Override
    public void showError(String Error) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
