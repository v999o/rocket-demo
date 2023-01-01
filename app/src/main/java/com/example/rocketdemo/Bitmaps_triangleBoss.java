package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bitmaps_triangleBoss {

    private final Bitmap[] bitmaps;

    public Bitmaps_triangleBoss(Context context){
        bitmaps = new Bitmap[10];
        bitmaps[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss), 100, 166, false);
        bitmaps[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss2), 100, 166, false);
        bitmaps[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss3), 100, 166, false);
        bitmaps[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss4), 100, 166, false);
        bitmaps[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss5), 100, 166, false);

        bitmaps[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_damage1), 100, 166, false);
        bitmaps[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_damage2), 100, 166, false);
        bitmaps[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_damage3), 100, 166, false);
        bitmaps[8] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_damage4), 100, 166, false);
        bitmaps[9] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_damage5), 100, 166, false);
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
