package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bitmaps_boom_ufoBoss {
    private final Bitmap[] bitmaps;

    public Bitmaps_boom_ufoBoss(Context context){
        bitmaps = new Bitmap[11];
        bitmaps[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed1), 700, 110, false);
        bitmaps[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed2), 700, 110, false);
        bitmaps[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed3), 700, 110, false);
        bitmaps[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed4), 700, 110, false);
        bitmaps[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed5), 700, 110, false);
        bitmaps[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed6), 700, 110, false);
        bitmaps[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed7), 700, 110, false);
        bitmaps[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed8), 700, 110, false);
        bitmaps[8] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed9), 700, 110, false);
        bitmaps[9] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed10), 700, 110, false);
        bitmaps[10] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_destroyed10), 700, 110, false);

    }

    public Bitmap get(int life) {
        int idx = bitmaps.length - ((life / 5) % bitmaps.length+1);
        return bitmaps[idx];
    }
}
