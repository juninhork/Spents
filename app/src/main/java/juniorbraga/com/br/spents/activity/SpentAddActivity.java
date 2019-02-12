package juniorbraga.com.br.spents.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.aplication.utils.CustomCurrencyTextWatcher;
import com.example.aplication.utils.datetextfield.CustomDateTextField;
import com.example.aplication.utils.datetextfield.CustomDateUtil;

import juniorbraga.com.br.spents.R;
import juniorbraga.com.br.spents.enums.FormOfPaymentEnum;
import juniorbraga.com.br.spents.viewcontroller.SpentAddViewController;

public class SpentAddActivity extends CustomToolbarActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    private CustomDateTextField payday;
    private EditText value;
    private EditText description;
    private Spinner formOfPaymentSpiner;
    private Switch scheduled;
    private SpentAddViewController spentAddViewController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.setResLayoutId(R.layout.activity_spent_add);
        super.onCreate(savedInstanceState);
        super.hideAdd();

        this.payday = (CustomDateTextField)findViewById(R.id.payday);
        this.value = (EditText) findViewById(R.id.value);
        this.description = (EditText) findViewById(R.id.description);
        this.formOfPaymentSpiner = (Spinner)findViewById(R.id.formOfPaymentSpiner);
        this.scheduled = (Switch) findViewById(R.id.scheduled);
        this.formOfPaymentSpiner.setOnItemSelectedListener(this);

        findViewById(R.id.saveSpent).setOnClickListener(this);
        this.value.addTextChangedListener(new CustomCurrencyTextWatcher(this.value));
        this.payday.setFragmentManager(getSupportFragmentManager());
        this.payday.setText(CustomDateUtil.getTodayDate());

        this.spentAddViewController = new SpentAddViewController(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveSpent:
                spentAddViewController.newSpent();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String descrpition = parent.getItemAtPosition(position).toString();
        this.spentAddViewController.setPositionFormOfPaymentSpiner(FormOfPaymentEnum.getFormOfPayment(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public CustomDateTextField getPayday() {
        return payday;
    }

    public void setPayday(CustomDateTextField payday) {
        this.payday = payday;
    }

    public EditText getValue() {
        return value;
    }

    public void setValue(EditText value) {
        this.value = value;
    }

    public EditText getDescription() {
        return description;
    }

    public void setDescription(EditText description) {
        this.description = description;
    }

    public Switch getScheduled() {
        return scheduled;
    }

    public void setScheduled(Switch scheduled) {
        this.scheduled = scheduled;
    }
}
