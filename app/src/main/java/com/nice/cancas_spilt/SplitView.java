package com.nice.cancas_spilt;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SplitView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private float d = 3; //圆半径
    private ArrayList<Ball> mBalls = new ArrayList<>();
    private ValueAnimator mValueAnimator;

    public SplitView(Context context) {
        super(context);
        init();
    }

    public SplitView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SplitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    /**
     * 初始化bitmap粒子化数据
     */
    private void init() {
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.girl);
        //双重for 获取每一个粒子ball的数据
        for (int i = 0; i < mBitmap.getWidth(); i++) {
            for (int j = 0; j < mBitmap.getHeight(); j++) {
                Ball ball = new Ball();
                ball.color = mBitmap.getPixel(i, j);
                ball.x = i * d + d / 2; //确定每一个ball粒子的圆心坐标
                ball.y = j * d + d / 2;
                ball.r = d / 2;

                //速度（）
                ball.vX = (float) (Math.pow(-1, Math.ceil(Math.random() * 1000)) * 20 * Math.random());
                ball.vY = rangInt(-15, 35);

                //加速度
                ball.aX = 0;
                ball.aY = 0.98f;
                mBalls.add(ball);
            }
        }
        mValueAnimator = ValueAnimator.ofFloat(0, 1);
        mValueAnimator.setRepeatCount(-1);
        mValueAnimator.setDuration(2000);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                updateBall();
                invalidate();
            }
        });
    }

    private float rangInt(int i, int j) {
        int max = Math.max(i, j);
        int min = Math.min(i, j);
        return (int) (min + Math.ceil(Math.random() * (max - min)));
    }

    private void updateBall() {
        //更新粒子的位置,自由落地运动
        for (Ball ball : mBalls) {
            ball.x += ball.vX;
            ball.y += ball.vY;

            ball.vX += ball.aX;
            ball.vY += ball.aY;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.translate(500, 500);
        for (Ball ball : mBalls) {
            mPaint.setColor(ball.color);
            canvas.drawCircle(ball.x, ball.y, ball.r, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mValueAnimator.start();
        }
        return super.onTouchEvent(event);
    }
}
