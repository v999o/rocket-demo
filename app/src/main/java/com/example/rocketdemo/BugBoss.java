package com.example.rocketdemo;

import android.graphics.Canvas;

public class BugBoss extends Boss{

    private final Bitmaps_bugBoss bitmaps_bugBoss;

    private int boss_bullet_delay2 = getBoss_bullet_delay_next()+15;
    private int boss_bullet_delay3 = getBoss_bullet_delay_next()-1;
    private int which_cannon_will_shoot = 3;

    public BugBoss(Bitmaps_bugBoss bitmaps_bugBoss){
        super(15, 50, 300);
        this.bitmaps_bugBoss = bitmaps_bugBoss;
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmaps_bugBoss.get(getHp(), isDamage()), this.getX(), this.getY(), null);
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
