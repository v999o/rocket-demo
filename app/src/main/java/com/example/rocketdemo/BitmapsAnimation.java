package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapsAnimation {

    private final Bitmap[] bitmaps;

    protected BitmapsAnimation(Context context, int w, int h, int... r) {
        bitmaps = new Bitmap[r.length];
        for (int i=0; i < r.length; i++) {
            bitmaps[i] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), r[i]), w, h, false);
        }
    }

    public Bitmap get(int life) {
        int idx = (bitmaps.length-1) - ((life / 5) % (bitmaps.length+1));
        return bitmaps[idx];
    }
}
