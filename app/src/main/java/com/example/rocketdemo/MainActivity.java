package com.example.rocketdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static TextView txt_score, txt_best_score, txt_game_over_score, txt_choose_gamemode;
    public static RelativeLayout rl_game_over;
    public static Button btn_normal_survival, btn_boss_survival, btn_campaign, btn_to_menu;
    private GameView gv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);  //чтобы убрать нижние кнопки
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        setContentView(R.layout.activity_main);
        txt_score = findViewById(R.id.txt_score);
        txt_best_score = findViewById(R.id.txt_best_score);
        txt_game_over_score = findViewById(R.id.txt_game_over_score);
        txt_choose_gamemode = findViewById(R.id.txt_choose_gamemode);
        btn_normal_survival = findViewById(R.id.btn_normal_survival);
        btn_boss_survival = findViewById(R.id.btn_boss_survival);
        btn_campaign = findViewById(R.id.btn_campaign);
        rl_game_over = findViewById(R.id.rl_game_over);
        gv = findViewById(R.id.gv);

        txt_score.setVisibility(View.INVISIBLE);

        btn_normal_survival.setOnClickListener(view -> {
            gv.setPlay(true);
            gv.setBoss_survival(false);
            txt_score.setVisibility(View.VISIBLE);
            txt_choose_gamemode.setVisibility(View.INVISIBLE);
            btn_normal_survival.setVisibility(View.INVISIBLE);
            btn_boss_survival.setVisibility(View.INVISIBLE);
            btn_campaign.setVisibility(View.INVISIBLE);
        });
        btn_boss_survival.setOnClickListener(view -> {
            gv.setPlay(true);
            gv.setBoss_survival(true);
            txt_score.setVisibility(View.VISIBLE);
            txt_choose_gamemode.setVisibility(View.INVISIBLE);
            btn_normal_survival.setVisibility(View.INVISIBLE);
            btn_boss_survival.setVisibility(View.INVISIBLE);
            btn_campaign.setVisibility(View.INVISIBLE);
        });
        rl_game_over.setOnClickListener(view -> {
            rl_game_over.setVisibility(View.INVISIBLE);
            txt_choose_gamemode.setVisibility(View.VISIBLE);
            btn_normal_survival.setVisibility(View.VISIBLE);
            btn_boss_survival.setVisibility(View.VISIBLE);
            btn_campaign.setVisibility(View.VISIBLE);
            Context context = rl_game_over.getContext();
            gv.setPlay(false);
            gv.reset(context);
        });
        btn_campaign.setOnClickListener(view -> {
            Intent intent = new Intent(this, CampaignActivity.class);
            startActivity(intent);
        });
    }
}