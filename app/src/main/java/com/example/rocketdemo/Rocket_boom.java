package com.example.rocketdemo;

import android.graphics.Canvas;

public class Rocket_boom extends GameObject{

    private int life = 39;
    private final Bitmaps_rocket_boom bitmaps_rocket_boom;

    public Rocket_boom(Bitmaps_rocket_boom bitmaps_rocket_boom, int x, int y){
        super(x, y, 85, 85);
        this.bitmaps_rocket_boom = bitmaps_rocket_boom;
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmaps_rocket_boom.get(life), this.getX(), this.getY(), null);
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
