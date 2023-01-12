package com.example.rocketdemo;

import android.graphics.Canvas;

public class Boom_bugBoss extends BoomBoss{

    private final Bitmaps_boom_bugBoss bitmaps_boom_bugBoss;

    public Boom_bugBoss(Bitmaps_boom_bugBoss bitmaps_boom_bugBoss, int x, int y){
        super(49, x, y, 500, 700);
        this.bitmaps_boom_bugBoss = bitmaps_boom_bugBoss;
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmaps_boom_bugBoss.get(getLife()), this.getX(), this.getY(), null);
    }
}
