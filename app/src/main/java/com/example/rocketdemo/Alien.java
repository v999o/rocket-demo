package com.example.rocketdemo;

public class Alien extends GameObject{

    private final int speed;
    private final int self_score;

    public Alien(int speed, int self_score, int x, int y, int w, int h){
        super(x, y, w, h);
        this.speed = speed;
        this.self_score = self_score;
    }

    public int getSpeed() {
        return speed;
    }

    public int getSelf_score() {
        return self_score;
    }
}
