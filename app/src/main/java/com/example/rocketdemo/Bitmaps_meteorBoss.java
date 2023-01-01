package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bitmaps_meteorBoss {
    private final Bitmap[] bitmaps;

    public Bitmaps_meteorBoss(Context context){
        bitmaps = new Bitmap[10];
        bitmaps[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor_boss1), 52, 117, false);
        bitmaps[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor_boss2), 52, 117, false);
        bitmaps[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor_boss3), 52, 117, false);
        bitmaps[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor_boss4), 52, 117, false);
        bitmaps[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor_boss5), 52, 117, false);

        bitmaps[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor_boss_damage1), 52, 117, false);
        bitmaps[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor_boss_damage2), 52, 117, false);
        bitmaps[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor_boss_damage3), 52, 117, false);
        bitmaps[8] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor_boss_damage4), 52, 117, false);
        bitmaps[9] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor_boss_damage5), 52, 117, false);
    }

    public Bitmap get(int hp, boolean damage){
        int idx;
        if (damage) {
            idx = (bitmaps.length/2 - hp)+5;
        } else {
            idx = (bitmaps.length/2 - hp);
        }
        return bitmaps[idx];
    }
}
