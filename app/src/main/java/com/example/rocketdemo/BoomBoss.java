package com.example.rocketdemo;

public class BoomBoss extends GameObject{
    private int life;

    public BoomBoss(int life){
        this.life = life;
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
