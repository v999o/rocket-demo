package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class AlienTriangle extends Alien {

    public AlienTriangle(Context context, int x, int y){
        super(10, 5, x, y, 51, 31);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.alien1_rot);
        this.setBm(Bitmap.createScaledBitmap(bitmap, 51, 31, false));
    }
}


