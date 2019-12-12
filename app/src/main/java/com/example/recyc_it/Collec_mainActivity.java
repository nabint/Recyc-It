package com.example.recyc_it;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Collec_mainActivity extends AppCompatActivity {

    private EditText month,house_num,people,deg_weight,nondeg_weight;
    private Float mpeople,mdeg_weight,mnondeg_weight;
    private Button Calc_Ratio,Send_it;
    private TextView deg_ratio,nondeg_ratio;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collec_main);
        month = (EditText) findViewById(R.id.months);
        house_num = (EditText) findViewById(R.id.house_id);
        people = (EditText) findViewById(R.id.people_num);
        deg_weight = (EditText) findViewById(R.id.degradeable_weight);
        nondeg_weight = (EditText) findViewById(R.id.nondegradeable_weight);
        deg_ratio = (TextView) findViewById(R.id.degradeable_ratio);
        nondeg_ratio = (TextView) findViewById(R.id.nondegradeable_ratio);
        Calc_Ratio = (Button) findViewById(R.id.calc_ratio);
        deg_ratio.setVisibility(View.INVISIBLE);
        nondeg_ratio.setVisibility(View.INVISIBLE);
        Send_it =(Button) findViewById(R.id.send_project);
        mAuth = FirebaseAuth.getInstance();

        Calc_Ratio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpeople = Float.valueOf(people.getText().toString());
                mdeg_weight = Float.valueOf(deg_weight.getText().toString());
                mnondeg_weight= Float.valueOf(nondeg_weight.getText().toString());
                deg_ratio.setVisibility(View.VISIBLE);
                deg_ratio.setText(String.valueOf(mdeg_weight/mpeople));
                nondeg_ratio.setVisibility(View.VISIBLE);
                nondeg_ratio.setText(String.valueOf(mnondeg_weight/mpeople));
                /*DatabaseReference Deg_ratio = FirebaseDatabase.getInstance().getReference().child("Users").child("House").child("DegRatio");
                DatabaseReference Non_deg_ratio = FirebaseDatabase.getInstance().getReference().child("Users").child("House").child("NonDegRatio");
                Deg_ratio.setValue(String.valueOf(mdeg_weight/mpeople));
                Non_deg_ratio.setValue(String.valueOf(mnondeg_weight/mpeople));*/
                String Userid = mAuth.getCurrentUser().getUid();

            }
        });
        Send_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String Userid = mAuth.getCurrentUser().getUid();
                mpeople = Float.valueOf(people.getText().toString());
                mdeg_weight = Float.valueOf(deg_weight.getText().toString());
                mnondeg_weight= Float.valueOf(nondeg_weight.getText().toString());

                DatabaseReference Deg_ratio = FirebaseDatabase.getInstance().getReference().child("Users").child(house_num.getText().toString()).child(month.getText().toString()).child("DegRatio");
                DatabaseReference Non_deg_ratio = FirebaseDatabase.getInstance().getReference().child("Users").child(house_num.getText().toString()).child(month.getText().toString()).child("NonDegRatio");

                Deg_ratio.setValue(String.valueOf(Float.valueOf(deg_weight.getText().toString())/Float.valueOf(people.getText().toString())));
                Non_deg_ratio.setValue(String.valueOf(Float.valueOf(nondeg_weight.getText().toString())/Float.valueOf(people.getText().toString())));

            }
        });

        //deg_ratio.setText(mdeg_weight/mpeople);

        //nondeg_ratio.setText(mnondeg_weight/mpeople);




    }
}
