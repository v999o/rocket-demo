package com.example.rocketdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView txt_best_score, txt_game_over_score, txt_choose_gamemode, txt_score, txt_loading;
    public RelativeLayout rl_game_over, rl_buttons_s;
    public Button btn_normal_survival, btn_boss_survival, btn_campaign;
    public ImageButton btn_to_menu_s, btn_restart_level_s, btn_resume_s;
    public ImageButton btn_pause_s;
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
        txt_loading = findViewById(R.id.txt_loading);
        btn_normal_survival = findViewById(R.id.btn_normal_survival);
        btn_boss_survival = findViewById(R.id.btn_boss_survival);
        btn_campaign = findViewById(R.id.btn_campaign);
        btn_to_menu_s = findViewById(R.id.btn_to_menu_s);
        btn_restart_level_s = findViewById(R.id.btn_restart_level_s);
        btn_pause_s = findViewById(R.id.btn_pause_s);
        btn_resume_s = findViewById(R.id.btn_resume_s);
        rl_game_over = findViewById(R.id.rl_game_over);
        rl_buttons_s = findViewById(R.id.rl_buttons_s);
        gv = findViewById(R.id.gv);

        txt_score.setVisibility(View.INVISIBLE);

        btn_normal_survival.setOnClickListener(view -> {
            startSurvival();
            gv.setBoss_survival(false);
        });
        btn_boss_survival.setOnClickListener(view -> {
            startSurvival();
            gv.setBoss_survival(true);
        });
        rl_game_over.setOnClickListener(view -> toMainMenu());

        btn_campaign.setOnClickListener(view -> {
            txt_loading.setVisibility(View.VISIBLE);
            Intent intent = new Intent(this, CampaignActivity.class);
            startActivity(intent);
        });

        btn_pause_s.setOnClickListener(view -> {
            gv.setPlay(false);
            btn_pause_s.setVisibility(View.INVISIBLE);
            btn_resume_s.setVisibility(View.VISIBLE);
            rl_buttons_s.setVisibility(View.VISIBLE);
        });

        btn_resume_s.setOnClickListener(view -> {
            gv.setPlay(true);
            btn_pause_s.setVisibility(View.VISIBLE);
            btn_resume_s.setVisibility(View.INVISIBLE);
            rl_buttons_s.setVisibility(View.INVISIBLE);
        });

        btn_to_menu_s.setOnClickListener(view -> {
            toMainMenu();
            btn_resume_s.setVisibility(View.INVISIBLE);
            rl_buttons_s.setVisibility(View.INVISIBLE);
        });

        btn_restart_level_s.setOnClickListener(view -> {
            gv.setPlay(true);
            btn_pause_s.setVisibility(View.VISIBLE);
            btn_resume_s.setVisibility(View.INVISIBLE);
            rl_buttons_s.setVisibility(View.INVISIBLE);
            Context context = rl_game_over.getContext();
            gv.reset(context);
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        txt_loading.setVisibility(View.INVISIBLE);
    }

    public void startSurvival(){
        gv.setPlay(true);
        txt_score.setVisibility(View.VISIBLE);
        txt_choose_gamemode.setVisibility(View.INVISIBLE);
        btn_normal_survival.setVisibility(View.INVISIBLE);
        btn_boss_survival.setVisibility(View.INVISIBLE);
        btn_campaign.setVisibility(View.INVISIBLE);
        btn_pause_s.setVisibility(View.VISIBLE);
    }

    public void toMainMenu(){
        rl_game_over.setVisibility(View.INVISIBLE);
        txt_choose_gamemode.setVisibility(View.VISIBLE);
        btn_normal_survival.setVisibility(View.VISIBLE);
        btn_boss_survival.setVisibility(View.VISIBLE);
        btn_campaign.setVisibility(View.VISIBLE);
        Context context = rl_game_over.getContext();
        gv.setPlay(false);
        gv.reset(context);
    }

    public void set_txt_score_text(String string){
        txt_score.setText(string);
    }

    public void set_txt_game_over_score_text(){
        txt_game_over_score.setText(txt_score.getText());
    }

    public void set_txt_best_score_text(String string){
        txt_best_score.setText(string);
    }
}