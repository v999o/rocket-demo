package com.example.rocketdemo;

import android.graphics.Canvas;

public class Boom extends GameObject{

    private int life = 25;
    private final Bitmaps_boom bitmaps_boom;

    public Boom(Bitmaps_boom bitmaps_boom) {
        this.bitmaps_boom = bitmaps_boom;
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmaps_boom.get(life), this.getX(), this.getY(), null);
    }

    @Override
    public void update(){
        if (life > 0){
            life -= 1;
        }
    }

    public int getLife() {
        return life;
    }
}