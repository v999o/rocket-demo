package com.example.rocketdemo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class GameObject {
    private int x, y;
    private int width, height;
    private Rect rect;
    private Bitmap bm;

    public GameObject(){

    }

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rect = new Rect(x, y, x+width, y+height);
    }

    public void update(){

    }

    public void move(int dx, int dy){
        this.rect.left+=dx;
        this.rect.right+=dx;
        this.rect.top+=dy;
        this.rect.bottom+=dy;
        this.x+=dx;
        this.y+=dy;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(this.bm, this.x, this.y, null);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    public Rect getRect() {
        return new Rect(this.x, this.y, this.x+this.width, this.y+this.height);
    }
}
