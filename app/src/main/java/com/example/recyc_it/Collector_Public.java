package com.example.recyc_it;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Collector_Public extends AppCompatActivity {

    private Button public1,collector;
    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collector_public);

        public1 = (Button) findViewById(R.id.public1);
        collector = (Button) findViewById(R.id.collector);
        mIntent = new Intent(Collector_Public.this,C_Login_Register.class);
        final String pub = ((Button)public1).getText().toString();
        final String col = ((Button)collector).getText().toString();
        public1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent.putExtra("name",pub);
                startActivity(mIntent);
                finish();
            }
        });

        collector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent.putExtra("name",col);
                startActivity(mIntent);
                finish();
            }
        });



    }
}
