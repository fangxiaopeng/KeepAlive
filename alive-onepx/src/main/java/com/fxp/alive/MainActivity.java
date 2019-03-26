package com.fxp.alive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fxp.alive.onepx.AliveManager;
import com.fxp.alive_onepx.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // 注册屏幕状态广播
            AliveManager.getInstance(this).registerAliveReceiver();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        try {
            // 取消注册屏幕状态
            AliveManager.getInstance(this).unRegisterAliveReceiver();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
