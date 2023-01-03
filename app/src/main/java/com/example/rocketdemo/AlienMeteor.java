package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class AlienMeteor extends Alien{
    public AlienMeteor(Context context, int x, int y){
        super(25, 10, x, y, 52, 117);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.alien3_rot);
        this.setBm(Bitmap.createScaledBitmap(bitmap, 52, 117, false));
    }
}
