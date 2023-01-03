package com.example.rocketdemo;

import android.graphics.Canvas;

public class MeteorBoss extends Boss{

    private final Bitmaps_meteorBoss bitmaps_meteorBoss;

    public MeteorBoss(int stop_height, Bitmaps_meteorBoss bitmaps_meteorBoss, int x){
        super(5, 50, stop_height, x, -117, 52, 117);
        this.bitmaps_meteorBoss = bitmaps_meteorBoss;
    }
    @Override
    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmaps_meteorBoss.get(getHp(), isDamage()), this.getX(), this.getY(), null);
    }


}
