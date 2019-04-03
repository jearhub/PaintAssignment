package com.example.paintassignment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class PaintView extends View implements View.OnTouchListener{

    Paint paint = new Paint();
    Random random = new Random();

    class PaintCoordinate
    {
        float radius;
        float x;
        float y;
        int colour;
        PaintCoordinate(float x, float y, float radius, int colour){
            this.x = x;
            this.y = y;
            this.radius = radius;
            this.colour = colour;
        }
    }

    ArrayList <PaintCoordinate> points = new ArrayList<>();
    ArrayList<PaintCoordinate> undonePoints = new ArrayList<>();

    public void setRadius(float radius) {
        this.radius = radius;
    }

    float radius = 5;

    public PaintView(Context context) {
        super(context);
        setup();
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    public void setup()
    {
        setOnTouchListener(this);
    }

    public void onClickUndo () {
        if (points.size()>0)
        {
            undonePoints.add(points.remove(points.size()-1));
            invalidate();
        }
    }

    public void onClickRedo (){
        if (undonePoints.size()>0)
        {
            points.add(undonePoints.remove(undonePoints.size()-1));
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw ( canvas );

        for(PaintCoordinate pc : points) {
           paint.setColor(pc.colour);
         canvas.drawCircle(pc.x, pc.y,pc.radius, paint);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent me) {

        int pointerCount = me.getPointerCount();

        for (int i = 0; i < pointerCount; i++) {
            float x = me.getX ( i );
            float y = me.getY ( i );
            int id = me.getPointerId ( i );

            if(id == 0){
                PaintCoordinate pc = new PaintCoordinate (x, y, radius, random.nextInt());
                points.add(pc);
            }
            if(id == 1){
                PaintCoordinate pc = new PaintCoordinate (x, y, radius, random.nextInt());
                points.add((pc));
            }
            if(id == 2){
                PaintCoordinate pc = new PaintCoordinate (x, y, radius, random.nextInt());
                points.add((pc));
            }
            if(id == 3){
                PaintCoordinate pc = new PaintCoordinate (x, y, radius, random.nextInt());
                points.add((pc));
            }
            if(id == 4){
                PaintCoordinate pc = new PaintCoordinate (x, y, radius, random.nextInt());
                points.add((pc));
            }
        }

        invalidate();

        return true;
    }
}
