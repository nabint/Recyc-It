package com.example.recyc_it;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Public_main extends AppCompatActivity {

    private TextView house,month,deg,nondeg;
    private Intent mIntent,intent;
    private String TAG = "DAD";
    //private BarChart mBarChart;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_main);
        mIntent = getIntent();
        //mBarChart = findViewById(R.id.barchart1);

        mButton = findViewById(R.id.show_waste);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        final ArrayList<BarEntry> NoOfEmp = new ArrayList<>();
        NoOfEmp.add(new BarEntry(0.0f,0));
        NoOfEmp.add(new BarEntry(0.0f,1));
        NoOfEmp.add(new BarEntry(0.0f,2));
        NoOfEmp.add(new BarEntry(0.0f,3));
        NoOfEmp.add(new BarEntry(0.0f,4));
        NoOfEmp.add(new BarEntry(0.0f,5));
        NoOfEmp.add(new BarEntry(0.0f,6));
        NoOfEmp.add(new BarEntry(0.0f,7));
        NoOfEmp.add(new BarEntry(0.0f,8));
        NoOfEmp.add(new BarEntry(0.0f,9));
        NoOfEmp.add(new BarEntry(0.0f,10));
        NoOfEmp.add(new BarEntry(0.0f,11));
        //updateBarEntry();

        BarDataSet barDataSet = new BarDataSet(NoOfEmp,"Waste to Population Ratio Per Month");

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

        final String HouseNo=mIntent.getStringExtra("Housenum");

        month = findViewById(R.id.monthss);
        deg = findViewById(R.id.degg);
        house = findViewById(R.id.housee);
        nondeg = findViewById(R.id.non_degg);
        month.setText("House No." +HouseNo);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Public_main.this,Waste_Bar_Chart.class);
                intent.putExtra("HouseNo",HouseNo);
                startActivity(intent);
            }
        });

    }
}
