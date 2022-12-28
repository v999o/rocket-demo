package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Boom extends GameObject{

    private int life = 25;
    private final Bitmap bitmap1;
    private final Bitmap bitmap2;
    private final Bitmap bitmap3;
    private final Bitmap bitmap4;
    private final Bitmap bitmap5;

    public Boom(Context context){
        this.bitmap1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boom1), 50, 50, false);
        this.bitmap2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boom2), 50, 50, false);
        this.bitmap3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boom3), 50, 50, false);
        this.bitmap4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boom4), 50, 50, false);
        this.bitmap5 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boom5), 50, 50, false);
    }

    @Override
    public void draw(Canvas canvas){
        Bitmap bm;
        if (life > 20) bm = bitmap1;
        else if (life > 15) bm = bitmap2;
        else if (life > 10) bm = bitmap3;
        else if (life > 5) bm = bitmap4;
        else bm = bitmap5;
        canvas.drawBitmap(bm, this.getX(), this.getY(), null);
    }

    @Override
    public void update(){
        if (life > 0){
            life -= 1;
        }
    }

    public int getLife() {
        return life;
    }
}
