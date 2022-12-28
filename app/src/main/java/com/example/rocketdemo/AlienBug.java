package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class AlienBug extends Alien{
    public AlienBug(Context context){
        super(20, 8);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.alien4_rot);
        this.setBm(Bitmap.createScaledBitmap(bitmap, 39, 90, false));
    }
}
