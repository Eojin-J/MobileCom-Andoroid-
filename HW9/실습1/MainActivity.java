package com.example.mobile_hw9_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    final static int LINE = 1, CIRCLE = 2, RECT = 3;
    static int curShape = LINE;
    static int curColor = Color.RED;
    static boolean finished = false;  //도형을 다 그린 것인지 체크하기 위함 (그리기 완료 : true)

    ImageButton rButton, gButton, bButton;
    LinearLayout drawArea;

    static List<MyShape> myShapeArrayList = new ArrayList<MyShape>();   //리스트로 도형들 저장하기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyGraphicView myGraphicView = new MyGraphicView(this);

        rButton = (ImageButton) findViewById(R.id.rButton);
        bButton = (ImageButton) findViewById(R.id.bButton);
        gButton = (ImageButton) findViewById(R.id.gButton);

        drawArea = (LinearLayout) findViewById(R.id.drawLayout);
        drawArea.addView(myGraphicView);
        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curColor = Color.RED;
            }
        });
        bButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curColor = Color.BLUE;
            }
        });
        gButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curColor = Color.BLACK;
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.itemLine:
                curShape = LINE;
                return true;
            case R.id.itemCircle:
                curShape = CIRCLE;
                return true;
            case R.id.itemRect:
                curShape = RECT;
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static class MyGraphicView extends View {
        int startX = -1, startY = -1, stopX = -1, stopY = -1;

        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    finished = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.d("jang", "move:x=" + event.getX() + " y=" + event.getY());
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    finished=false;
                    this.invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    MyShape shape = new MyShape();    //현재 그린 도형 저장

                    shape.shape_type = curShape;
                    shape.startX = startX;
                    shape.startY = startY;
                    shape.stopX = stopX;
                    shape.stopY = stopY;
                    shape.color = curColor;

                    myShapeArrayList.add(shape);
                    finished = true;
                    this.invalidate();
                    break;
            }
            return true;
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle((Paint.Style.STROKE));

            for (int i = 0; i < myShapeArrayList.size(); i++) {  //저장된 리스트를 불러서 하나씩 그리기
                MyShape sv_shape = myShapeArrayList.get(i);
                paint.setColor(sv_shape.color);

                System.out.println("aaaaaaaaaaaaaaaaaa"+myShapeArrayList); //대체 왜 안 되었을까

                switch (sv_shape.shape_type) {
                    case LINE:
                        canvas.drawLine(sv_shape.startX, sv_shape.startY, sv_shape.stopX, sv_shape.stopY, paint);
                        break;
                    case CIRCLE:
                        int radius = (int) Math.sqrt(Math.pow(sv_shape.stopX - sv_shape.startX, 2) + Math.pow(sv_shape.stopY - sv_shape.startY, 2));
                        canvas.drawCircle(sv_shape.startX, sv_shape.startY, radius, paint);
                        break;
                    case RECT:
                        Rect rect = new Rect(sv_shape.startX, sv_shape.startY, sv_shape.stopX, sv_shape.stopY);
                        canvas.drawRect(rect, paint);
                        break;
                }
            }

            if (finished == false) {
                paint.setColor(curColor);
                switch (curShape) {
                    case LINE:
                        canvas.drawLine(startX, startY, stopX, stopY, paint);
                        break;
                    case CIRCLE:
                        int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2) + Math.pow(stopY - startY, 2));
                        canvas.drawCircle(startX, startY, radius, paint);
                        break;
                    case RECT:
                        Rect rect = new Rect(startX, startY, stopX, stopY);
                        canvas.drawRect(rect, paint);
                        break;
                }
            }

        }
    }
    private static class MyShape {
        int shape_type, startX, startY, stopX, stopY;
        int color;
     }
}
