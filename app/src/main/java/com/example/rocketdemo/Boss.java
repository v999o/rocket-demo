package com.example.rocketdemo;

public class Boss extends GameObject {

    private boolean damage = false;
    private int damage_life = 5;
    private int hp;
    private boolean switcher = true;
    private int boss_bullet_delay;
    private final int boss_bullet_delay_next;
    private final int stop_height;

    public Boss(int hp, int boss_bullet_delay_next, int stop_height, int x, int y, int w, int h){
        super(x, y, w, h);
        this.stop_height = stop_height;
        this.hp = hp;
        this.boss_bullet_delay_next = boss_bullet_delay_next;
        this.boss_bullet_delay = boss_bullet_delay_next;
    }

    public void update(){
        if (getY() < stop_height){
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

    public boolean isDamage() {
        return damage;
    }

    public void setDamage(boolean damage) {
        this.damage = damage;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isSwitcher() {
        return switcher;
    }

    public void setSwitcher(boolean switcher) {
        this.switcher = switcher;
    }

    public int getBoss_bullet_delay() {
        return boss_bullet_delay;
    }

    public void setBoss_bullet_delay(int boss_bullet_delay) {
        this.boss_bullet_delay = boss_bullet_delay;
    }

    public int getBoss_bullet_delay_next() {
        return boss_bullet_delay_next;
    }
}