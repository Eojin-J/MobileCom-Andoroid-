package com.example.mobile_hw10_ex2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_hw10_ex2.MyViewHolder;
import com.example.mobile_hw10_ex2.PaintTitle;
import com.example.mobile_hw10_ex2.R;
import com.example.mobile_hw10_ex2.ResultActivity;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private ArrayList<PaintTitle> mDataset;
    Context context;
    public MyAdapter(ArrayList<PaintTitle> mDataset){this.mDataset = mDataset;}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.imageView.setImageResource(mDataset.get(position).imageId);
        holder.nameTxt1.setText(mDataset.get(position).name);
        holder.priceTxt1.setText(mDataset.get(position).price);

        holder.selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ResultActivity.class);
                intent.putExtra("name",mDataset.get(position).name);
                intent.putExtra("image",mDataset.get(position).imageId);
                intent.putExtra("learn more","https://www.starbucks.co.kr/menu/drink_list.do");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
