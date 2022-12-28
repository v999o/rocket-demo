package com.example.rocketdemo;

import android.content.Context;
//import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CampaignView extends View {

    public TextView testText;
    private Rocket rocket;
    private TriangleBoss triangleBoss;
    private UfoBoss ufoBoss;
    private BugBoss bugBoss;
    private MeteorBoss meteorBoss;
    private Rocket_boom rocket_boom;
    private Boom_triangleBoss boom_triangleBoss;
    private Boom_ufoBoss boom_ufoBoss;
    private Boom_bugBoss boom_bugBoss;
    private Boom_meteorBoss boom_meteorBoss;
    private final Coordinates coordinates = new Coordinates();
    private boolean start = false;
    private boolean isRocketSpawned = false;
    private boolean isRocket_boom_created = false;
    private boolean isBossSpawned = false;
    private boolean isBossBoomAnimationPlaying = false;
    private boolean isAllMeteorBossesDestroyed = false;
    private boolean isLevelComplete = false;
    private boolean touchRight = false;
    private boolean touchLeft = false;
    private boolean game_over = false;
    private int rocket_bullet_delay = 1;
    private int score = 0;
    private int current_y = 0;
    private int level_number = 0;
    private final ArrayList<GameObject> objects = new ArrayList<>();
    private final ArrayList<Alien> aliens = new ArrayList<>();
    private final ArrayList<Bullet_rocket> bullets = new ArrayList<>();
    private final ArrayList<Bullet> bullets_boss = new ArrayList<>();
    private final ArrayList<Boom> booms = new ArrayList<>();
    private final ArrayList<MeteorBoss> meteorBosses = new ArrayList<>();
    private final ArrayList<Boom_meteorBoss> boom_meteorBosses = new ArrayList<>();

    private final android.os.Handler handler;
    private final Runnable r;

    public CampaignView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        handler = new Handler();
        r = () -> {
            if (game_over){
                show_game_over();
                handle_rocket_boom(context);
            } else if (isLevelComplete){
                show_level_complete();
            } else {
                if (start && !isRocketSpawned) {
                    create_rocket(context);
                    isRocketSpawned = true;
                } else if (start) {
                    handle_rocket();
                    handle_aliens(context);
                    switch (level_number){
                        case 1:
                            handle_rocket_bullet(context, triangleBoss);
                            break;
                        case 2:
                            handle_rocket_bullet(context, ufoBoss);
                            break;
                        case 3:
                            handle_rocket_bullet(context, bugBoss);
                            break;
                        case 4:
                            handle_rocket_bullet(context, meteorBoss);
                            break;
                    }
                    if (aliens.size() == 0){
                        if (level_number != 4) {
                            if (!isBossBoomAnimationPlaying) {
                                switch (level_number) {
                                    case 1:
                                        handle_Boss(context, triangleBoss);
                                        handle_boss_bullet(context, triangleBoss);
                                        break;
                                    case 2:
                                        handle_Boss(context, ufoBoss);
                                        handle_boss_bullet(context, ufoBoss);
                                        break;
                                    case 3:
                                        handle_Boss(context, bugBoss);
                                        handle_boss_bullet(context, bugBoss);
                                        break;
                                }
                            }
                        } else if (!isAllMeteorBossesDestroyed){
                            handle_Boss(context, meteorBoss);
                            handle_boss_bullet(context, meteorBoss);
                        }
                    }
                    if (isBossBoomAnimationPlaying && !isLevelComplete){
                        switch (level_number){
                            case 1:
                                handle_boom_boss(boom_triangleBoss);
                                break;
                            case 2:
                                handle_boom_boss(boom_ufoBoss);
                                break;
                            case 3:
                                handle_boom_boss(boom_bugBoss);
                                break;
                            case 4:
                                handle_boom_boss(boom_meteorBoss);
                                break;
                        }
                    }
                    handle_booms();
                }
            }
            invalidate();
        };
    }


    public void show_game_over(){
        CampaignActivity.txt_game_over_score_c.setText(CampaignActivity.txt_score_campaign.getText());
        /* if (bestscore < score) {
            bestscore = score;
            SharedPreferences sp = context.getSharedPreferences(GAME_SETTINGS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(BEST_SCORE, bestscore);
            editor.apply();
        }*/
        //MainActivity.txt_best_score.setText("best: " + bestscore);
        CampaignActivity.txt_score_campaign.setVisibility(INVISIBLE);
        CampaignActivity.rl_game_over_c.setVisibility(VISIBLE);
        testText.setVisibility(VISIBLE);
    }

    public void show_level_complete(){
        CampaignActivity.txt_game_over_score_c.setText(CampaignActivity.txt_score_campaign.getText());
        CampaignActivity.txt_score_campaign.setVisibility(INVISIBLE);
        CampaignActivity.rl_game_over_c.setVisibility(VISIBLE);
        CampaignActivity.txt_level_complete.setVisibility(VISIBLE);
        CampaignActivity.txt_game_over_c.setVisibility(INVISIBLE);
    }

    public void create_aliens_triangle(Context context, int i){
        AlienTriangle alienTriangle = new AlienTriangle(context);
        alienTriangle.setWidth(51);
        alienTriangle.setHeight(31);
        alienTriangle.setX(coordinates.AlienX[i]*coordinates.getW());
        alienTriangle.setY(coordinates.AlienYTest[i]*coordinates.ht);
        alienTriangle.setRect(alienTriangle.getX(), alienTriangle.getY(), alienTriangle.getWidth() + alienTriangle.getX(), alienTriangle.getHeight() + alienTriangle.getY());
        objects.add(alienTriangle);
        aliens.add(alienTriangle);
    }

    public void create_aliens_ufo(Context context, int i){
        AlienUfo alienUfo = new AlienUfo(context);
        alienUfo.setWidth(80);
        alienUfo.setHeight(80);
        alienUfo.setX(coordinates.AlienX2[i]*coordinates.getW());
        alienUfo.setY(coordinates.AlienYTest[i]*coordinates.hu);
        alienUfo.setRect(alienUfo.getX(), alienUfo.getY(), alienUfo.getWidth() + alienUfo.getX(), alienUfo.getHeight() + alienUfo.getY());
        objects.add(alienUfo);
        aliens.add(alienUfo);
    }

    public void create_aliens_bug(Context context, int i){
        AlienBug alienBug = new AlienBug(context);
        alienBug.setWidth(39);
        alienBug.setHeight(90);
        alienBug.setX(coordinates.AlienX3[i]*coordinates.getW());
        alienBug.setY(coordinates.AlienYTest[i]*coordinates.hb);
        alienBug.setRect(alienBug.getX(), alienBug.getY(), alienBug.getWidth() + alienBug.getX(), alienBug.getHeight() + alienBug.getY());
        objects.add(alienBug);
        aliens.add(alienBug);
    }

    public void create_aliens_meteor(Context context, int i){
        AlienMeteor alienMeteor = new AlienMeteor(context);
        alienMeteor.setWidth(52);
        alienMeteor.setHeight(117);
        alienMeteor.setX(coordinates.AlienX4[i]*coordinates.getW());
        alienMeteor.setY(coordinates.AlienYTest[i]*coordinates.hm);
        alienMeteor.setRect(alienMeteor.getX(), alienMeteor.getY(), alienMeteor.getWidth() + alienMeteor.getX(), alienMeteor.getHeight() + alienMeteor.getY());
        objects.add(alienMeteor);
        aliens.add(alienMeteor);
    }

    public void create_rocket(Context context){
        rocket = new Rocket(context);
        rocket.setWidth(34);
        rocket.setHeight(85);
        rocket.setX(Constants.SCREEN_WIDTH/2-rocket.getWidth()/2);
        rocket.setY(Constants.SCREEN_HEIGHT-100);
        rocket.setRect(rocket.getX(), rocket.getY(), rocket.getWidth() + rocket.getX(), rocket.getHeight() + rocket.getY());
        objects.add(rocket);
    }

    public void create_rocket_bullet(Context context){
        Bullet_rocket rocketBullet = new Bullet_rocket(context);
        rocketBullet.setWidth(10);
        rocketBullet.setHeight(10);
        rocketBullet.setX(rocket.getX()+rocket.getWidth()/2);
        rocketBullet.setY(rocket.getY());
        rocketBullet.setRect(rocketBullet.getX(), rocketBullet.getY(), rocketBullet.getWidth() + rocketBullet.getX(), rocketBullet.getHeight() + rocketBullet.getY());
        objects.add(rocketBullet);
        bullets.add(rocketBullet);
    }

    public void create_boom(Context context, int x, int y){
        Boom boom = new Boom(context);
        boom.setWidth(50);
        boom.setHeight(50);
        boom.setX(x);
        boom.setY(y);
        objects.add(boom);
        booms.add(boom);
    }

    public void create_rocket_boom(Context context){
        rocket_boom = new Rocket_boom(context);
        rocket_boom.setWidth(85);
        rocket_boom.setHeight(85);
        rocket_boom.setX(rocket.getX()-27);
        rocket_boom.setY(rocket.getY());
        rocket_boom.setRect(rocket_boom.getX(), rocket_boom.getY(), rocket_boom.getWidth() + rocket_boom.getX(), rocket_boom.getHeight() + rocket_boom.getY());
        objects.add(rocket_boom);
        objects.remove(rocket);
        isRocket_boom_created = true;
    }

    public void create_triangleBoss(Context context){
        triangleBoss = new TriangleBoss(context);
        triangleBoss.setWidth(100);
        triangleBoss.setHeight(166);
        triangleBoss.setX(Constants.SCREEN_WIDTH/2-triangleBoss.getWidth()/2);
        triangleBoss.setY(-166);
        triangleBoss.setRect(triangleBoss.getX(), triangleBoss.getY(), triangleBoss.getWidth() + triangleBoss.getX(), triangleBoss.getHeight() + triangleBoss.getY());
        objects.add(triangleBoss);
    }

    public void create_ufoBoss(Context context){
        ufoBoss = new UfoBoss(context);
        ufoBoss.setWidth(400);
        ufoBoss.setHeight(110);
        ufoBoss.setX(Constants.SCREEN_WIDTH/2-ufoBoss.getWidth()/2);
        ufoBoss.setY(-110);
        ufoBoss.setRect(ufoBoss.getX(), ufoBoss.getY(), ufoBoss.getWidth() + ufoBoss.getX(), ufoBoss.getHeight() + ufoBoss.getY());
        objects.add(ufoBoss);
    }

    public void create_bugBoss(Context context){
        bugBoss = new BugBoss(context);
        bugBoss.setWidth(300);
        bugBoss.setHeight(500);
        bugBoss.setX(Constants.SCREEN_WIDTH/2-bugBoss.getWidth()/2);
        bugBoss.setY(-500);
        bugBoss.setRect(bugBoss.getX(), bugBoss.getY(), bugBoss.getWidth() + bugBoss.getX(), bugBoss.getHeight() + bugBoss.getY());
        objects.add(bugBoss);
    }

    public void create_meteorBoss(Context context, int x, int stop_height){
        meteorBoss = new MeteorBoss(context, stop_height);
        meteorBoss.setWidth(52);
        meteorBoss.setHeight(117);
        meteorBoss.setX(x);
        meteorBoss.setY(-117);
        meteorBoss.setRect(meteorBoss.getX(), meteorBoss.getY(), meteorBoss.getWidth() + meteorBoss.getX(), meteorBoss.getHeight() + meteorBoss.getY());
        objects.add(meteorBoss);
        meteorBosses.add(meteorBoss);
    }

    public void create_triangleBoss_bullet(Context context){
        Bullet_triangleBoss bullet_triangleBoss = new Bullet_triangleBoss(context);
        bullet_triangleBoss.setWidth(10);
        bullet_triangleBoss.setHeight(10);
        bullet_triangleBoss.setX(triangleBoss.getX()+triangleBoss.getWidth()/2);
        bullet_triangleBoss.setY(triangleBoss.getY()+triangleBoss.getHeight());
        bullet_triangleBoss.setRect(bullet_triangleBoss.getX(), bullet_triangleBoss.getY(), bullet_triangleBoss.getWidth() + bullet_triangleBoss.getX(), bullet_triangleBoss.getHeight() + bullet_triangleBoss.getY());
        objects.add(bullet_triangleBoss);
        bullets_boss.add(bullet_triangleBoss);
    }

    public void create_ufoBoss_bullet(Context context, int x){
        Bullet_UfoBoss bullet_ufoBoss = new Bullet_UfoBoss(context);
        bullet_ufoBoss.setWidth(10);
        bullet_ufoBoss.setHeight(10);
        bullet_ufoBoss.setX(x);
        bullet_ufoBoss.setY(ufoBoss.getY()+ufoBoss.getHeight());
        bullet_ufoBoss.setRect(bullet_ufoBoss.getX(), bullet_ufoBoss.getY(), bullet_ufoBoss.getWidth() + bullet_ufoBoss.getX(), bullet_ufoBoss.getHeight() + bullet_ufoBoss.getY());
        objects.add(bullet_ufoBoss);
        bullets_boss.add(bullet_ufoBoss);
    }

    public void create_bugBoss_bullet(Context context, int x, int y){
        Bullet_BugBoss bullet_bugBoss = new Bullet_BugBoss(context);
        bullet_bugBoss.setWidth(10);
        bullet_bugBoss.setHeight(10);
        bullet_bugBoss.setX(x);
        bullet_bugBoss.setY(y);
        bullet_bugBoss.setRect(bullet_bugBoss.getX(), bullet_bugBoss.getY(), bullet_bugBoss.getWidth() + bullet_bugBoss.getX(), bullet_bugBoss.getHeight() + bullet_bugBoss.getY());
        objects.add(bullet_bugBoss);
        bullets_boss.add(bullet_bugBoss);
    }

    public void create_meteorBoss_bullet(Context context, MeteorBoss meteorBoss){
        Bullet_MeteorBoss bullet_meteorBoss = new Bullet_MeteorBoss(context);
        bullet_meteorBoss.setWidth(10);
        bullet_meteorBoss.setHeight(10);
        bullet_meteorBoss.setX(meteorBoss.getX()+meteorBoss.getWidth()/2-5);
        bullet_meteorBoss.setY(meteorBoss.getY()+meteorBoss.getHeight());
        bullet_meteorBoss.setRect(bullet_meteorBoss.getX(), bullet_meteorBoss.getY(), bullet_meteorBoss.getWidth() + bullet_meteorBoss.getX(), bullet_meteorBoss.getWidth() + bullet_meteorBoss.getY());
        objects.add(bullet_meteorBoss);
        bullets_boss.add(bullet_meteorBoss);
    }

    public void create_boom_triangleBoss(Context context, int x, int y){
        boom_triangleBoss = new Boom_triangleBoss(context);
        boom_triangleBoss.setWidth(200);
        boom_triangleBoss.setHeight(126);
        boom_triangleBoss.setX(x);
        boom_triangleBoss.setY(y);
        objects.add(boom_triangleBoss);
    }

    public void create_boom_ufoBoss(Context context, int x, int y){
        boom_ufoBoss = new Boom_ufoBoss(context);
        boom_ufoBoss.setWidth(700);
        boom_ufoBoss.setHeight(110);
        boom_ufoBoss.setX(x);
        boom_ufoBoss.setY(y);
        objects.add(boom_ufoBoss);
    }

    public void create_boom_bugBoss(Context context, int x, int y){
        boom_bugBoss = new Boom_bugBoss(context);
        boom_bugBoss.setWidth(500);
        boom_bugBoss.setHeight(700);
        boom_bugBoss.setX(x);
        boom_bugBoss.setY(y);
        objects.add(boom_bugBoss);
    }

    public void create_boom_meteorBoss(Context context, int x, int y){
        boom_meteorBoss = new Boom_meteorBoss(context);
        boom_meteorBoss.setWidth(152);
        boom_meteorBoss.setHeight(217);
        boom_meteorBoss.setX(x);
        boom_meteorBoss.setY(y);
        objects.add(boom_meteorBoss);
        boom_meteorBosses.add(boom_meteorBoss);
    }

    public void handle_rocket(){
        if (touchRight && (rocket.getX() < (Constants.SCREEN_WIDTH - rocket.getWidth()))) {
            rocket.move(10, 0);
        } else if (touchLeft && (rocket.getX() > 0)) {
            rocket.move(-10, 0);
        }
    }

    public void handle_rocket_bullet(Context context, Boss boss) {
        if (rocket_bullet_delay == 0 && (touchLeft || touchRight)) {
            create_rocket_bullet(context);
            rocket_bullet_delay = 20;
        } else if (touchLeft || touchRight) {
            rocket_bullet_delay -= 1;
        }
        for (int i = bullets.size() - 1; i >= 0; i--) { //проходим от конца массива к началу чтобы безопасно удалять
            if (bullets.get(i).getY() < 0) {
                objects.remove(bullets.get(i));
                bullets.remove(bullets.get(i));
            } else {
                bullets.get(i).move(0, bullets.get(i).getSpeed());
                if (isBossSpawned){
                    if (level_number == 4){
                        for (int j = 0; j < meteorBosses.size(); j++){
                            if (bullets.get(i).getRect().intersect(meteorBosses.get(j).getRect())){
                                meteorBosses.get(j).setHp(meteorBosses.get(j).getHp()-1);
                                meteorBosses.get(j).setDamage(true);
                                objects.remove(bullets.get(i));
                                bullets.remove(bullets.get(i));
                            }
                        }
                    } else if (bullets.get(i).getRect().intersect(boss.getRect())){
                        boss.setHp(boss.getHp()-1);
                        boss.setDamage(true);
                        objects.remove(bullets.get(i));
                        bullets.remove(bullets.get(i));
                    }
                } else {
                    for (int j = aliens.size() - 1; j >= 0; j--) {  //проходим по пришельцам чтобы удалить
                        if (bullets.get(i).getRect().intersect(aliens.get(j).getRect())) {
                            create_boom(context, aliens.get(j).getX(), aliens.get(j).getY());
                            score += aliens.get(j).getSelf_score();
                            objects.remove(bullets.get(i));
                            objects.remove(aliens.get(j));
                            bullets.remove(bullets.get(i));
                            aliens.remove(aliens.get(j));
                            CampaignActivity.txt_score_campaign.setText(getResources().getString(R.string.text_score, score));
                            break;
                        }
                    }
                }
            }
        }
    }

    public void handle_aliens(Context context){
        switch (level_number){
            case 1:
                for (int i = 0; i <= coordinates.AlienYTest.length-1; i++){
                    if (current_y == coordinates.AlienYTest[i]*coordinates.ht){
                        create_aliens_triangle(context, i);
                    }
                }
                break;
            case 2:
                for (int i = 0; i <= coordinates.AlienYTest.length-1; i++){
                    if (current_y == coordinates.AlienYTest[i]*coordinates.hu){
                        create_aliens_ufo(context, i);
                    }
                }
                break;
            case 3:
                for (int i = 0; i <= coordinates.AlienYTest.length-1; i++){
                    if (current_y == coordinates.AlienYTest[i]*coordinates.hb){
                        create_aliens_bug(context, i);
                    }
                }
                break;
            case 4:
                for (int i = 0; i <= coordinates.AlienYTest.length-1; i++){
                    if (current_y == coordinates.AlienYTest[i]*coordinates.hm){
                        create_aliens_meteor(context, i);
                    }
                }
                break;
        }
        current_y -= 10;
        for (int i = aliens.size() - 1; i >= 0; i--) { //проходим от конца массива к началу чтобы безопасно удалять
            if (aliens.get(i).getY() > Constants.SCREEN_HEIGHT + 150 + aliens.get(i).getHeight()) { //+100 чтобы уходили за toolbar и еще их высота
                objects.remove(aliens.get(i));
                aliens.remove(aliens.get(i));
            } else {
                aliens.get(i).move(0, aliens.get(i).getSpeed());
                if (aliens.get(i).getRect().intersect(rocket.getRect())) {
                    game_over = true;
                }
            }
        }
    }

    public void handle_Boss(Context context, Boss boss){
        if (!isBossSpawned && !isLevelComplete){
            switch (level_number){
                case 1:
                    create_triangleBoss(context);
                    break;
                case 2:
                    create_ufoBoss(context);
                    break;
                case 3:
                    create_bugBoss(context);
                    break;
                case 4:
                    create_meteorBoss(context, 0, 250);
                    create_meteorBoss(context, Constants.SCREEN_WIDTH-52, 400);
                    create_meteorBoss(context, Constants.SCREEN_WIDTH/4, 550);
                    create_meteorBoss(context, (Constants.SCREEN_WIDTH/4)*2, 700);
                    break;
            }
            isBossSpawned = true;
        } else {
            if (level_number == 4){
                for (int i = meteorBosses.size()-1; i >= 0; i--){
                    if (meteorBosses.get(i).getHp() > 0) {
                        meteorBosses.get(i).update();
                    } else if (!isLevelComplete){
                        create_boom_meteorBoss(context, meteorBosses.get(i).getX()-50, meteorBosses.get(i).getY()-50);
                        isBossBoomAnimationPlaying = true;
                        objects.remove(meteorBosses.get(i));
                        meteorBosses.remove(meteorBosses.get(i));
                    }
                }
                if (meteorBosses.size() == 0){
                    isBossSpawned = false;
                    isBossBoomAnimationPlaying = true;
                    isAllMeteorBossesDestroyed = true;
                }
            } else if (boss.getHp() > 0){
                boss.update();
            } else if (!isLevelComplete) {
                switch (level_number){
                    case 1:
                        create_boom_triangleBoss(context, triangleBoss.getX()-50, triangleBoss.getY()-67);
                        break;
                    case 2:
                        create_boom_ufoBoss(context, ufoBoss.getX()-150, ufoBoss.getY());
                        break;
                    case 3:
                        create_boom_bugBoss(context, bugBoss.getX()-100, bugBoss.getY()-100);
                        break;
                }
                isBossSpawned = false;
                isBossBoomAnimationPlaying = true;
                objects.remove(boss);
            }
        }
    }

    public void handle_boss_bullet(Context context, Boss boss){
        if (isBossSpawned) {
            if (boss.getBoss_bullet_delay() == 0) {  //если пора стрелять
                switch (level_number){
                    case 1:
                        create_triangleBoss_bullet(context);
                        break;
                    case 2:
                        create_ufoBoss_bullet(context, ufoBoss.getX()+42);  //еще отнимаем половину ширины пули
                        create_ufoBoss_bullet(context, ufoBoss.getX()+ufoBoss.getWidth()-50);
                        break;
                    case 3:
                        switch (bugBoss.getWhich_cannon_will_shoot()){
                            case 1:
                                create_bugBoss_bullet(context, bugBoss.getX()+35, bugBoss.getY()+bugBoss.getHeight()-40);
                                break;
                            case 2:
                                create_bugBoss_bullet(context, bugBoss.getX()+bugBoss.getWidth()/2, bugBoss.getY()+bugBoss.getHeight()-130);
                                break;
                            case 3:
                                create_bugBoss_bullet(context, bugBoss.getX()+bugBoss.getWidth()-35, bugBoss.getY()+bugBoss.getHeight()-45);
                                break;
                        }
                    case 4:
                        for (int i = 0; i < meteorBosses.size(); i++){
                            create_meteorBoss_bullet(context, meteorBosses.get(i));
                        }
                        break;
                }
                boss.setBoss_bullet_delay(boss.getBoss_bullet_delay_next());
            } else {
                boss.setBoss_bullet_delay(boss.getBoss_bullet_delay() - 1);   //если не пора стрелять
            }
            for (int i = bullets_boss.size() - 1; i >= 0; i--) {
                if (bullets_boss.get(i).getY() > Constants.SCREEN_HEIGHT + 200) {
                    objects.remove(bullets_boss.get(i));   //если пуля ушла за край экрана
                    bullets_boss.remove(bullets_boss.get(i));
                } else {
                    bullets_boss.get(i).move(0, bullets_boss.get(i).getSpeed());
                    if (bullets_boss.get(i).getRect().intersect(rocket.getRect())) {
                        game_over = true;
                    }
                }
            }
        } else if (bullets_boss.size() > 0){
            for (int i = bullets_boss.size()-1; i >= 0; i--){
                objects.remove(bullets_boss.get(i));
            }
            bullets_boss.clear();
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

    public void handle_boom_boss(BoomBoss boomBoss){
        if (level_number != 4) {
            if (boomBoss.getLife() <= 0) {
                objects.remove(boomBoss);
                isBossBoomAnimationPlaying = false;
                isLevelComplete = true;
            } else {
                boomBoss.update();
            }
        } else if (isBossBoomAnimationPlaying){
            for (int i = boom_meteorBosses.size() - 1; i >= 0; i--) {
                if (boom_meteorBosses.get(i).getLife() <= 0) {
                    objects.remove(boom_meteorBosses.get(i));
                    boom_meteorBosses.remove(boom_meteorBosses.get(i));
                    if (boom_meteorBosses.size() == 0) {
                        isBossBoomAnimationPlaying = false;
                    }
                    if (isAllMeteorBossesDestroyed && boom_meteorBosses.size() == 0) {
                        isLevelComplete = true;
                        break;
                    }
                } else {
                    boom_meteorBosses.get(i).update();
                }
            }
        }
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).draw(canvas);
        }
        handler.postDelayed(r, 10);
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public int getLevel_number() {
        return level_number;
    }

    public void setLevel_number(int level_number) {
        this.level_number = level_number;
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
            touchRight = false;
            touchLeft = false;
        }
        return true;
    }

    public void reset(){
        CampaignActivity.txt_score_campaign.setText("0");
        CampaignActivity.txt_score_campaign.setVisibility(VISIBLE);
        score = 0;
        aliens.clear();
        bullets.clear();
        objects.clear();
        bullets_boss.clear();
        booms.clear();
        meteorBosses.clear();
        boom_meteorBosses.clear();
        game_over = false;
        isLevelComplete = false;
        isRocketSpawned = false;
        isRocket_boom_created = false;
        isBossSpawned = false;
        isBossBoomAnimationPlaying = false;
        isAllMeteorBossesDestroyed = false;
        rocket_bullet_delay = 1;
        current_y = 0;
    }
}
