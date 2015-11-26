package com.example.mhemdan.carmudi_task.ui.activities.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.mhemdan.carmudi_task.R;
import com.example.mhemdan.carmudi_task.ui.activities.products.ProductsListActivity;
import com.example.mhemdan.carmudi_task.ui.base.BaseActivity;


/**
 * Created by mohammed on 7/15/15.
 */
public class SplashActivity extends BaseActivity {
    private Handler handler;
    private Runnable callback;
    boolean isAppInForeGround = true;
    private final int DURATION = 3000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_view);

        handler = new Handler();
        callback = new Runnable() {
            @Override
            public void run() {
                openNextView();
            }
        };

        handler.postDelayed(callback , DURATION);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isAppInForeGround = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!isAppInForeGround) {
            isAppInForeGround = true;
            openNextView();
        }
    }

    private void openNextView(){
        if(isAppInForeGround) {
            startActivity(new Intent(getApplicationContext(), ProductsListActivity.class));
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
}
