package com.fxp.alive.onepx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.ref.WeakReference;

/**
 * Title:       AliveManager
 * <p>
 * Package:     com.fxp.alive.onepx
 *
 * @Author: fxp
 * <p>
 * Create at:   2019/3/25 5:28 PM
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
public class AliveManager {

    private Context context;

    private static AliveManager instance;

    private AliveReceiver aliveReceiver;

    public AliveManager(Context context){
        this.context = context.getApplicationContext();
    }

    public static AliveManager getInstance(Context context) {
        if (instance == null){
            instance = new AliveManager(context);
        }
        return instance;
    }

    public void registerAliveReceiver(){
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Intent.ACTION_SCREEN_ON);
            intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
            aliveReceiver = new AliveReceiver();
            context.registerReceiver(aliveReceiver, intentFilter);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void unRegisterAliveReceiver(){
        try {
            if (aliveReceiver != null){
                context.unregisterReceiver(aliveReceiver);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @Description: 实测华为手机可正常调起AliveActivity。小米手机调不起来
     *
     * @Author:  fxp
     * @Date:    2019/3/27   9:53 AM
     * @param
     * @return   void
     * @exception/throws
     */
    public void startAliveActivity(){
        try {
            Intent intent = new Intent();
            intent.setAction("com.fxp.alive.onepx.AliveActivity");
            context.startActivity(intent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void stopAliveActivity(){
        try {
            if (activityWeakReference != null){
                Activity activity = activityWeakReference.get();
                if (activity != null){
                    activity.finish();
                }
                activityWeakReference = null;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private WeakReference<AliveActivity> activityWeakReference;
    public void setAliveActivity(AliveActivity aliveActivity){
        activityWeakReference = new WeakReference<>(aliveActivity);
    }

}
