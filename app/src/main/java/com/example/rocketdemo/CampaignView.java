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

public class CampaignView extends View {

    private final Bitmaps_boom bitmaps_boom = new Bitmaps_boom(getContext());
    private final Bitmaps_rocket_boom bitmaps_rocket_boom = new Bitmaps_rocket_boom(getContext());
    private final Bitmaps_triangleBoss bitmaps_triangleBoss = new Bitmaps_triangleBoss(getContext());
    private final Bitmaps_ufoBoss bitmaps_ufoBoss = new Bitmaps_ufoBoss(getContext());
    private final Bitmaps_bugBoss bitmaps_bugBoss = new Bitmaps_bugBoss(getContext());
    private final Bitmaps_meteorBoss bitmaps_meteorBoss = new Bitmaps_meteorBoss(getContext());
    private final Bitmaps_boom_triangleBoss bitmaps_boom_triangleBoss = new Bitmaps_boom_triangleBoss(getContext());
    private final Bitmaps_boom_ufoBoss bitmaps_boom_ufoBoss = new Bitmaps_boom_ufoBoss(getContext());
    private final Bitmaps_boom_bugBoss bitmaps_boom_bugBoss = new Bitmaps_boom_bugBoss(getContext());
    private final Bitmaps_boom_meteorBoss bitmaps_boom_meteorBoss = new Bitmaps_boom_meteorBoss(getContext());

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
    private boolean game_over_showed = false;
    private int rocket_bullet_delay = 1;
    private int score = 0;
    private int bestscore1, bestscore2, bestscore3, bestscore4;
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

    private static final String GAME_SETTINGS = "game-settings";
    private static final String BEST_SCORE1 = "best-score1";
    private static final String BEST_SCORE2 = "best-score2";
    private static final String BEST_SCORE3 = "best-score3";
    private static final String BEST_SCORE4 = "best-score4";

    public CampaignView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);

        SharedPreferences sp = context.getSharedPreferences(GAME_SETTINGS, Context.MODE_PRIVATE);
        if(sp != null){
            bestscore1 = sp.getInt(BEST_SCORE1, 0);
            bestscore2 = sp.getInt(BEST_SCORE2, 0);
            bestscore3 = sp.getInt(BEST_SCORE3, 0);
            bestscore4 = sp.getInt(BEST_SCORE4, 0);
        }
        handler = new Handler();
        r = () -> {
            if (game_over){
                if (!game_over_showed){
                    show_game_over();
                }
                handle_rocket_boom();
            } else if (isLevelComplete){
                show_level_complete();
            } else {
                if (start && !isRocketSpawned) {
                    create_rocket(context);
                    isRocketSpawned = true;
                } else if (start) {
                    onStart(context);
                }
            }
            invalidate();
        };
    }

    public void onStart(Context context){
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
                            handle_Boss(triangleBoss);
                            handle_boss_bullet(context, triangleBoss);
                            break;
                        case 2:
                            handle_Boss(ufoBoss);
                            handle_boss_bullet(context, ufoBoss);
                            break;
                        case 3:
                            handle_Boss(bugBoss);
                            handle_boss_bullet(context, bugBoss);
                            break;
                    }
                }
            } else if (!isAllMeteorBossesDestroyed){
                handle_Boss(meteorBoss);
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

    public void save_bestscore(int bestscore, String BEST_SCORE){
        if (bestscore < score){
            bestscore = score;
            SharedPreferences sp = getContext().getSharedPreferences(GAME_SETTINGS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(BEST_SCORE, bestscore);
            editor.apply();
        }
        CampaignActivity.txt_best_score_c.setText(getResources().getString(R.string.bestcore, bestscore));
    }

    public void show_game_over(){
        CampaignActivity.txt_game_over_score_c.setText(CampaignActivity.txt_score_campaign.getText());
        switch (level_number){
            case 1:
                save_bestscore(bestscore1, BEST_SCORE1);
                break;
            case 2:
                save_bestscore(bestscore2, BEST_SCORE2);
                break;
            case 3:
                save_bestscore(bestscore3, BEST_SCORE3);
                break;
            case 4:
                save_bestscore(bestscore4, BEST_SCORE4);
                break;
        }
        ((CampaignActivity)getContext()).findViewById(R.id.txt_score_campaign).setVisibility(INVISIBLE);
        ((CampaignActivity)getContext()).findViewById(R.id.rl_game_over_c).setVisibility(VISIBLE);
        ((CampaignActivity)getContext()).findViewById(R.id.rl_buttons).setVisibility(VISIBLE);
        ((CampaignActivity)getContext()).findViewById(R.id.txt_level_complete).setVisibility(INVISIBLE);
        ((CampaignActivity)getContext()).findViewById(R.id.txt_game_over_c).setVisibility(VISIBLE);
        ((CampaignActivity)getContext()).findViewById(R.id.btn_pause).setVisibility(INVISIBLE);
        game_over_showed = true;
    }

    public void show_level_complete(){
        switch (level_number){
            case 1:
                save_bestscore(bestscore1, BEST_SCORE1);
                break;
            case 2:
                save_bestscore(bestscore2, BEST_SCORE2);
                break;
            case 3:
                save_bestscore(bestscore3, BEST_SCORE3);
                break;
            case 4:
                save_bestscore(bestscore4, BEST_SCORE4);
                break;
        }
        CampaignActivity.txt_game_over_score_c.setText(CampaignActivity.txt_score_campaign.getText());
        ((CampaignActivity)getContext()).findViewById(R.id.txt_score_campaign).setVisibility(INVISIBLE);
        ((CampaignActivity)getContext()).findViewById(R.id.rl_game_over_c).setVisibility(VISIBLE);
        ((CampaignActivity)getContext()).findViewById(R.id.rl_buttons).setVisibility(VISIBLE);
        ((CampaignActivity)getContext()).findViewById(R.id.txt_level_complete).setVisibility(VISIBLE);
        ((CampaignActivity)getContext()).findViewById(R.id.txt_game_over_c).setVisibility(INVISIBLE);
        if (level_number < 4) {
            ((CampaignActivity)getContext()).findViewById(R.id.btn_next_level).setVisibility(VISIBLE);
        }
        ((CampaignActivity)getContext()).findViewById(R.id.btn_pause).setVisibility(INVISIBLE);
    }

    public void create_aliens_triangle(Context context, int i){
        AlienTriangle alienTriangle = new AlienTriangle(context, coordinates.AlienX[i]*coordinates.getW(), coordinates.AlienYTest[i]*coordinates.ht);
        objects.add(alienTriangle);
        aliens.add(alienTriangle);
    }

    public void create_aliens_ufo(Context context, int i){
        AlienUfo alienUfo = new AlienUfo(context, coordinates.AlienX2[i]*coordinates.getW(), coordinates.AlienYTest[i]*coordinates.hu);
        objects.add(alienUfo);
        aliens.add(alienUfo);
    }

    public void create_aliens_bug(Context context, int i){
        AlienBug alienBug = new AlienBug(context, coordinates.AlienX3[i]*coordinates.getW(), coordinates.AlienYTest[i]*coordinates.hb);
        objects.add(alienBug);
        aliens.add(alienBug);
    }

    public void create_aliens_meteor(Context context, int i){
        AlienMeteor alienMeteor = new AlienMeteor(context, coordinates.AlienX4[i]*coordinates.getW(), coordinates.AlienYTest[i]*coordinates.hm);
        objects.add(alienMeteor);
        aliens.add(alienMeteor);
    }

    public void create_rocket(Context context){
        rocket = new Rocket(context, Constants.SCREEN_WIDTH/2 - 34/2, Constants.SCREEN_HEIGHT-100);
        objects.add(rocket);
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

    public void create_rocket_boom(){
        rocket_boom = new Rocket_boom(bitmaps_rocket_boom, rocket.getX()-27, rocket.getY());
        objects.add(rocket_boom);
        objects.remove(rocket);
        isRocket_boom_created = true;
    }

    public void create_triangleBoss(){
        triangleBoss = new TriangleBoss(bitmaps_triangleBoss);
        objects.add(triangleBoss);
    }

    public void create_ufoBoss(){
        ufoBoss = new UfoBoss(bitmaps_ufoBoss);
        objects.add(ufoBoss);
    }

    public void create_bugBoss(){
        bugBoss = new BugBoss(bitmaps_bugBoss);
        objects.add(bugBoss);
    }

    public void create_meteorBoss(int x, int stop_height){
        meteorBoss = new MeteorBoss(stop_height, bitmaps_meteorBoss, x);
        objects.add(meteorBoss);
        meteorBosses.add(meteorBoss);
    }

    public void create_triangleBoss_bullet(Context context){
        Bullet_triangleBoss bullet_triangleBoss = new Bullet_triangleBoss(context, triangleBoss.getX()+triangleBoss.getWidth()/2, triangleBoss.getY()+triangleBoss.getHeight());
        objects.add(bullet_triangleBoss);
        bullets_boss.add(bullet_triangleBoss);
    }

    public void create_ufoBoss_bullet(Context context, int x){
        Bullet_UfoBoss bullet_ufoBoss = new Bullet_UfoBoss(context, x, ufoBoss.getY()+ufoBoss.getHeight());
        objects.add(bullet_ufoBoss);
        bullets_boss.add(bullet_ufoBoss);
    }

    public void create_bugBoss_bullet(Context context, int x, int y){
        Bullet_BugBoss bullet_bugBoss = new Bullet_BugBoss(context, x, y);
        objects.add(bullet_bugBoss);
        bullets_boss.add(bullet_bugBoss);
    }

    public void create_meteorBoss_bullet(Context context, MeteorBoss meteorBoss){
        Bullet_MeteorBoss bullet_meteorBoss = new Bullet_MeteorBoss(context, meteorBoss.getX()+meteorBoss.getWidth()/2-5, meteorBoss.getY()+meteorBoss.getHeight());
        objects.add(bullet_meteorBoss);
        bullets_boss.add(bullet_meteorBoss);
    }

    public void create_boom_triangleBoss(int x, int y){
        boom_triangleBoss = new Boom_triangleBoss(bitmaps_boom_triangleBoss, x, y);
        objects.add(boom_triangleBoss);
    }

    public void create_boom_ufoBoss(int x, int y){
        boom_ufoBoss = new Boom_ufoBoss(bitmaps_boom_ufoBoss, x, y);
        objects.add(boom_ufoBoss);
    }

    public void create_boom_bugBoss(int x, int y){
        boom_bugBoss = new Boom_bugBoss(bitmaps_boom_bugBoss, x, y);
        objects.add(boom_bugBoss);
    }

    public void create_boom_meteorBoss(int x, int y){
        boom_meteorBoss = new Boom_meteorBoss(bitmaps_boom_meteorBoss, x, y);
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
        if (rocket_bullet_delay == 0 && (touchLeft || touchRight) && rocket.getX() > 0 && rocket.getX() + rocket.getWidth() < Constants.SCREEN_WIDTH) {
            create_rocket_bullet(context);
            rocket_bullet_delay = 20;
        } else if ((touchLeft || touchRight) && rocket.getX() > 0 && rocket.getX() + rocket.getWidth() < Constants.SCREEN_WIDTH) {
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
                            create_boom(aliens.get(j).getX(), aliens.get(j).getY());
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

    public void handle_Boss(Boss boss){
        if (!isBossSpawned && !isLevelComplete){
            switch (level_number){
                case 1:
                    create_triangleBoss();
                    break;
                case 2:
                    create_ufoBoss();
                    break;
                case 3:
                    create_bugBoss();
                    break;
                case 4:
                    create_meteorBoss(0, 250);
                    create_meteorBoss(Constants.SCREEN_WIDTH-52, 400);
                    create_meteorBoss(Constants.SCREEN_WIDTH/4, 550);
                    create_meteorBoss((Constants.SCREEN_WIDTH/4)*2, 700);
                    break;
            }
            isBossSpawned = true;
        } else {
            if (level_number == 4){
                for (int i = meteorBosses.size()-1; i >= 0; i--){
                    if (meteorBosses.get(i).getHp() > 0) {
                        meteorBosses.get(i).update();
                    } else if (!isLevelComplete){
                        create_boom_meteorBoss(meteorBosses.get(i).getX()-50, meteorBosses.get(i).getY()-50);
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
                        create_boom_triangleBoss(triangleBoss.getX()-50, triangleBoss.getY()-67);
                        break;
                    case 2:
                        create_boom_ufoBoss(ufoBoss.getX()-150, ufoBoss.getY());
                        break;
                    case 3:
                        create_boom_bugBoss(bugBoss.getX()-100, bugBoss.getY()-100);
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

    public void handle_rocket_boom() {
        if (!isRocket_boom_created) {
            create_rocket_boom();
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

    public void reset(){
        CampaignActivity.txt_score_campaign.setText("0");
        ((CampaignActivity)getContext()).findViewById(R.id.txt_score_campaign).setVisibility(VISIBLE);
        score = 0;
        aliens.clear();
        bullets.clear();
        objects.clear();
        bullets_boss.clear();
        booms.clear();
        meteorBosses.clear();
        boom_meteorBosses.clear();
        game_over = false;
        game_over_showed = false;
        isLevelComplete = false;
        isRocketSpawned = false;
        isRocket_boom_created = false;
        isBossSpawned = false;
        isBossBoomAnimationPlaying = false;
        isAllMeteorBossesDestroyed = false;
        rocket_bullet_delay = 1;
        current_y = 0;
        SharedPreferences sp = getContext().getSharedPreferences(GAME_SETTINGS, Context.MODE_PRIVATE);
        if(sp != null){
            bestscore1 = sp.getInt(BEST_SCORE1, 0);
            bestscore2 = sp.getInt(BEST_SCORE2, 0);
            bestscore3 = sp.getInt(BEST_SCORE3, 0);
            bestscore4 = sp.getInt(BEST_SCORE4, 0);
        }
    }
}
