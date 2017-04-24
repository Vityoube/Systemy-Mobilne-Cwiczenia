package com.example.andrii.poziomnica;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import static com.example.andrii.poziomnica.MainActivity.currentAxisDegree;
import static com.example.andrii.poziomnica.MainActivity.textX;
import static com.example.andrii.poziomnica.MainActivity.x;


/**
 * Created by Administrator on 2016-10-26.
 */

public class CanvasView extends View {
    public CanvasView(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {


         x = MainActivity.sensorX * MainActivity.scale + canvas.getWidth() / 2;
        textX=MainActivity.sensorX * MainActivity.scale + canvas.getWidth() / 2;
        if (x < (canvas.getWidth() / 10) + 50) {
            x = (canvas.getWidth() / 10) + 50;
        } else if (x > (9 * canvas.getWidth() / 10) - 50) {
            x = (9*canvas.getWidth() / 10) - 50;
        }
        Paint paint = new Paint();
        paint.setARGB(100,255,0,0);
        paint.setStrokeWidth(4);
        canvas.drawLine((canvas.getWidth() / 2) - 60, (canvas.getHeight() / 2)+52,(canvas.getWidth()/2)-60,(canvas.getHeight()/2)-52,paint);
        canvas.drawLine((canvas.getWidth() / 2) + 60, (canvas.getHeight() / 2)+52,(canvas.getWidth()/2)+60,(canvas.getHeight()/2)-52,paint);
        paint.setARGB(100,0,255,0);
        canvas.drawLine((canvas.getWidth() / 10), (canvas.getHeight() / 2)-52,(9*canvas.getWidth()/10),(canvas.getHeight()/2)-52,paint);
        canvas.drawLine((canvas.getWidth() / 10), (canvas.getHeight() / 2)+52,(9*canvas.getWidth()/10),(canvas.getHeight()/2)+52,paint);
        canvas.drawLine((canvas.getWidth() / 10), (canvas.getHeight() / 2)-52,(canvas.getWidth()/10),(canvas.getHeight()/2)+52,paint);
        canvas.drawLine((9*canvas.getWidth() / 10), (canvas.getHeight() / 2)-52,9*canvas.getWidth()/10,(canvas.getHeight()/2)+52,paint);
        paint.setStrokeWidth(4);
        paint.setARGB(100,0,0,255);
        canvas.drawCircle(x,canvas.getHeight()/2,50,paint);
        canvas.drawPaint(paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(16);
        canvas.drawText("Nachylenie: "+currentAxisDegree,textX,canvas.getHeight()/2-100,paint);
        invalidate();
    }
}