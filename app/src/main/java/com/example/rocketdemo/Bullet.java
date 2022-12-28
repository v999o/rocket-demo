package com.example.rocketdemo;

public class Bullet extends GameObject{
    private final int speed;

    public Bullet(int speed){
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
