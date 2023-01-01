package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bitmaps_boom {
    private final Bitmap[] bitmaps;

    public Bitmaps_boom(Context context){
        bitmaps = new Bitmap[5];
        bitmaps[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boom1), 50, 50, false);
        bitmaps[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boom2), 50, 50, false);
        bitmaps[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boom3), 50, 50, false);
        bitmaps[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boom4), 50, 50, false);
        bitmaps[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boom5), 50, 50, false);
    }

    public Bitmap get(int life) {
        int idx = (life / 5) % bitmaps.length;
        return bitmaps[idx];
    }

}
