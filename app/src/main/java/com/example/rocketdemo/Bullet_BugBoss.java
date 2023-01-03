package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bullet_BugBoss extends Bullet{

    public Bullet_BugBoss(Context context, int x, int y){
        super(10, x, y, 10, 10);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet_bug_boss);
        this.setBm(Bitmap.createScaledBitmap(bitmap, 10, 10, false));
    }
}
