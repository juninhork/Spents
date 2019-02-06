package juniorbraga.com.br.spents.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import juniorbraga.com.br.spents.R;
import juniorbraga.com.br.spents.util.UtilsActivity;

public class CustomToolbarActivity extends AppCompatActivity {

    private int resLayoutId;
    private ImageView ivAddSpent;
    private ImageView ivBackSpace;
    private TextView titleText;
    public boolean isHome = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(getResLayoutId());
        this.ivAddSpent = (ImageView) findViewById(R.id.addSpent);
        this.ivBackSpace = (ImageView) findViewById(R.id.backSpace);
        this.titleText = (TextView) findViewById(R.id.titleText);

        this.ivAddSpent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilsActivity.getInstance().showIntent(getApplicationContext(), SpentAddActivity.class);
            }
        });

        this.ivBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void hideBackSpace() {
        this.ivBackSpace.setVisibility(View.INVISIBLE);
    }

    public void hideAdd() {
        this.ivAddSpent.setVisibility(View.INVISIBLE);
    }

    public int getResLayoutId() {
        return resLayoutId;
    }

    public void setResLayoutId(int resLayoutId) {
        this.resLayoutId = resLayoutId;
    }

    public TextView getTitleText() {
        return titleText;
    }

    public void setTitleText(TextView titleText) {
        this.titleText = titleText;
    }
}
