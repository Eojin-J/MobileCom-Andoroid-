package com.example.mobile_hw6_work1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    ViewFlipper viewFlipper;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                String imageName;
                switch (viewFlipper.getDisplayedChild()){
                    case 0:
                        imageName = "Gingerbread";
                        break;
                    case 1:
                        imageName = "IceCreamSandwich";
                        break;
                    case 2:
                        imageName = "Jellybean";
                        break;
                    case 3:
                        imageName = "Kitkat";
                        break;
                    default:
                        imageName = null;
                }
                Toast.makeText(MainActivity.this, "그림이름 : " + imageName
                        + " 별점 : " + rating, Toast.LENGTH_SHORT).show();
            }
        });

        ImageView imageView1 = new ImageView(this);
        ImageView imageView2 = new ImageView(this);
        imageView1.setImageResource(R.drawable.a_jellybean);
        imageView2.setImageResource(R.drawable.a_kitkat);
        viewFlipper.addView(imageView1);
        viewFlipper.addView(imageView2);
    }

    public void nextBtnClick(View v){
        viewFlipper.showNext();
    }

    public void prevBtnClick(View v){
        viewFlipper.showPrevious();
    }

}