package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Boom_ufoBoss extends BoomBoss{
    private final Bitmap bitmap1;
    private final Bitmap bitmap2;
    private final Bitmap bitmap3;
    private final Bitmap bitmap4;
    private final Bitmap bitmap5;
    private final Bitmap bitmap6;
    private final Bitmap bitmap7;
    private final Bitmap bitmap8;
    private final Bitmap bitmap9;
    private final Bitmap bitmap10;
    private final Bitmap bitmap11;

    public Boom_ufoBoss(Context context){
        super(55);
        this.bitmap1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed1), 700, 110, false);
        this.bitmap2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed2), 700, 110, false);
        this.bitmap3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed3), 700, 110, false);
        this.bitmap4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed4), 700, 110, false);
        this.bitmap5 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed5), 700, 110, false);
        this.bitmap6 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed6), 700, 110, false);
        this.bitmap7 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed7), 700, 110, false);
        this.bitmap8 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed8), 700, 110, false);
        this.bitmap9 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed9), 700, 110, false);
        this.bitmap10 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed10), 700, 110, false);
        this.bitmap11 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed11), 700, 110, false);
    }

    @Override
    public void draw(Canvas canvas){
        Bitmap bm;
        if (getLife() > 50) bm = bitmap1;
        else if (getLife() > 45) bm = bitmap2;
        else if (getLife() > 40) bm = bitmap3;
        else if (getLife() > 35) bm = bitmap4;
        else if (getLife() > 30) bm = bitmap5;
        else if (getLife() > 25) bm = bitmap6;
        else if (getLife() > 20) bm = bitmap7;
        else if (getLife() > 15) bm = bitmap8;
        else if (getLife() > 10) bm = bitmap9;
        else if (getLife() > 5) bm = bitmap10;
        else bm = bitmap11;
        canvas.drawBitmap(bm, this.getX(), this.getY(), null);
    }
}
