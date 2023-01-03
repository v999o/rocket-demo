package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class AlienUfo extends Alien{

    public AlienUfo(Context context, int x, int y){
        super(15, 6, x, y, 80, 80);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.alien2_rot);
        this.setBm(Bitmap.createScaledBitmap(bitmap, 80, 80, false));
    }
}