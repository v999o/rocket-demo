package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Rocket extends GameObject {

    public Rocket(Context context, int x, int y){
        super(x, y, 34, 85);
        //this.bm = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.rocket), this.width, this.height, true);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.rocket_rot);
        this.setBm(Bitmap.createScaledBitmap(bitmap, 34, 85, false));
    }
}
