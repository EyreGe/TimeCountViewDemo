package com.timecountviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created byTextDemo xionghaocai on 17/10/12.
 */

public class TimeCountView extends View {

    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mAreaPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int areaColor = 0xFFfffff;//方块区域颜色
    private int textColor = 0xFF000000;//文字颜色
    private float areaWith = 50f;//方块区域宽度
    private float areaHeight = 50f;//方块区域高度
    private float textSize = 10.0f;//text 大小
    private float areaSpaceWidth;//方块区域间隔
    private long initTime = 30 * 60 * 1000;//初始时间
    private long restTime;//剩余时间

    private TimeCount count;

    public TimeCountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TimeCountView, 0, 0);

        areaColor = a.getColor(R.styleable.TimeCountView_areaColor, areaColor);
        textColor = a.getColor(R.styleable.TimeCountView_textColor, textColor);
        areaWith = a.getDimension(R.styleable.TimeCountView_areaWidth, areaHeight);
        areaHeight = a.getDimension(R.styleable.TimeCountView_areaHeight, areaHeight);
        textSize = a.getDimension(R.styleable.TimeCountView_textSize, textSize);
        areaSpaceWidth = a.getDimension(R.styleable.TimeCountView_areaSpaceWidth, areaSpaceWidth);
        mAreaPaint.setColor(areaColor);
        mTextPaint.setColor(textColor);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        timeCount(restTime,canvas);
    }


    class TimeCount extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            restTime = millisUntilFinished;
            invalidate();
        }

        @Override
        public void onFinish() {

        }
    }


    private float getBaseLine(RectF targetRect) {
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        return (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
    }

    public void start() {
        count = new TimeCount(initTime, 1000);
        count.start();
    }


    public void setInitTime(long initTime){
        this.initTime=initTime;
    }

    /**
     * 控件倒计时
     * @param restTime
     * @param canvas
     */
    private void  timeCount(long restTime,Canvas canvas){
        long restSecond=restTime/1000;
        //获取分
        int minute= (int) (restSecond/60);

        //获取分的十位数
        int minute_1=(minute/10);

        //获取分的个位数
        int minute_2=minute%10;

        //获取秒
        int second= (int) (restSecond%60);

        //获取秒的十位
        int second_1=second/10;

        //获取秒的个位
        int second_2=second%10;



        RectF targetRect1 = new RectF(0, 0, areaWith, areaHeight);

        canvas.drawRoundRect(targetRect1, 10.0f, 10.0f, mAreaPaint);
        canvas.drawText(minute_1+"", targetRect1.centerX(), getBaseLine(targetRect1), mTextPaint);


        RectF targetRect2 = new RectF(areaWith + areaSpaceWidth, 0, 2 * areaWith + areaSpaceWidth, areaHeight);
        canvas.drawRoundRect(targetRect2, 10.0f, 10.0f, mAreaPaint);
        canvas.drawText(minute_2+"", targetRect2.centerX(), getBaseLine(targetRect2), mTextPaint);


        RectF targetRect3 = new RectF(2 * areaWith + areaSpaceWidth, 0, 2 * areaWith + areaSpaceWidth + 40, areaHeight);
        canvas.drawText(":", targetRect3.centerX(), getBaseLine(targetRect3), mTextPaint);

        RectF targetRect4 = new RectF(2 * areaWith + areaSpaceWidth + 40, 0, 3 * areaWith + areaSpaceWidth + 40, areaHeight);
        canvas.drawRoundRect(targetRect4, 10.0f, 10.0f, mAreaPaint);
        canvas.drawText(second_1+"", targetRect4.centerX(), getBaseLine(targetRect4), mTextPaint);


        RectF targetRect5 = new RectF(3 * areaWith + 2 * areaSpaceWidth + 40, 0, 4 * areaWith + 2 * areaSpaceWidth + 40, areaHeight);
        canvas.drawRoundRect(targetRect5, 10.0f, 10.0f, mAreaPaint);
        canvas.drawText(second_2+"", targetRect5.centerX(), getBaseLine(targetRect5), mTextPaint);

    }



}
