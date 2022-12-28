package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bullet_SurvivalBoss extends Bullet{

    public Bullet_SurvivalBoss(Context context){
        super(10);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_bullet);
        this.setBm(Bitmap.createScaledBitmap(bitmap, 10, 10, false));
    }
}
