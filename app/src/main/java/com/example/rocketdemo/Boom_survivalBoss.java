package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Boom_survivalBoss extends GameObject{

    private int life = 45;
    private final Bitmap bitmap1;
    private final Bitmap bitmap2;
    private final Bitmap bitmap3;
    private final Bitmap bitmap4;
    private final Bitmap bitmap5;
    private final Bitmap bitmap6;
    private final Bitmap bitmap7;
    private final Bitmap bitmap8;
    private final Bitmap bitmap9;

    public Boom_survivalBoss(Context context){
        this.bitmap1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_survival_destroyed1), 200, 126, false);
        this.bitmap2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_survival_destroyed2), 200, 126, false);
        this.bitmap3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_survival_destroyed3), 200, 126, false);
        this.bitmap4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_survival_destroyed4), 200, 126, false);
        this.bitmap5 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_survival_destroyed5), 200, 126, false);
        this.bitmap6 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_survival_destroyed6), 200, 126, false);
        this.bitmap7 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_survival_destroyed7), 200, 126, false);
        this.bitmap8 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_survival_destroyed8), 200, 126, false);
        this.bitmap9 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_survival_destroyed9), 200, 126, false);
    }

    @Override
    public void draw(Canvas canvas){
        Bitmap bm;
        if (life > 40) bm = bitmap1;
        else if (life > 35) bm = bitmap2;
        else if (life > 30) bm = bitmap3;
        else if (life > 25) bm = bitmap4;
        else if (life > 20) bm = bitmap5;
        else if (life > 15) bm = bitmap6;
        else if (life > 10) bm = bitmap7;
        else if (life > 5) bm = bitmap8;
        else bm = bitmap9;
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
