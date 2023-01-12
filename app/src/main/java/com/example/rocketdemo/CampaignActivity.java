package com.example.rocketdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CampaignActivity extends AppCompatActivity {

    public static TextView txt_score_campaign, txt_game_over_score_c, txt_best_score_c, txt_level_complete, txt_game_over_c;
    public static Button btn_lvl1, btn_lvl2, btn_lvl3, btn_lvl4;
    public static ImageButton btn_to_menu, btn_restart_level, btn_next_level, btn_back, btn_pause, btn_resume;
    public static RelativeLayout rl_game_over_c, rl_buttons;
    private CampaignView cv;

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
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_campaign);
        txt_score_campaign = findViewById(R.id.txt_score_campaign);
        btn_lvl1 = findViewById(R.id.btn_lvl1);
        btn_lvl2 = findViewById(R.id.btn_lvl2);
        btn_lvl3 = findViewById(R.id.btn_lvl3);
        btn_lvl4 = findViewById(R.id.btn_lvl4);
        btn_to_menu = findViewById(R.id.btn_to_menu);
        btn_restart_level = findViewById(R.id.btn_restart_level);
        btn_next_level = findViewById(R.id.btn_next_level);
        btn_back = findViewById(R.id.btn_back);
        btn_pause = findViewById(R.id.btn_pause);
        btn_resume = findViewById(R.id.btn_resume);
        rl_game_over_c = findViewById(R.id.rl_game_over_c);
        rl_buttons = findViewById(R.id.rl_buttons);
        txt_game_over_score_c = findViewById(R.id.txt_game_over_score_c);
        txt_best_score_c = findViewById(R.id.txt_best_score_c);
        txt_level_complete = findViewById(R.id.txt_level_complete);
        txt_game_over_c = findViewById(R.id.txt_game_over_c);


        cv = findViewById(R.id.cv);

        MainActivity.txt_loading.setVisibility(View.INVISIBLE);
        txt_score_campaign.setVisibility(View.INVISIBLE);

        btn_back.setOnClickListener(view -> CampaignActivity.this.finish());

        btn_lvl1.setOnClickListener(view -> {
            startCampaignLevel();
            cv.setLevel_number(1);
        });

        btn_lvl2.setOnClickListener(view -> {
            startCampaignLevel();
            cv.setLevel_number(2);
        });

        btn_lvl3.setOnClickListener(view -> {
            startCampaignLevel();
            cv.setLevel_number(3);
        });

        btn_lvl4.setOnClickListener(view -> {
            startCampaignLevel();
            cv.setLevel_number(4);
        });

        btn_pause.setOnClickListener(view -> {
            cv.setStart(false);
            btn_pause.setVisibility(View.INVISIBLE);
            rl_buttons.setVisibility(View.VISIBLE);
            btn_resume.setVisibility(View.VISIBLE);
            btn_next_level.setVisibility(View.INVISIBLE);
        });

        btn_resume.setOnClickListener(view -> {
            cv.setStart(true);
            btn_pause.setVisibility(View.VISIBLE);
            rl_buttons.setVisibility(View.INVISIBLE);
            btn_resume.setVisibility(View.INVISIBLE);
        });

        btn_to_menu.setOnClickListener(view -> CampaignActivity.this.finish());

        btn_restart_level.setOnClickListener(view -> {
            rl_game_over_c.setVisibility(View.INVISIBLE);
            rl_buttons.setVisibility(View.INVISIBLE);
            btn_pause.setVisibility(View.VISIBLE);
            btn_resume.setVisibility(View.INVISIBLE);
            cv.setStart(true);
            cv.reset();
        });

        btn_next_level.setOnClickListener(view -> {
            rl_game_over_c.setVisibility(View.INVISIBLE);
            btn_next_level.setVisibility(View.INVISIBLE);
            rl_buttons.setVisibility(View.INVISIBLE);
            btn_pause.setVisibility(View.VISIBLE);
            cv.setStart(true);
            cv.reset();
            if (cv.getLevel_number() < 4) {
                cv.setLevel_number(cv.getLevel_number() + 1);
            }
        });
    }

    public void startCampaignLevel() {
        txt_score_campaign.setVisibility(View.VISIBLE);
        btn_lvl1.setVisibility(View.INVISIBLE);
        btn_lvl2.setVisibility(View.INVISIBLE);
        btn_lvl3.setVisibility(View.INVISIBLE);
        btn_lvl4.setVisibility(View.INVISIBLE);
        btn_back.setVisibility(View.INVISIBLE);
        btn_pause.setVisibility(View.VISIBLE);
        cv.setStart(true);
    }
}