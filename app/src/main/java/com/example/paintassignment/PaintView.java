package com.example.paintassignment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
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
        PointF pt;
        float radius;
        float x;
        float y;
        int colour;
        public PaintCoordinate(float x, float y, int colour){
            this.x = x;
            this.y = y;
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
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(PaintCoordinate pc : points) {
            paint.setColor(pc.colour);
            canvas.drawCircle(pc.x, pc.y,pc.radius, paint);
        }
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
    public boolean onTouch(View view, MotionEvent me) {
        PaintCoordinate pc = new PaintCoordinate(me.getX(), me.getY(), random.nextInt());

        PointF pt = new PointF();
        pt.set(me.getX(), me.getY());

        pc.pt = pt;
        pc.radius = radius;
        points.add(pc);

        invalidate();

        return true;
    }
}
