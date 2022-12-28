package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bullet_triangleBoss extends Bullet{

    public Bullet_triangleBoss(Context context){
        super(10);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet_triangle_boss);
        this.setBm(Bitmap.createScaledBitmap(bitmap, 10, 10, false));
    }
}
