package com.fxp.alive.doubleprocess;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Title:       AliveService
 * <p>
 * Package:     com.fxp.alive.doubleprocess
 *
 * @Author: fxp
 * <p>
 * Create at:   2019/3/18 9:56 AM
 * <p>
 * Description:
 * <p>
 * <p>
 * Modification History:
 * <p>
 * Date       Author       Version      Description
 * -----------------------------------------------------------------
 * 2019/3/18    fxp       1.0         First Created
 * <p>
 * Github:  https://github.com/fangxiaopeng
 */
public class AliveService extends Service {

    private static final int SERVICE_ID = 10000;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    private class LocalBinder extends Binder {

    }

    @Override
    public void onCreate(){
        super.onCreate();

        Log.e("fxp-AliveService", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("fxp-AliveService", "onStartCommand - startId = " + startId + ", Thread ID = " + Thread.currentThread().getId());

        if (Build.VERSION.SDK_INT < 18){
            // 4.4之前
            startForeground(SERVICE_ID, new Notification());
        } else if (Build.VERSION.SDK_INT < 26){
            // 7.0之前
            startForeground(SERVICE_ID, new Notification());
            // 删除通知栏消息
            startService(new Intent(this, InnerService.class));
        } else {
            // 7.0之后
            NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

            // 设置通知重要性为低
            NotificationChannel channel = new NotificationChannel("FXP", "Alive Service", NotificationManager.IMPORTANCE_MIN);
            if (manager != null){
                manager.createNotificationChannel(channel);
                Notification notification = new NotificationCompat.Builder(this, "FXP").build();
                startForeground(SERVICE_ID, notification);
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public static class InnerService extends Service {

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Log.e("fxp-InnerService", "onStartCommand - startId = " + startId + ", Thread ID = " + Thread.currentThread().getId());

            startForeground(SERVICE_ID, new Notification());
            // 让通知消失
            stopForeground(true);
            stopSelf();

            return super.onStartCommand(intent, flags, startId);
        }
    }
}