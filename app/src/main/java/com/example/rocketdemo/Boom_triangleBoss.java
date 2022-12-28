package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Boom_triangleBoss extends BoomBoss{
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

    public Boom_triangleBoss(Context context){
        super(50);
        this.bitmap1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_destroyed1), 200, 300, false);
        this.bitmap2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_destroyed2), 200, 300, false);
        this.bitmap3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_destroyed3), 200, 300, false);
        this.bitmap4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_destroyed4), 200, 300, false);
        this.bitmap5 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_destroyed5), 200, 300, false);
        this.bitmap6 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_destroyed6), 200, 300, false);
        this.bitmap7 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_destroyed7), 200, 300, false);
        this.bitmap8 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_destroyed8), 200, 300, false);
        this.bitmap9 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_destroyed9), 200, 300, false);
        this.bitmap10 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_destroyed10), 200, 300, false);
    }

    @Override
    public void draw(Canvas canvas){
        Bitmap bm;
        if (getLife() > 45) bm = bitmap1;
        else if (getLife() > 40) bm = bitmap2;
        else if (getLife() > 35) bm = bitmap3;
        else if (getLife() > 30) bm = bitmap4;
        else if (getLife() > 25) bm = bitmap5;
        else if (getLife() > 20) bm = bitmap6;
        else if (getLife() > 15) bm = bitmap7;
        else if (getLife() > 10) bm = bitmap8;
        else if (getLife() > 5) bm = bitmap9;
        else bm = bitmap10;
        canvas.drawBitmap(bm, this.getX(), this.getY(), null);
    }
}
