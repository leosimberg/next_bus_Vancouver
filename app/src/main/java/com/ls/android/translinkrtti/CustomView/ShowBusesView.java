/**
 * Created by Leonardo Simberg on 4/11/2016.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.ls.android.translinkrtti.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.View;

/**
 * Shows the next buses for a Route and Bus Stop
 *
 */
public class ShowBusesView extends View {
    private static final String TAG = ShowBusesView.class.getSimpleName();

    private static final int MAX_BUSES = 4;
    private static final int STROKE = 5;

    Pair<String, String> mTextPair[] = new Pair[0];
    String mTextError = "";
    float mDiameter;
    float mDistance, mSpace;
    float mPointerX, mPointerY;
    Paint mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mDashLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mSolidLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mTextTimePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mTextRoutePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public ShowBusesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCirclePaint.setColor(Color.BLUE);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(STROKE);
        mSolidLinePaint.setColor(Color.BLUE);
        mDashLinePaint.setColor(Color.BLUE);
        mDashLinePaint.setStyle(Paint.Style.STROKE);
        mDashLinePaint.setStrokeWidth(STROKE);
        mDashLinePaint.setPathEffect(new DashPathEffect(new float[]{5, 5}, 0));
        mTextTimePaint.setColor(Color.GRAY);
        mTextRoutePaint.setColor(Color.BLUE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        float xpad = (float)(getPaddingLeft() + getPaddingRight());
        float ypad = (float)(getPaddingTop() + getPaddingBottom());

        float ww = (float)w - xpad;
        float hh = (float)h - ypad;

        Log.d(TAG, "onSizeChanged: " + hh);
        mDiameter = (float)(hh * 0.07) + STROKE;
        mDistance = (float)(hh * 0.17);
        mSpace = (float)(hh * 0.007);
        mPointerX = (ww / 3) + getPaddingLeft();
        mPointerY = (float)(hh * 0.07) + getPaddingTop();
        mTextTimePaint.setTextSize((float) (mDiameter * 2 * 0.6));
        mTextRoutePaint.setTextSize((float) (mDiameter * 2 * 0.4));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float y = mPointerY;
        int times = Math.min(mTextPair.length, MAX_BUSES);

        for (int i = 0; i < times; i++) {
            float yl = y + mDiameter + mSpace;
            drawAlignedMiddleText(canvas, mTextRoutePaint, mTextPair[i].first,
                    mPointerX, y, Paint.Align.CENTER);
            drawAlignedMiddleText(canvas, mTextTimePaint, mTextPair[i].second,
                    (mPointerX + mDiameter + 15), y + mSpace, Paint.Align.LEFT);
            canvas.drawCircle(mPointerX, y, mDiameter - STROKE, mCirclePaint);
            y = y + mDiameter + mDistance + mSpace;
            if (i < (times - 1)){
                canvas.drawRect(mPointerX - 5, yl, mPointerX + 5,
                        yl + mDistance - mSpace - mDiameter, mSolidLinePaint);
            } else {
                canvas.drawRect(mPointerX - 2, yl, mPointerX + 2,
                        yl + mDistance - mSpace - mDiameter, mDashLinePaint);
            }
        }
    }

    private void drawAlignedMiddleText(Canvas canvas,
                                       Paint paint,
                                       String text,
                                       float x,
                                       float y,
                                       Paint.Align align) {
        Rect rect = new Rect();
        paint.setTextAlign(align);
        paint.getTextBounds(text, 0, text.length(), rect);
        y = y - rect.top - rect.height()/2;
        canvas.drawText(text, x, y, paint);
    }

    public void setData(Pair<String, String> data[]){
        mTextPair = data;
        this.invalidate();
    }
}
