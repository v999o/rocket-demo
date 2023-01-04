package com.example.rocketdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;


public class GameView extends View {

    private final Bitmaps_boom bitmaps_boom = new Bitmaps_boom(getContext());
    private Rocket rocket;
    private SurvivalBoss survivalBoss;
    private Bullet_SurvivalBoss bullet_survivalBoss;
    private Boom_survivalBoss boom_survivalBoss;
    private Rocket_boom rocket_boom;
    private boolean isSurvival_boss_destroyed = false;
    private boolean isRocket_boom_created = false;
    private int score, bestscore = 0;
    private int score_for_boss = 50;
    private boolean play, game_over;
    private int default_boss_hp = 5;
    private int survival_boss_bullet_delay_next = 50;
    private boolean survival_boss_spawned = false;
    private boolean boss_survival = false;
    private final Context context;
    private final ArrayList<GameObject> objects = new ArrayList<>();
    private final ArrayList<Alien> aliens = new ArrayList<>();
    private final ArrayList<Bullet_rocket> bullets = new ArrayList<>();
    private final ArrayList<Bullet_SurvivalBoss> bullets_survivalBoss = new ArrayList<>();
    private final ArrayList<Boom> booms = new ArrayList<>();
    private int rocket_bullet_delay = 1;
    private int aliens_count = 2;
    private int aliens_to_pass = aliens_count*3;
    private boolean touchRight = false;
    private boolean touchLeft = false;
    private final android.os.Handler handler;
    private final Runnable r;
    private static final String GAME_SETTINGS = "game-settings";
    private static final String BEST_SCORE = "best-score";

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        SharedPreferences sp = context.getSharedPreferences(GAME_SETTINGS, Context.MODE_PRIVATE);
        if(sp != null){
            bestscore = sp.getInt(BEST_SCORE, 0);
        }
        play = false;
        game_over = false;
        create_rocket(context);
        create_Aliens(context);

        handler = new Handler();
        r = () -> {
            if (game_over){
                show_game_over();
                handle_rocket_boom(context);
            } else {
                if (play) {
                    handle_rocket();
                    handle_aliens(context);
                    handle_rocket_bullet(context);
                    if (boss_survival){
                        handle_boss();
                        handle_boss_bullet();
                        if (isSurvival_boss_destroyed){
                            handle_boom_survivalBoss();
                        }
                    }
                    handle_booms();
                }
            }
            invalidate();
        };
    }

    public void create_rocket(Context context){
        rocket = new Rocket(context, Constants.SCREEN_WIDTH/2-this.getWidth()/2, Constants.SCREEN_HEIGHT-100);
        objects.add(rocket);
    }

    public void create_rocket_boom(Context context){
        rocket_boom = new Rocket_boom(context, rocket.getX()-27, rocket.getY());
        objects.add(rocket_boom);
        objects.remove(rocket);
        isRocket_boom_created = true;
    }

    public void create_Aliens(Context context){
        for (int i = 0; i < this.aliens_count; i++){
            spawn_random_alien(context);
        }
    }

    public void spawn_random_alien(Context context){
        Random random = new Random();
        switch (random.nextInt(4)){
            case 0:
                create_alienTriangle(context);
                break;
            case 1:
                create_alienUfo(context);
                break;
            case 2:
                create_alienMeteor(context);
                break;
            case 3:
                create_alienBug(context);
        }
    }

    public void create_alienTriangle(Context context){
        Random random = new Random();
        AlienTriangle alienTriangle = new AlienTriangle(context, random.nextInt(Constants.SCREEN_WIDTH-51), 5);
        objects.add(alienTriangle);
        aliens.add(alienTriangle);
    }

    public void create_alienUfo(Context context){
        Random random = new Random();
        AlienUfo alienUfo = new AlienUfo(context, random.nextInt(Constants.SCREEN_WIDTH-80), 5);
        objects.add(alienUfo);
        aliens.add(alienUfo);
    }

    public void create_alienMeteor(Context context){
        Random random = new Random();
        AlienMeteor alienMeteor = new AlienMeteor(context, random.nextInt(Constants.SCREEN_WIDTH-52), 5);
        objects.add(alienMeteor);
        aliens.add(alienMeteor);
    }

    public void create_alienBug(Context context){
        Random random = new Random();
        AlienBug alienBug = new AlienBug(context, random.nextInt(Constants.SCREEN_WIDTH-39), 5);
        objects.add(alienBug);
        aliens.add(alienBug);
    }

    public void create_rocket_bullet(Context context){
        Bullet_rocket rocketBullet = new Bullet_rocket(context, rocket.getX()+rocket.getWidth()/2, rocket.getY());
        objects.add(rocketBullet);
        bullets.add(rocketBullet);
    }

    public void create_boom(int x, int y){
        Boom boom = new Boom(bitmaps_boom, x, y);
        objects.add(boom);
        booms.add(boom);
    }

    public void create_boom_survivalBoss(Context context, int x, int y){
        boom_survivalBoss = new Boom_survivalBoss(context, x, y);
        objects.add(boom_survivalBoss);
    }

    public void createSurvivalBoss(Context context){
        survivalBoss = new SurvivalBoss(context, default_boss_hp, survival_boss_bullet_delay_next);
        objects.add(survivalBoss);
    }

    public void create_survivalBoss_bullet(Context context){
        bullet_survivalBoss = new Bullet_SurvivalBoss(context, survivalBoss.getX()+survivalBoss.getWidth()/2, survivalBoss.getY()+survivalBoss.getHeight());
        objects.add(bullet_survivalBoss);
        bullets_survivalBoss.add(bullet_survivalBoss);
    }

    public void handle_aliens(Context context){
        for (int i = aliens.size()-1; i >= 0; i--){ //проходим от конца массива к началу чтобы безопасно удалять
            if (aliens.get(i).getY() > Constants.SCREEN_HEIGHT+150+aliens.get(i).getHeight()){ //+100 чтобы уходили за toolbar и еще их высота
                objects.remove(aliens.get(i));
                aliens.remove(aliens.get(i));
            } else {
                aliens.get(i).move(0, aliens.get(i).getSpeed());
                if (aliens.get(i).getRect().intersect(rocket.getRect())){
                    game_over = true;
                }
            }
        }

        if (this.aliens.size() < this.aliens_count && !survival_boss_spawned){
            spawn_random_alien(context);
            aliens_to_pass -= 1;
            if (aliens_to_pass == 0){
                aliens_count += 1;
                aliens_to_pass = aliens_count*3;
            }
        }
    }

    public void handle_rocket(){
        if (touchRight && (rocket.getX() < (Constants.SCREEN_WIDTH - rocket.getWidth()))) {
            rocket.move(10, 0);
        } else if (touchLeft && (rocket.getX() > 0)) {
            rocket.move(-10, 0);
        }
    }

    public void handle_rocket_bullet(Context context){
        if (rocket_bullet_delay == 0 && (touchLeft || touchRight)){
            create_rocket_bullet(context);
            rocket_bullet_delay = 20;
        } else if (touchLeft || touchRight){
            rocket_bullet_delay -= 1;
        }
        for (int i = bullets.size()-1; i >= 0; i--){ //проходим от конца массива к началу чтобы безопасно удалять
            if (bullets.get(i).getY() < 0){
                objects.remove(bullets.get(i));
                bullets.remove(bullets.get(i));
            } else {
                bullets.get(i).move(0, bullets.get(i).getSpeed());
                if (survival_boss_spawned) {
                    if (bullets.get(i).getRect().intersect(survivalBoss.getRect())){
                        survivalBoss.setHp(survivalBoss.getHp()-1);
                        survivalBoss.setDamage(true);
                        objects.remove(bullets.get(i));
                        bullets.remove(bullets.get(i));
                        break;
                    }
                } else {
                    for (int j = aliens.size() - 1; j >= 0; j--) {  //проходим по пришельцам чтобы удалить
                        if (bullets.get(i).getRect().intersect(aliens.get(j).getRect())) {
                            create_boom(aliens.get(j).getX(), aliens.get(j).getY());
                            score += aliens.get(j).getSelf_score();
                            objects.remove(bullets.get(i));
                            objects.remove(aliens.get(j));
                            bullets.remove(bullets.get(i));
                            aliens.remove(aliens.get(j));
                            //MainActivity.txt_score.setText("" + score);
                            MainActivity.txt_score.setText(getResources().getString(R.string.text_score, score));
                            break;
                        }
                    }
                }
            }
        }
    }

    public void handle_boss(){
        if (survival_boss_spawned){
            if (survivalBoss.getHp() > 0){
                survivalBoss.update();
            } else {
                survival_boss_spawned = false;
                isSurvival_boss_destroyed = true;
                score += 20;
                MainActivity.txt_score.setText(getResources().getString(R.string.text_score, score));
                score_for_boss += 50;
                default_boss_hp += 1;
                survival_boss_bullet_delay_next -= 1;
                create_boom_survivalBoss(context, survivalBoss.getX()-51, survivalBoss.getY());
                objects.remove(survivalBoss);
            }
        } else if (score >= score_for_boss) {
            createSurvivalBoss(context);
            survival_boss_spawned = true;
            score_for_boss += 50;
            for (int i = aliens.size()-1; i >= 0; i--){
                create_boom(aliens.get(i).getX(), aliens.get(i).getY());
                objects.remove(aliens.get(i));
                aliens.remove(aliens.get(i));
            }
        }
    }

    public void handle_boss_bullet(){
        if (survival_boss_spawned) {
            if (survivalBoss.getBoss_bullet_delay() == 0) {
                create_survivalBoss_bullet(context);      //если пора стрелять
                survivalBoss.setBoss_bullet_delay(survivalBoss.getBoss_bullet_delay_next());
            } else {
                survivalBoss.setBoss_bullet_delay(survivalBoss.getBoss_bullet_delay() - 1);   //если не пора стрелять
            }
            for (int i = bullets_survivalBoss.size() - 1; i >= 0; i--) {
                if (bullets_survivalBoss.get(i).getY() > Constants.SCREEN_HEIGHT + 200) {
                    objects.remove(bullets_survivalBoss.get(i));   //если пуля ушла за край экрана
                    bullets_survivalBoss.remove(bullets_survivalBoss.get(i));
                } else {
                    bullets_survivalBoss.get(i).move(0, bullet_survivalBoss.getSpeed());
                    if (bullets_survivalBoss.get(i).getRect().intersect(rocket.getRect())) {
                        game_over = true;
                    }
                }
            }
        } else if (bullets_survivalBoss.size() > 0){
            for (int i = bullets_survivalBoss.size()-1; i >= 0; i--){
                objects.remove(bullets_survivalBoss.get(i));
            }
            bullets_survivalBoss.clear();
        }
    }

    public void handle_booms(){
        if (booms.size() > 0){
            for (int i = booms.size()-1; i >= 0; i--){ //если анимация кончилась удаляем взрыв
                if(booms.get(i).getLife() <= 0){
                    objects.remove(booms.get(i));
                    booms.remove(booms.get(i));
                }
                else {
                    booms.get(i).update();
                }
            }
        }
    }

    public void handle_boom_survivalBoss(){
        if (boom_survivalBoss.getLife() <= 0){
            objects.remove(boom_survivalBoss);
            isSurvival_boss_destroyed = false;
        } else {
            boom_survivalBoss.update();
        }
    }

    public void handle_rocket_boom(Context context) {
        if (!isRocket_boom_created) {
            create_rocket_boom(context);
        } else if (rocket_boom.getLife() <= 0) {
            objects.remove(rocket_boom);
        } else {
            rocket_boom.update();
        }
    }

    public void show_game_over(){
        MainActivity.txt_game_over_score.setText(MainActivity.txt_score.getText());
        if (bestscore < score) {
            bestscore = score;
            SharedPreferences sp = context.getSharedPreferences(GAME_SETTINGS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(BEST_SCORE, bestscore);
            editor.apply();
        }
        MainActivity.btn_pause_s.setVisibility(INVISIBLE);
        MainActivity.txt_best_score.setText(getResources().getString(R.string.bestcore, bestscore));
        MainActivity.txt_score.setVisibility(INVISIBLE);
        MainActivity.rl_game_over.setVisibility(VISIBLE);
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).draw(canvas);
        }
        handler.postDelayed(r, 10);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if (event.getX()>Constants.SCREEN_WIDTH/2.0){
                touchRight = true;
            } else {
                touchLeft = true;
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            performClick();
        }
        return true;
    }

    @Override
    public boolean performClick(){
        super.performClick();

        touchRight = false;
        touchLeft = false;

        return true;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public void setBoss_survival(boolean boss_survival) {
        this.boss_survival = boss_survival;
    }

    public void reset(Context context) {
        MainActivity.txt_score.setText("0");
        score = 0;
        score_for_boss = 50;
        default_boss_hp = 5;
        survival_boss_bullet_delay_next = 50;
        aliens.clear();
        bullets.clear();
        bullets_survivalBoss.clear();
        objects.clear();
        game_over = false;
        boss_survival = false;
        survival_boss_spawned = false;
        isRocket_boom_created = false;
        aliens_count = 2;
        aliens_to_pass = aliens_count*3;
        rocket_bullet_delay = 1;
        create_rocket(context);
        create_Aliens(context);
    }
}
