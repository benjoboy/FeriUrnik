package com.skupina1.urnik.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

@SuppressLint("AppCompatCustomView")
public class ImageView2 extends ImageView {
    public static float MAX_SCALE = 8f;
    public static float MIN_SCALE = 0.4f;
    public static float START_SCALE = 0.9f;
    public int width;
    public int height;

    private ArrayList<Point> points;
    Drawable baseDrawable = null;
    OvalShape ovalShape = null;
    ShapeDrawable shapeDrawable = null;
    Paint circlePaint = null, linePaint = null;

    private void init() {
        points = new ArrayList<>();
        ovalShape = new OvalShape();
        ovalShape.resize(1f, 1f);
        shapeDrawable = new ShapeDrawable(ovalShape);
        shapeDrawable.getPaint().setColor(Color.BLUE);
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(Color.BLUE);
        linePaint = new Paint();
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(4f);
        linePaint.setColor(Color.GREEN);
        setScale(START_SCALE);

        setOnTouchListener(new OnTouchListener() {
            Point Point0 = new Point();
            Point Point1 = new Point();
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getPointerCount() == 1)
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_MOVE:
                            float x = Point0.x + event.getX() - Point1.x;
                            float y = Point0.y + event.getY() - Point1.y;
                            setX(x);
                            setY(y);
                            Point0.set(x, y);
                            break;
                        case MotionEvent.ACTION_DOWN:
                            Point1.set(event.getX(), event.getY());
                            Point0.set(getX(), getY());
                            break;
                        case MotionEvent.ACTION_UP:
                            break;
                        default:
                            break;
                    }
                return true;
            }
        });

    }

    public ImageView2(Context context) {
        super(context);
        init();
    }

    public ImageView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < points.size() - 1; i++) {
            canvas.drawLine(
                    points.get(i).x * getWidth(),
                    points.get(i).y * getHeight(),
                    points.get(i + 1).x * getWidth(),
                    points.get(i + 1).y * getHeight(),
                    linePaint);
            canvas.drawCircle(
                    points.get(i).x * getWidth(),
                    points.get(i).y * getHeight(),
                    6f, circlePaint);
        }
        if (points.size() > 0)
            canvas.drawCircle(
                    points.get(points.size() - 1).x * getWidth(),
                    points.get(points.size() - 1).y * getHeight(),
                    6f, circlePaint);
    }

    public void setBase(int resourceID) {
        baseDrawable = getResources().getDrawable(resourceID);
        setImageDrawable(baseDrawable);
        width = baseDrawable.getIntrinsicWidth();
        height = baseDrawable.getIntrinsicHeight();
    }

    public void setTranslate(float left, float right) {
        getMatrix().setTranslate(left, right);
    }

    public void setScale(float v) {
        if (v < MIN_SCALE) {
            setScaleX(MIN_SCALE);
            setScaleY(MIN_SCALE);
        } else if (v > MAX_SCALE) {
            setScaleX(MAX_SCALE);
            setScaleY(MAX_SCALE);
        } else {
            setScaleX(v);
            setScaleY(v);
        }
    }

    public float getScale() {
        return getScaleX();//X in Y sta vedno enaka v mojem primeru
    }

    public void changeScale(float v) {
        float curr = getScaleX();
        setScale(curr + v);
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void clearPoints() {
        points.clear();
    }
}