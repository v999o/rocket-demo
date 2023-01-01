package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bitmaps_boom_triangleBoss {
    private final Bitmap[] bitmaps;

    public Bitmaps_boom_triangleBoss(Context context){
        bitmaps = new Bitmap[10];
        bitmaps[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_destroyed1), 200, 300, false);
        bitmaps[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_destroyed2), 200, 300, false);
        bitmaps[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_destroyed3), 200, 300, false);
        bitmaps[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_destroyed4), 200, 300, false);
        bitmaps[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_destroyed5), 200, 300, false);
        bitmaps[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_destroyed6), 200, 300, false);
        bitmaps[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_destroyed7), 200, 300, false);
        bitmaps[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_destroyed8), 200, 300, false);
        bitmaps[8] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_destroyed9), 200, 300, false);
        bitmaps[9] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle_boss_destroyed10), 200, 300, false);

    }

    public Bitmap get(int life) {
        int idx = bitmaps.length - ((life / 5) % bitmaps.length+1);
        return bitmaps[idx];
    }

}
