package juniorbraga.com.br.spents.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import juniorbraga.com.br.spents.model.Spent;
import juniorbraga.com.br.spents.model.SpentChart;

public class CustomChart {

    private static CustomChart uniqueInstance;

    public static synchronized CustomChart getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new CustomChart();

        return uniqueInstance;
    }

    List<SpentChart> spentChartList = new ArrayList<SpentChart>();

    public void initChart(Context context,PieChart chart){
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);
        chart.setDragDecelerationFrictionCoef(0.95f);
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");
        chart.setCenterTextTypeface(Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf"));
        chart.setCenterText("Despezas");
        chart.setExtraOffsets(20.f, 0.f, 20.f, 0.f);
        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);
        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);
        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);
        chart.setDrawCenterText(true);
        chart.setRotationAngle(0);
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);

        setData(context,chart);
    }

    private void setData(Context context,PieChart chart) {

        ArrayList<PieEntry> entries = new ArrayList<>();
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.

        for(SpentChart spentChart:spentChartList){
            entries.add(new PieEntry(spentChart.getValue(), spentChart.getTypeSpend()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.4f);

        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        data.setValueTypeface(tf);
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);
        chart.invalidate();
    }

    public void initData(List<Spent> spentList,Context context,PieChart chart){
        for(Spent spent:spentList){
            SpentChart spentChart = new SpentChart();
            String value = String.valueOf(spent.getValuer());
            spentChart.setTypeSpend(spent.getDescription());

            if(value != null)
                spentChart.setValue(Float.valueOf(value));

            this.spentChartList.add(spentChart);
        }
        setData(context,chart);
    }
}
