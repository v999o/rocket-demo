package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bullet_rocket extends Bullet {

    public Bullet_rocket(Context context, int x, int y){
        super(-10, x, y, 10, 10);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.rocket_bullet);
        this.setBm(Bitmap.createScaledBitmap(bitmap, 10, 10, false));
    }
}
