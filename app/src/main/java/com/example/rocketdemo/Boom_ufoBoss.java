package com.example.rocketdemo;

import android.graphics.Canvas;

public class Boom_ufoBoss extends BoomBoss{

    private final Bitmaps_boom_ufoBoss bitmaps_boom_ufoBoss;

    public Boom_ufoBoss(Bitmaps_boom_ufoBoss bitmaps_boom_ufoBoss){
        super(55);
        this.bitmaps_boom_ufoBoss = bitmaps_boom_ufoBoss;
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmaps_boom_ufoBoss.get(getLife()), this.getX(), this.getY(), null);
    }
}
