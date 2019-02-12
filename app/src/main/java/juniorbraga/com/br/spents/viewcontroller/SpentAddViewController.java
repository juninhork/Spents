package juniorbraga.com.br.spents.viewcontroller;

import com.example.aplication.utils.Utils;
import com.example.aplication.utils.base.BaseActivityViewController;
import com.example.aplication.utils.datetextfield.CustomDateUtil;
import com.example.aplication.utils.resultservice.IResponseSevice;
import com.example.aplication.utils.resultservice.ResponseService;

import juniorbraga.com.br.spents.R;
import juniorbraga.com.br.spents.activity.SpentAddActivity;
import juniorbraga.com.br.spents.enums.FormOfPaymentEnum;
import juniorbraga.com.br.spents.interfaces.ISpent;
import juniorbraga.com.br.spents.model.Spent;
import juniorbraga.com.br.spents.presenter.SpentAddPresenter;
import juniorbraga.com.br.spents.util.UtilsActivity;

public class SpentAddViewController extends BaseActivityViewController<SpentAddActivity> implements ISpent.AddSpent, IResponseSevice {

    private SpentAddPresenter spentAddPresenter;
    private FormOfPaymentEnum positionFormOfPaymentSpiner;

    public SpentAddViewController(SpentAddActivity activity) {
        super(activity);
        this.spentAddPresenter = new SpentAddPresenter(activity, this);
        positionFormOfPaymentSpiner = FormOfPaymentEnum.DINHEIRO;
    }

    public void newSpent() {
        if (this.isValidFormSpents()) {
            double value = Utils.getDoubleValue(activity.getValue().getText().toString());

            Spent spent = new Spent();
            spent.setDescription(activity.getDescription().getText().toString());
            spent.setFormOfPayment(positionFormOfPaymentSpiner.getFormOfPayment());
            spent.setPaidOut(true);
            spent.setScheduled(this.activity.getScheduled().isChecked());
            spent.setTypeSpend(0);
            spent.setValuer(value);
            spent.setPayday(this.activity.getPayday().getText().toString());
            spent.setRepeat(false);

            super.hideSoftKeyBoard();
            this.spentAddPresenter.insertSpent(spent);
        }
    }

    private boolean isValidFormSpents() {
        if (!UtilsActivity.verificString(activity.getDescription().getText().toString())) {
            activity.getDescription().setError(activity.getString(R.string.prompt_error));
            return false;
        } else if (!UtilsActivity.verificString(activity.getPayday().getText().toString())) {
            activity.getPayday().setError(activity.getString(R.string.prompt_error));
            return false;
        } else if (!UtilsActivity.verificString(activity.getValue().getText().toString())) {
            activity.getValue().setError(activity.getString(R.string.prompt_error));
            return false;
        }
        return true;
    }

    @Override
    public void showSucessAdd() {
        ResponseService responseService = new ResponseService();
        responseService.setSuccess(true);
        responseService.setMsg("Cadatro realizado com sucesso");

        super.sucessServiceView(this.activity.getSupportFragmentManager().beginTransaction(), responseService, this);

    }

    @Override
    public void showButon(boolean isSucessed) {
        if (isSucessed) {
            activity.getValue().setText("R$0,00");
            activity.getDescription().setText("");
            this.activity.getScheduled().setChecked(false);
            this.activity.getPayday().setText(CustomDateUtil.getTodayDate());
        }
    }

    public FormOfPaymentEnum getPositionFormOfPaymentSpiner() {
        return positionFormOfPaymentSpiner;
    }

    public void setPositionFormOfPaymentSpiner(FormOfPaymentEnum positionFormOfPaymentSpiner) {
        this.positionFormOfPaymentSpiner = positionFormOfPaymentSpiner;
    }
}
