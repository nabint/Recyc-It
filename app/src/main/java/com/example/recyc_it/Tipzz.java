package com.example.recyc_it;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Tipzz extends AppCompatActivity {

    private ArrayList<String> mArrayList;
    private ArrayAdapter<String> arrayAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipzz);
        mArrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.row_list,R.id.txt_tips,mArrayList);
        mListView = findViewById(R.id.listview_tips);

        mArrayList.add("Use Cloth Bags Instead of Plastic");
        mArrayList.add("Buy Food That has less Packaging");
        mArrayList.add("Don't Use Bottled Drinks Unless You Have to");
        mArrayList.add("Reduce your Paper Usage");
        mArrayList.add("Donate Item When Possible");
        mArrayList.add("Reuse Containers");

        arrayAdapter.notifyDataSetChanged();
        mListView.setAdapter(arrayAdapter);
    }
}
