package com.example.rocketdemo;

import android.graphics.Canvas;

public class Boom_meteorBoss extends BoomBoss{

    private final Bitmaps_boom_meteorBoss bitmaps_boom_meteorBoss;

    public Boom_meteorBoss(Bitmaps_boom_meteorBoss bitmaps_boom_meteorBoss, int x, int y){
        super(39, x, y, 152, 217);
        this.bitmaps_boom_meteorBoss = bitmaps_boom_meteorBoss;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmaps_boom_meteorBoss.get(getLife()), this.getX(), this.getY(), null);
    }
}
