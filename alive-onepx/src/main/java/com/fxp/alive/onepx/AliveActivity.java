package com.fxp.alive.onepx;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Title:       AliveActivity
 * <p>
 * Package:     com.fxp.alive.onepx
 *
 * @Author: fxp
 * <p>
 * Create at:   2019/3/25 5:21 PM
 * <p>
 * Description:
 * <p>
 * <p>
 * Modification History:
 * <p>
 * Date       Author       Version      Description
 * -----------------------------------------------------------------
 * 2019/3/25    fxp       1.0         First Created
 * <p>
 * Github:  https://github.com/fangxiaopeng
 */
public class AliveActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("fxp-AliveActivity", "onCreate");

        Window window = getWindow();
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 0;
        params.y = 0;
        params.height = 1;
        params.width = 1;
        window.setAttributes(params);

        AliveManager.getInstance(this).setAliveActivity(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("fxp-AliveActivity", "onDestroy");

    }

}
