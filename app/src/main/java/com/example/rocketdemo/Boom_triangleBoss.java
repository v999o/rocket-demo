package com.example.rocketdemo;

import android.graphics.Canvas;

public class Boom_triangleBoss extends BoomBoss{

    private final Bitmaps_boom_triangleBoss bitmaps_boom_triangleBoss;

    public Boom_triangleBoss(Bitmaps_boom_triangleBoss bitmaps_boom_triangleBoss, int x, int y){
        super(50, x, y, 200, 300);
        this.bitmaps_boom_triangleBoss = bitmaps_boom_triangleBoss;
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmaps_boom_triangleBoss.get(getLife()), this.getX(), this.getY(), null);
    }
}
