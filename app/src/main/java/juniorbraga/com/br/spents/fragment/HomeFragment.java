package juniorbraga.com.br.spents.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aplication.utils.datetextfield.CustomDateUtil;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import juniorbraga.com.br.spents.R;
import juniorbraga.com.br.spents.interfaces.ISpent;
import juniorbraga.com.br.spents.model.Spent;
import juniorbraga.com.br.spents.presenter.HomeFragmentPresenter;
import juniorbraga.com.br.spents.util.CustomChart;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment  implements OnChartValueSelectedListener,ISpent.GetListSpent{

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    private PieChart chart;
    private HomeFragmentPresenter homeFragmentPresenter;
    private List<Spent> spentList;
    private TextView monthDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.chart = view.findViewById(R.id.chart1);
        this.chart.setOnChartValueSelectedListener(this);
        this.homeFragmentPresenter = new HomeFragmentPresenter(getActivity(),this);
        this.monthDetail = (TextView) view.findViewById(R.id.monthDetail);


        try {
            this.monthDetail.setText(CustomDateUtil.getMonthName()+"  " + CustomDateUtil.getCurrentYear());
        } catch (Exception e) {
            this.monthDetail.setText(CustomDateUtil.getCurrentMonth()+" - " + CustomDateUtil.getCurrentYear());
        }
        CustomChart.getInstance().initChart(getActivity(),chart);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.homeFragmentPresenter.getAllSpents();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", xIndex: " + e.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() { }

    @Override
    public void showList(List<Spent> spentList) {
        this.spentList = spentList;
        CustomChart.getInstance().initData(spentList,getActivity(),chart);
    }
}
