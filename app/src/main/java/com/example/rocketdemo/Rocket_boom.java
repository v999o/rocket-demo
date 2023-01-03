package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Rocket_boom extends GameObject{

    private int life = 40;
    private final Bitmap bitmap1;
    private final Bitmap bitmap2;
    private final Bitmap bitmap3;
    private final Bitmap bitmap4;
    private final Bitmap bitmap5;
    private final Bitmap bitmap6;
    private final Bitmap bitmap7;
    private final Bitmap bitmap8;

    public Rocket_boom(Context context, int x, int y){
        super(x, y, 85, 85);
        this.bitmap1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.rocket_boom1), 85, 85, false);
        this.bitmap2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.rocket_boom2), 85, 85, false);
        this.bitmap3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.rocket_boom3), 85, 85, false);
        this.bitmap4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.rocket_boom4), 85, 85, false);
        this.bitmap5 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.rocket_boom5), 85, 85, false);
        this.bitmap6 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.rocket_boom6), 85, 85, false);
        this.bitmap7 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.rocket_boom7), 85, 85, false);
        this.bitmap8 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.rocket_boom8), 85, 85, false);
    }

    @Override
    public void draw(Canvas canvas){
        Bitmap bm;
        if (life > 35) bm = bitmap1;
        else if (life > 30) bm = bitmap2;
        else if (life > 25) bm = bitmap3;
        else if (life > 20) bm = bitmap4;
        else if (life > 15) bm = bitmap5;
        else if (life > 10) bm = bitmap6;
        else if (life > 5) bm = bitmap7;
        else bm = bitmap8;
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
