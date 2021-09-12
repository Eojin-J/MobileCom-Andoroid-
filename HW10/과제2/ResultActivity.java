package com.example.mobile_hw10_ex2;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ResultActivity extends AppCompatActivity {
    TextView nameText2;
    ImageView imageView2, image_info;
    Button learnMoreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        nameText2 = (TextView) findViewById(R.id.nameText2);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        image_info = (ImageView) findViewById(R.id.image_info);
        learnMoreBtn =(Button) findViewById(R.id.learnMoreBtn);

        final Intent intent = getIntent();
        nameText2.setText(intent.getExtras().getString("name"));
        imageView2.setImageResource(intent.getExtras().getInt("image"));

        //System.out.println("뭘까나 "+intent.getExtras().getInt("image"));

        switch (intent.getExtras().getInt("image")){
            case 2131165302:
                image_info.setImageResource(R.drawable.americaninfo);
                break;
            case 2131165303:
                image_info.setImageResource(R.drawable.javachipinfo);
                break;
            case 2131165337:
                image_info.setImageResource(R.drawable.strawberryyougurtinfo);
                break;
        }

        learnMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = intent.getExtras().getString("learn more");
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent2);
            }
        });
    }
}
