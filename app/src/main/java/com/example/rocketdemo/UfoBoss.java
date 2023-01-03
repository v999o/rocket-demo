package com.example.rocketdemo;

import android.graphics.Canvas;

public class UfoBoss extends Boss{

    private final Bitmaps_ufoBoss bitmaps_ufoBoss;

    public UfoBoss(Bitmaps_ufoBoss bitmaps_ufoBoss) {
        super(10, 50, 300, Constants.SCREEN_WIDTH/2-400/2, -110, 400, 110);
        this.bitmaps_ufoBoss = bitmaps_ufoBoss;

    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmaps_ufoBoss.get(getHp(), isDamage()), this.getX(), this.getY(), null);
    }
}
