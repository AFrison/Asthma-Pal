package com.AP.Asthma_Pal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChartActivity extends AppCompatActivity {

    private GraphView graphview;
    private LineGraphSeries<DataPoint> series;
    private SimpleDateFormat sdf = new SimpleDateFormat("MMM dd  \n aa");
    private double[][] graphValues;
    private Button update, back;
    private EditText Yvalue;
    private int clickNumber;
    DatabaseHelper2 db;
    private ArrayList<Double> mPeak, mDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        graphview = (GraphView) findViewById(R.id.graph);
        update = findViewById(R.id.btnAddtoGraph);
        Yvalue = findViewById(R.id.etYvalue);
        activateScroll();
        clickNumber = 0;
        back = findViewById(R.id.btnBackToMenu);
        series = new LineGraphSeries<>();
        mPeak = new ArrayList<>();
        mDate = new ArrayList<>();

        graphview.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter()
        {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX){
                    return sdf.format(new Date((long) value));
                }
                else{
                return super.formatLabel(value, isValueX);
            }
        }
        });

        graphview.getGridLabelRenderer().setNumHorizontalLabels(3);


        db = new DatabaseHelper2(this);
        Cursor data = db.getListContents();


        if(data.getCount() == 0){
        }
        else {
            while (data.moveToNext()) {
                mDate.add(data.getDouble(0));
                mPeak.add(data.getDouble(1));
            }

            for (int i = 0; i < mDate.size(); i++) {
                series.appendData(new DataPoint(mDate.get(i), mPeak.get(i)),true, 1000);
            }

            graphview.addSeries(series);

            if(mDate.size() <= 10) {
                graphview.getViewport().setMaxX(mDate.get(mDate.size() - 1));
                graphview.getViewport().setMinX(mDate.get(0));
            }
            else{
                graphview.getViewport().setMaxX(mDate.get(mDate.size() - 1));
                graphview.getViewport().setMinX(mDate.get(mDate.size() - 11));
            }
            graphview.getViewport().setXAxisBoundsManual(true);
        }
        init();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MainActivity.active){
                    Intent intent = new Intent(ChartActivity.this, MainActivity.class);
                    MainActivity.MA.finish();
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(ChartActivity.this, MainActivity.class);
                    DailyEntryActivity.DA.finish();
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void init(){

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Yvalue.getText().toString().equals("")){
                    double y = Double.parseDouble(Yvalue.getText().toString());
                    long xdate = new Date().getTime();
                    Toast.makeText(ChartActivity.this, ""+y, Toast.LENGTH_SHORT).show();
                    DataPoint data = new DataPoint(xdate, y);
                    series.appendData(data, true, 1000);
                    graphview.addSeries(series);
                    graphview.getViewport().setMaxX(xdate);
                    addData(xdate, y);
                }
                else
                    Toast.makeText(ChartActivity.this, "Please Enter a value", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void activateScroll() {
        // activate horizontal zooming and scrolling
        graphview.getViewport().setScalable(true);
        // activate horizontal scrolling
        graphview.getViewport().setScrollable(true);
        // activate horizontal and vertical zooming and scrolling
        graphview.getViewport().setScalableY(true);
        // activate vertical scrolling
        graphview.getViewport().setScrollableY(true);
    }

    public void addData(double date, double peak) {
        db.insertData(date, peak);
    }

}