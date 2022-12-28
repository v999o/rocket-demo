package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class BugBoss extends Boss{
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

    private int boss_bullet_delay2 = getBoss_bullet_delay_next()+15;
    private int boss_bullet_delay3 = getBoss_bullet_delay_next()-1;
    private int which_cannon_will_shoot = 3;

    public BugBoss(Context context){
        super(15, 50, 300);
        this.bitmap1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss1), 300, 500, false);
        this.bitmap2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_2), 300, 500, false);
        this.bitmap3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_3), 300, 500, false);
        this.bitmap4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_4), 300, 500, false);
        this.bitmap5 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_5), 300, 500, false);

        this.bitmap_damage1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_damage1), 300, 500, false);
        this.bitmap_damage2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_damage2), 300, 500, false);
        this.bitmap_damage3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_damage3), 300, 500, false);
        this.bitmap_damage4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_damage4), 300, 500, false);
        this.bitmap_damage5 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bug_boss_damage5), 300, 500, false);
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

    @Override
    public int getBoss_bullet_delay(){
        if (Math.min(Math.min(boss_bullet_delay2, boss_bullet_delay3), super.getBoss_bullet_delay()) == super.getBoss_bullet_delay()){
            which_cannon_will_shoot = 1;
        } else if (Math.min(Math.min(boss_bullet_delay2, boss_bullet_delay3), super.getBoss_bullet_delay()) == boss_bullet_delay2){
            which_cannon_will_shoot = 2;
        } else {
            which_cannon_will_shoot = 3;
        }
        return Math.min(Math.min(boss_bullet_delay2, boss_bullet_delay3), super.getBoss_bullet_delay());
    }

    @Override
    public void setBoss_bullet_delay(int boss_bullet_delay) {
        switch (which_cannon_will_shoot){
            case 1:
                super.setBoss_bullet_delay(boss_bullet_delay);
                this.boss_bullet_delay2 -= 1;
                this.boss_bullet_delay3 -= 1;
                break;
            case 2:
                super.setBoss_bullet_delay(super.getBoss_bullet_delay()-1);
                this.boss_bullet_delay2 = boss_bullet_delay;
                this.boss_bullet_delay3 -= 1;
                break;
            case 3:
                super.setBoss_bullet_delay(super.getBoss_bullet_delay()-1);
                this.boss_bullet_delay2 -= 1;
                this.boss_bullet_delay3 = boss_bullet_delay;
                break;
        }
    }

    public int getWhich_cannon_will_shoot() {
        return which_cannon_will_shoot;
    }
}
