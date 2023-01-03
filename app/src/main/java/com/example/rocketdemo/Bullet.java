package com.example.rocketdemo;

public class Bullet extends GameObject{
    private final int speed;

    public Bullet(int speed, int x, int y, int w, int h){
        super(x, y, w, h);
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
