package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class UfoBoss extends Boss{

    private final Bitmap bitmap1;
    private final Bitmap bitmap2;
    private final Bitmap bitmap3;
    private final Bitmap bitmap4;
    private final Bitmap bitmap5;

    private final Bitmap bitmap_damage1;
    private final Bitmap bitmap_damage2;
    private final Bitmap bitmap_damage3;
    private final Bitmap bitmap_damage4;
    private final Bitmap bitmap_damage5;

    public UfoBoss(Context context) {
        super(10, 50, 300);
        this.bitmap1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_1), 400, 110, false);
        this.bitmap2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_2), 400, 110, false);
        this.bitmap3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_3), 400, 110, false);
        this.bitmap4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_4), 400, 110, false);
        this.bitmap5 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_5), 400, 110, false);

        this.bitmap_damage1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_damage1), 400, 110, false);
        this.bitmap_damage2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_damage2), 400, 110, false);
        this.bitmap_damage3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_damage3), 400, 110, false);
        this.bitmap_damage4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_damage4), 400, 110, false);
        this.bitmap_damage5 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo_boss_damage5), 400, 110, false);
    }

    @Override
    public void draw(Canvas canvas){
        if (isDamage() && getDamage_life() > 0) {
            if (getHp() > getDefault_hp() / 5 * 4) {
                canvas.drawBitmap(bitmap_damage1, getX(), getY(), null);
            } else if ((getDefault_hp() / 5 * 4) >= getHp() && getHp() > (getDefault_hp() / 5 * 3)) {
                canvas.drawBitmap(bitmap_damage2, getX(), getY(), null);
            } else if ((getDefault_hp() / 5 * 3) >= getHp() && getHp() > (getDefault_hp() / 5 * 2)) {
                canvas.drawBitmap(bitmap_damage3, getX(), getY(), null);
            } else if ((getDefault_hp() / 5 * 2) >= getHp() && getHp() > (getDefault_hp() / 5)) {
                canvas.drawBitmap(bitmap_damage4, getX(), getY(), null);
            } else {
                canvas.drawBitmap(bitmap_damage5, getX(), getY(), null);
            }
        } else if (!isDamage()){
            if (getHp() > getDefault_hp() / 5 * 4) {
                canvas.drawBitmap(bitmap1, getX(), getY(), null);
            } else if ((getDefault_hp() / 5 * 4) >= getHp() && getHp() > (getDefault_hp() / 5 * 3)) {
                canvas.drawBitmap(bitmap2, getX(), getY(), null);
            } else if ((getDefault_hp() / 5 * 3) >= getHp() && getHp() > (getDefault_hp() / 5 * 2)) {
                canvas.drawBitmap(bitmap3, getX(), getY(), null);
            } else if ((getDefault_hp() / 5 * 2) >= getHp() && getHp() > (getDefault_hp() / 5)) {
                canvas.drawBitmap(bitmap4, getX(), getY(), null);
            } else {
                canvas.drawBitmap(bitmap5, getX(), getY(), null);
            }
        }
    }
}
