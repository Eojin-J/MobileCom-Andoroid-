package com.example.mobile_hw10_ex2;

import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_hw10_ex2.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView nameTxt1,priceTxt1;
    ImageView imageView;
    Button selectBtn;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTxt1=(TextView)itemView.findViewById(R.id.nameText1);
        priceTxt1=(TextView)itemView.findViewById(R.id.priceText1);
        imageView=(ImageView)itemView.findViewById(R.id.imageView1);
        selectBtn = (Button)itemView.findViewById(R.id.selectButton);
    }
}
