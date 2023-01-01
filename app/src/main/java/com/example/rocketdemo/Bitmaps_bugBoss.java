package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bitmaps_bugBoss {

    private final Bitmap[] bitmaps;

    public Bitmaps_bugBoss(Context context){
        bitmaps = new Bitmap[10];
        bitmaps[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss1), 300, 500, false);
        bitmaps[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_2), 300, 500, false);
        bitmaps[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_3), 300, 500, false);
        bitmaps[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_4), 300, 500, false);
        bitmaps[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_5), 300, 500, false);

        bitmaps[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_damage1), 300, 500, false);
        bitmaps[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_damage2), 300, 500, false);
        bitmaps[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_damage3), 300, 500, false);
        bitmaps[8] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_damage4), 300, 500, false);
        bitmaps[9] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_damage5), 300, 500, false);
    }

    public Bitmap get(int hp, boolean damage){
        int idx;
        double roundedHp = Math.ceil(hp/3.0);
        if (damage) {
            idx = (bitmaps.length/2 - (int) roundedHp)+5;
        } else {
            idx = (bitmaps.length/2 - (int) roundedHp);
        }
        return bitmaps[idx];
    }
}
