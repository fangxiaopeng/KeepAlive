package com.fxp.alive.onepx;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

/**
 * Title:       AliveReceiver
 * <p>
 * Package:     com.fxp.alive.onepx
 *
 * @Author: fxp
 * <p>
 * Create at:   2019/3/25 5:26 PM
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
public class AliveReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.equals(action, Intent.ACTION_SCREEN_ON)){
            Log.e("fxp-AliveReceiver", "ACTION_SCREEN_ON");

            AliveManager.getInstance(context).stopAliveActivity();

        } else if (TextUtils.equals(action, Intent.ACTION_SCREEN_OFF)){
            Log.e("fxp-AliveReceiver", "ACTION_SCREEN_OFF");

            AliveManager.getInstance(context).startAliveActivity();

        } else {}
    }

}
