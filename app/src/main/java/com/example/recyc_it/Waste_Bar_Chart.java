package com.example.recyc_it;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class Waste_Bar_Chart extends AppCompatActivity {


    private BarChart mBarChart;
    private String TAG = "DAD";
    private Intent mIntent;
    private TextView mTextView;
    private Button mTips;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waste__bar__chart);
        final ArrayList<BarEntry> NoOfEmp = new ArrayList<>();
        NoOfEmp.add(new BarEntry(0.0f, 0));
        NoOfEmp.add(new BarEntry(0.0f, 1));
        NoOfEmp.add(new BarEntry(0.0f, 2));
        NoOfEmp.add(new BarEntry(0.0f, 3));
        NoOfEmp.add(new BarEntry(0.0f, 4));
        NoOfEmp.add(new BarEntry(0.0f, 5));
        NoOfEmp.add(new BarEntry(0.0f, 6));
        NoOfEmp.add(new BarEntry(0.0f, 7));
        NoOfEmp.add(new BarEntry(0.0f, 8));
        NoOfEmp.add(new BarEntry(0.0f, 9));
        NoOfEmp.add(new BarEntry(0.0f, 10));
        NoOfEmp.add(new BarEntry(0.0f, 11));
        //updateBarEntry();
        mTips = findViewById(R.id.tipz);

        mBarChart = findViewById(R.id.barchart1);
        mTextView = findViewById(R.id.SatisFactory_Level);
        String[] monthName = {"January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};
        Calendar now = Calendar.getInstance();
        final String month = monthName[(now.get(Calendar.MONTH))];
        final String prevMonth = monthName[(now.get(Calendar.MONTH))-1];


        mIntent = getIntent();
        BarDataSet barDataSet = new BarDataSet(NoOfEmp, "Waste to Population Ratio Per Month");
        final String HouseNo = mIntent.getStringExtra("HouseNo");

        ArrayList<String> Months = new ArrayList<String>();
        Months.add("January");
        Months.add("February");
        Months.add("March");
        Months.add("April");
        Months.add("May");
        Months.add("June");
        Months.add("July");
        Months.add("August");
        Months.add("September");
        Months.add("October");
        Months.add("November");
        Months.add("December");


        BarData data = new BarData(Months,barDataSet);

        YAxis yAxis = mBarChart.getAxisLeft();
        yAxis.setAxisMinValue(0f);
        yAxis.setAxisMaxValue(40f);
        yAxis.setDrawLabels(true);
        mBarChart.setData(data);
        mBarChart.setDescription("");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        mBarChart.animateY(5000);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(HouseNo);

        reference.child(prevMonth).child("NonDegRatio").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Toast.makeText(Waste_Bar_Chart.this, " " + prevMonth, Toast.LENGTH_SHORT).show();
                    if(Float.valueOf(dataSnapshot.getValue().toString()) > 28.0)
                    {
                        mTips.setVisibility(View.VISIBLE);
                        mTextView.setText("Warning");
                        AlertDialog builder= new AlertDialog.Builder(Waste_Bar_Chart.this)
                                .setTitle("Warning")
                                .setMessage("Your Waste to Population Ratio of Previous Month is Quite High")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Continue with delete operation
                                        return;
                                    }
                                })

                                // A null listener allows the button to dismiss the dialog and take no further action.
                                .setNegativeButton(android.R.string.no, null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                        mTips.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent mintent = new Intent(Waste_Bar_Chart.this,Tipzz.class);
                                startActivity(mintent);
                            }
                        });
                    }
                    else if((Float.valueOf(dataSnapshot.getValue().toString())>17.0)&&(Float.valueOf(dataSnapshot.getValue().toString())<28.0))
                    {
                        mTextView.setText("Satisfactory");
                        mTips.setVisibility(View.VISIBLE);
                        mTips.setText("You Might Lower the Wastage Production by these tips");
                        mTips.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent mintent = new Intent(Waste_Bar_Chart.this,Tipzz.class);
                                startActivity(mintent);
                            }
                        });
                    }
                    else
                    {

                        mTextView.setText(" Amazing You're Doing Good");

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshotz, @Nullable String s) {
                if(dataSnapshotz.exists())
                {
                    final String monTh = dataSnapshotz.getKey();
                    final DatabaseReference non_degz = FirebaseDatabase.getInstance().getReference().child("Users").child(HouseNo).child(monTh).child("NonDegRatio");
                    if(!monTh.isEmpty())
                    {

                        non_degz.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                switch (monTh)
                                {
                                    case "January":
                                    {
                                        NoOfEmp.add(new BarEntry(Float.parseFloat(dataSnapshot.getValue().toString()),0));
                                        Log.d(TAG, "onDataChange: " + monTh);
                                        mBarChart.notifyDataSetChanged();
                                        break;
                                    }case "February":
                                {
                                    NoOfEmp.add(new BarEntry(Float.parseFloat(dataSnapshot.getValue().toString()),1));
                                    Log.d(TAG, "onDataChange: " + monTh);
                                    mBarChart.notifyDataSetChanged();
                                    break;
                                }case "March":
                                {
                                    NoOfEmp.add(new BarEntry(Float.parseFloat(dataSnapshot.getValue().toString()),2));
                                    Log.d(TAG, "onDataChange: " + monTh);
                                    mBarChart.notifyDataSetChanged();
                                    break;
                                }
                                    case "April":
                                    {
                                        NoOfEmp.add(new BarEntry(Float.parseFloat(dataSnapshot.getValue().toString()),3));
                                        Log.d(TAG, "onDataChange: " + monTh + dataSnapshot.getValue().toString());
                                        mBarChart.notifyDataSetChanged();
                                        break;
                                    }case "May":
                                {
                                    NoOfEmp.add(new BarEntry(Float.parseFloat(dataSnapshot.getValue().toString()),4));
                                    Log.d(TAG, "onDataChange: " + monTh);
                                    mBarChart.notifyDataSetChanged();
                                    break;
                                }case "June":
                                {
                                    NoOfEmp.add(new BarEntry(Float.parseFloat(dataSnapshot.getValue().toString()),5));
                                    Log.d(TAG, "onDataChange: " + monTh);
                                    mBarChart.notifyDataSetChanged();
                                    break;
                                }case "July":
                                {
                                    NoOfEmp.add(new BarEntry(Float.parseFloat(dataSnapshot.getValue().toString()),6));
                                    Log.d(TAG, "onDataChange: " + monTh);
                                    mBarChart.notifyDataSetChanged();
                                    break;
                                }case "August":
                                {
                                    NoOfEmp.add(new BarEntry(Float.parseFloat(dataSnapshot.getValue().toString()),7));
                                    Log.d(TAG, "onDataChange: " + monTh);
                                    mBarChart.notifyDataSetChanged();
                                    break;
                                }case "September":
                                {
                                    NoOfEmp.add(new BarEntry(Float.parseFloat(dataSnapshot.getValue().toString()),8));
                                    Log.d(TAG, "onDataChange: " + monTh);
                                    mBarChart.notifyDataSetChanged();
                                    break;
                                }
                                    case "October":
                                    {
                                        NoOfEmp.add(new BarEntry(Float.parseFloat(dataSnapshot.getValue().toString()), 9));
                                        mBarChart.notifyDataSetChanged();
                                        break;
                                    }case "November":
                                {
                                    NoOfEmp.add(new BarEntry(Float.parseFloat(dataSnapshot.getValue().toString()), 10));
                                    mBarChart.notifyDataSetChanged();
                                    break;
                                }case "December":
                                {
                                    NoOfEmp.add(new BarEntry(Float.parseFloat(dataSnapshot.getValue().toString()), 11));
                                    mBarChart.notifyDataSetChanged();
                                    break;
                                }
                                    default:
                                        return;
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        Log.d(TAG, "onChildAdded: " + monTh);
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
