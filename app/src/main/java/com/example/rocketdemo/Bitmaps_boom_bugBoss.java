package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bitmaps_boom_bugBoss {
    private final Bitmap[] bitmaps;

    public Bitmaps_boom_bugBoss(Context context){
        bitmaps = new Bitmap[10];
        bitmaps[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_destroyed1), 500, 700, false);
        bitmaps[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_destroyed2), 500, 700, false);
        bitmaps[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_destroyed3), 500, 700, false);
        bitmaps[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_destroyed4), 500, 700, false);
        bitmaps[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_destroyed5), 500, 700, false);
        bitmaps[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_destroyed6), 500, 700, false);
        bitmaps[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_destroyed7), 500, 700, false);
        bitmaps[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_destroyed8), 500, 700, false);
        bitmaps[8] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_destroyed9), 500, 700, false);
        bitmaps[9] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_destroyed10), 500, 700, false);

    }

    public Bitmap get(int life) {
        int idx = bitmaps.length - ((life / 5) % bitmaps.length+1);
        return bitmaps[idx];
    }
}
