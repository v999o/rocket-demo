package com.example.rocketdemo;

import android.graphics.Canvas;

public class Boom extends GameObject{

    private int life = 24;
    private final Bitmaps_boom bitmaps_boom;

    public Boom(Bitmaps_boom bitmaps_boom, int x, int y) {
        super(x, y, 50, 50);
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
