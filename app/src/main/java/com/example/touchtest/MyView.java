package com.example.touchtest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyView extends View {

    private static final int R = 10;
    private ArrayList<MyPoint> myPoints;
    private Paint paint;
    private int currentColor;
    private int brushType;
    private int brushSize;

    public MyView(Context context) {
        super(context);
        initialize();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    public void setPaintColor(int color) {
        currentColor = color;
    }

    public void setBrushType(int type){
        this.brushType = type;
    }

    public void setBrushSize(int size){
        this.brushSize = size;
    }

    private void initialize() {
        myPoints = new ArrayList<>();
        paint = new Paint();
        currentColor = Color.BLACK;
        brushType = com.example.touchtest.R.drawable.brush_type1;
        brushSize = 10;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        ColorFilter colorFilter;
        for (int i = 0; i < myPoints.size(); i += 1) {
            MyPoint myPoint = myPoints.get(i);
            colorFilter = new PorterDuffColorFilter(myPoint.color, PorterDuff.Mode.SRC_ATOP);
            paint.setColorFilter(colorFilter);
            canvas.drawBitmap(reduceSize(myPoint.brushType, myPoint.brushSize)
                    , myPoint.x - R, myPoint.y - R, paint);
        }
    }

    private Bitmap reduceSize(int brushType, int brushSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = brushSize;
        return BitmapFactory.decodeResource(getResources(), brushType, options);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                myPoints.add(new MyPoint(event.getX(), event.getY()
                        , currentColor, brushType, brushSize));
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                return true;
        }
        return super.onTouchEvent(event);
    }

    public void clear() {
        myPoints.clear();
        invalidate();
    }
}
