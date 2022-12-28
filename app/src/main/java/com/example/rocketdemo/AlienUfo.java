package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class AlienUfo extends Alien{

    public AlienUfo(Context context){
        super(15, 6);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.alien2_rot);
        this.setBm(Bitmap.createScaledBitmap(bitmap, 80, 80, false));
    }
}