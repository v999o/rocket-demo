package com.example.rocketdemo;

import android.graphics.Canvas;

public class TriangleBoss extends Boss{

    private final Bitmaps_triangleBoss bitmaps_triangleBoss;

    public TriangleBoss(Bitmaps_triangleBoss bitmaps_triangleBoss) {
        super(5, 50, 300, Constants.SCREEN_WIDTH/2-100/2, -166, 100, 166);
        this.bitmaps_triangleBoss = bitmaps_triangleBoss;
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmaps_triangleBoss.get(getHp(), isDamage()), this.getX(), this.getY(), null);
    }

}
