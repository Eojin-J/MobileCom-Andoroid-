package com.example.mobile_hw10_ex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mobile_hw10_ex2.MyAdapter;
import com.example.mobile_hw10_ex2.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<PaintTitle> myDataset = new ArrayList<>();
        myDataset.add(new PaintTitle(R.drawable.iceamericano,"Ice Americano","4,100원"));
        myDataset.add(new PaintTitle(R.drawable.javachipfra,"Java Chip Frappuccino","6,100원"));
        myDataset.add(new PaintTitle(R.drawable.strawberrydelightyougurt,"Strawberry Delight Yougurt Blended","6,100원"));

        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }
}
