package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Boom_meteorBoss extends BoomBoss{

    private final Bitmap bitmap1;
    private final Bitmap bitmap2;
    private final Bitmap bitmap3;
    private final Bitmap bitmap4;
    private final Bitmap bitmap5;
    private final Bitmap bitmap6;
    private final Bitmap bitmap7;
    private final Bitmap bitmap8;

    public Boom_meteorBoss(Context context){
        super(40);
        this.bitmap1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor_boss_destroyed1), 152, 217, false);
        this.bitmap2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor_boss_destroyed2), 152, 217, false);
        this.bitmap3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor_boss_destroyed3), 152, 217, false);
        this.bitmap4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor_boss_destroyed4), 152, 217, false);
        this.bitmap5 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor_boss_destroyed5), 152, 217, false);
        this.bitmap6 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor_boss_destroyed6), 152, 217, false);
        this.bitmap7 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor_boss_destroyed7), 152, 217, false);
        this.bitmap8 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor_boss_destroyed8), 152, 217, false);
    }

    @Override
    public void draw(Canvas canvas) {
        Bitmap bm;
        if (getLife() > 35) bm = bitmap1;
        else if (getLife() > 30) bm = bitmap2;
        else if (getLife() > 25) bm = bitmap3;
        else if (getLife() > 20) bm = bitmap4;
        else if (getLife() > 15) bm = bitmap5;
        else if (getLife() > 10) bm = bitmap6;
        else if (getLife() > 5) bm = bitmap7;
        else bm = bitmap8;
        canvas.drawBitmap(bm, this.getX(), this.getY(), null);
    }
}
