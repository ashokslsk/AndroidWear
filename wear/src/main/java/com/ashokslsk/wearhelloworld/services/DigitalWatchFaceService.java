package com.ashokslsk.wearhelloworld.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.wearable.watchface.CanvasWatchFaceService;
import android.support.wearable.watchface.WatchFaceService;
import android.support.wearable.watchface.WatchFaceStyle;
import android.text.format.Time;
import android.view.SurfaceHolder;
import android.view.WindowInsets;

import com.ashokslsk.wearhelloworld.R;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by ashok.kumar on 07/04/16.
 */
public class DigitalWatchFaceService extends CanvasWatchFaceService {

    @Override
    public Engine onCreateEngine() {
        return new Engine();
    }

    private class Engine extends CanvasWatchFaceService.Engine{
        private Typeface WATCH_TEXT_TYPEFACE = Typeface.create(Typeface.SERIF,Typeface.NORMAL);
        private Paint mBackGroundColorPaint;
        private Paint mTextColorPaint;
        private int mBackGroundColor = Color.parseColor("white");
        private int mTextColor = Color.parseColor("cyan");

        private Time mTime;

        private static final int MSG_UPDATE_TIME_ID = 1;
        private static final long DEFAULT_UPDATE_RATE_MS= 1000;
        private long mUpdateRateMs = 1000;

        private boolean mHasTimeZoneRecieverBeenRegistered = false;
        private boolean mIsInMuteMode;
        private boolean mIsLowBitAmbient;

        private float mXOffset;
        private float mYOffset;

        final BroadcastReceiver mTimeZoneBroadCastReciever = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mTime.clear(intent.getStringExtra("time-zone"));
                mTime.setToNow();
            }
        };

        private final Handler mTimeHandler = new Handler()  {
            @Override
            public void handleMessage(Message msg) {
                switch(msg.what){
                    case MSG_UPDATE_TIME_ID:{
                        invalidate();
                        if(isVisible() && !isInAmbientMode()){
                            long curTime = System.currentTimeMillis();
                            long delay = mUpdateRateMs - (curTime % mUpdateRateMs);
                            mTimeHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIME_ID,delay);
                        }
                    }
                }
            }
        };



        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);
            setWatchFaceStyle(new WatchFaceStyle.Builder(DigitalWatchFaceService.this)
            .setBackgroundVisibility(WatchFaceStyle.BACKGROUND_VISIBILITY_INTERRUPTIVE)
            .setCardPeekMode(WatchFaceStyle.PEEK_MODE_VARIABLE)
            .setShowSystemUiTime(false)
            .build());

            mBackGroundColorPaint = new Paint();
            mBackGroundColorPaint.setColor(mBackGroundColor);

            mTextColorPaint = new Paint();
            mTextColorPaint.setColor(mTextColor);
            mTextColorPaint.setTypeface(WATCH_TEXT_TYPEFACE);
            mTextColorPaint.setTextSize(getResources().getDimension(R.dimen.watch_face_text_size));
            mTextColorPaint.setAntiAlias(true);

            mTime = new Time();


        }

        @Override
        public void onTimeTick() {
            super.onTimeTick();
            invalidate();
        }

        @Override
        public void onDraw(Canvas canvas, Rect bounds) {
            super.onDraw(canvas, bounds);
            mTime.setToNow();
            canvas.drawRect(0,0,bounds.width(),bounds.height(),mBackGroundColorPaint);

            //Drawing the time on watch face
            String timeText = getHourString() + ":" + String.format("%02d", mTime.minute);
            if(isInAmbientMode() || mIsInMuteMode) {
                timeText += (mTime.hour < 12) ? "AM" : "PM";
            }else {
                timeText += String.format(":%02d",mTime.second);
            }
            canvas.drawText(timeText,mXOffset,mYOffset,mTextColorPaint);
        }

        private String getHourString(){
            if(mTime.hour%12 == 0 ){
                return "12";
            }else if(mTime.hour<=12){
                return String.valueOf(mTime.hour);
            }else{
                return String.valueOf(mTime.hour-12);
            }
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);

            if(visible){
                if(!mHasTimeZoneRecieverBeenRegistered){
                    IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_CHANGED);
                    DigitalWatchFaceService.this.registerReceiver(mTimeZoneBroadCastReciever,filter);
                    mHasTimeZoneRecieverBeenRegistered = true;
                }

                mTime.clear(TimeZone.getDefault().getID());
                mTime.setToNow();
            }else{
                if(mHasTimeZoneRecieverBeenRegistered){
                    DigitalWatchFaceService.this.unregisterReceiver(mTimeZoneBroadCastReciever);
                    mHasTimeZoneRecieverBeenRegistered = false;
                }
            }

            updateTimer();
        }

        @Override
        public void onPropertiesChanged(Bundle properties) {
            super.onPropertiesChanged(properties);

            if(properties.getBoolean(PROPERTY_BURN_IN_PROTECTION, false)){

                mIsLowBitAmbient = properties.getBoolean(PROPERTY_LOW_BIT_AMBIENT,false);

            }
        }

        @Override
        public void onAmbientModeChanged(boolean inAmbientMode) {
            super.onAmbientModeChanged(inAmbientMode);

            if(inAmbientMode){
                mTextColorPaint.setColor(Color.parseColor("white"));
                mBackGroundColorPaint.setColor(Color.parseColor("black"));
            }else {
                mTextColorPaint.setColor(Color.parseColor("cyan"));
                mBackGroundColorPaint.setColor(Color.parseColor("white"));
            }

            if(mIsLowBitAmbient){
                mTextColorPaint.setAntiAlias(!inAmbientMode);
                mBackGroundColorPaint.setAntiAlias(!inAmbientMode);
            }

            invalidate();
            updateTimer();
        }

        @Override
        public void onInterruptionFilterChanged(int interruptionFilter) {
            super.onInterruptionFilterChanged(interruptionFilter);

            boolean isDeviceMuted = interruptionFilter== WatchFaceService.INTERRUPTION_FILTER_NONE;
            if(isDeviceMuted){
                mUpdateRateMs = TimeUnit.MINUTES.toMillis(1);
            }else{
                mUpdateRateMs = DEFAULT_UPDATE_RATE_MS;
            }

            if(mIsInMuteMode != isDeviceMuted){
                mIsInMuteMode = isDeviceMuted;
                int alpha = (isDeviceMuted) ? 100:255;
                mTextColorPaint.setAlpha(alpha);
                invalidate();
            }
            updateTimer();
        }

        @Override
        public void onApplyWindowInsets(WindowInsets insets) {
            super.onApplyWindowInsets(insets);

            mYOffset = getResources().getDimension(R.dimen.y_offset);
            if(insets.isRound()){
                mXOffset = getResources().getDimension(R.dimen.x_offset_round);
            }else {
                mXOffset = getResources().getDimension(R.dimen.x_offset_square);
            }
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            mTimeHandler.removeMessages(MSG_UPDATE_TIME_ID);
        }

        private void updateTimer(){
            mTimeHandler.removeMessages(MSG_UPDATE_TIME_ID);
            if(isVisible()&& !isInAmbientMode()){
                mTimeHandler.sendEmptyMessage(MSG_UPDATE_TIME_ID);
            }
        }
    }
}
