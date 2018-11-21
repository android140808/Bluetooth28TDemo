package com.avater.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BezierView extends View {

    private Paint mPaint;
    private Point startPoint;
    private Point endPoint;
    private Point controlPoint;
    private int centenX, centenY;


    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        startPoint = new Point(0, 0);
        endPoint = new Point(0, 0);
        controlPoint = new Point(0, 0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centenX = getWidth() / 2;
        centenY = getHeight() / 2;
        startPoint.x = centenX - 200;
        startPoint.y = centenY;
        endPoint.x = centenX + 200;
        endPoint.y = centenY;
        controlPoint.x = centenX;
        controlPoint.y = centenY - 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        canvas.drawPoint(startPoint.x, startPoint.y, mPaint);
        canvas.drawPoint(endPoint.x, endPoint.y, mPaint);
        canvas.drawPoint(controlPoint.x, controlPoint.y, mPaint);

        mPaint.setStrokeWidth(5);
        canvas.drawLine(startPoint.x, startPoint.y, controlPoint.x, controlPoint.y, mPaint);
        canvas.drawLine(endPoint.x, endPoint.y, controlPoint.x, controlPoint.y, mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(3);
        Path path = new Path();
        path.moveTo(startPoint.x, startPoint.y);
        path.quadTo(controlPoint.x, controlPoint.y, endPoint.x, endPoint.y);
        canvas.drawPath(path, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        controlPoint.x = (int) event.getX();
        controlPoint.y = (int) event.getY();
        invalidate();
        return true;
    }
}
