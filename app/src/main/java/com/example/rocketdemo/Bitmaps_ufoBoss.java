package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bitmaps_ufoBoss {
    private final Bitmap[] bitmaps;

    public Bitmaps_ufoBoss(Context context){
        bitmaps = new Bitmap[10];
        bitmaps[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_1), 400, 110, false);
        bitmaps[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_2), 400, 110, false);
        bitmaps[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_3), 400, 110, false);
        bitmaps[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_4), 400, 110, false);
        bitmaps[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_5), 400, 110, false);

        bitmaps[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_damage1), 400, 110, false);
        bitmaps[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_damage2), 400, 110, false);
        bitmaps[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_damage3), 400, 110, false);
        bitmaps[8] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_damage4), 400, 110, false);
        bitmaps[9] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_damage5), 400, 110, false);
    }

    public Bitmap get(int hp, boolean damage){
        int idx;
        double roundedHp = Math.ceil(hp/2.0);
        if (damage) {
            idx = (bitmaps.length/2 - (int) roundedHp)+5;
        } else {
            idx = (bitmaps.length/2 - (int) roundedHp);
        }
        return bitmaps[idx];
    }
}

