package com.example.rocketdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class SurvivalBoss extends Alien{

    private boolean damage = false;
    private int damage_life = 5;
    private final int default_hp;
    private int hp;
    private boolean switcher = true;
    private int boss_bullet_delay;
    private final int boss_bullet_delay_next;
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

    public SurvivalBoss(Context context, int default_hp, int boss_bullet_delay_next) {
        super(10, 20);
        this.bitmap1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_survival_rot1), 149, 126, false);
        this.bitmap2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_survival_rot2), 149, 126, false);
        this.bitmap3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_survival_rot3), 149, 126, false);
        this.bitmap4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_survival_rot4), 149, 126, false);
        this.bitmap5 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_survival_rot5), 149, 126, false);

        this.bitmap_damage1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_survival_rot1_damaged), 149, 126, false);
        this.bitmap_damage2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_survival_rot2_damaged), 149, 126, false);
        this.bitmap_damage3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_survival_rot3_damaged), 149, 126, false);
        this.bitmap_damage4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_survival_rot4_damaged), 149, 126, false);
        this.bitmap_damage5 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_survival_rot5_damaged), 149, 126, false);

        this.default_hp = default_hp;
        this.hp = default_hp;
        this.boss_bullet_delay_next = boss_bullet_delay_next;
        this.boss_bullet_delay = boss_bullet_delay_next;
    }

    @Override
    public void draw(Canvas canvas){
        if (damage && damage_life > 0) {
            if (hp > default_hp / 5 * 4) {
                canvas.drawBitmap(bitmap_damage1, getX(), getY(), null);
            } else if ((default_hp / 5 * 4) >= hp && hp > (default_hp / 5 * 3)) {
                canvas.drawBitmap(bitmap_damage2, getX(), getY(), null);
            } else if ((default_hp / 5 * 3) >= hp && hp > (default_hp / 5 * 2)) {
                canvas.drawBitmap(bitmap_damage3, getX(), getY(), null);
            } else if ((default_hp / 5 * 2) >= hp && hp > (default_hp / 5)) {
                canvas.drawBitmap(bitmap_damage4, getX(), getY(), null);
            } else {
                canvas.drawBitmap(bitmap_damage5, getX(), getY(), null);
            }
        } else if (!damage){
            if (hp > default_hp / 5 * 4) {
                canvas.drawBitmap(bitmap1, getX(), getY(), null);
            } else if ((default_hp / 5 * 4) >= hp && hp > (default_hp / 5 * 3)) {
                canvas.drawBitmap(bitmap2, getX(), getY(), null);
            } else if ((default_hp / 5 * 3) >= hp && hp > (default_hp / 5 * 2)) {
                canvas.drawBitmap(bitmap3, getX(), getY(), null);
            } else if ((default_hp / 5 * 2) >= hp && hp > (default_hp / 5)) {
                canvas.drawBitmap(bitmap4, getX(), getY(), null);
            } else {
                canvas.drawBitmap(bitmap5, getX(), getY(), null);
            }
        }
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getBoss_bullet_delay() {
        return boss_bullet_delay;
    }

    public void setBoss_bullet_delay(int boss_bullet_delay) {
        this.boss_bullet_delay = boss_bullet_delay;
    }

    public void setDamage(boolean damage) {
        this.damage = damage;
    }

    public boolean isSwitcher() {
        return switcher;
    }

    public void setSwitcher(boolean switcher) {
        this.switcher = switcher;
    }

    public int getBoss_bullet_delay_next() {
        return boss_bullet_delay_next;
    }

    public void update(){
        if (getY() < 300){
            move(0, 2);
        } else {
            if (getX() <= 0){
                setSwitcher(false);
            }
            if (getX() >= Constants.SCREEN_WIDTH - getWidth()){
                setSwitcher(true);
            }
            if (getX() > 0 && isSwitcher()){
                move(-5,0);
            }
            if (getX() < (Constants.SCREEN_WIDTH - getWidth()) && !isSwitcher()){
                move(5,0);
            }
        }
        if (damage){
            if (damage_life > 0){
                damage_life -= 1;
            } else {
                damage_life = 5;
                damage = false;
            }
        }
    }
}
