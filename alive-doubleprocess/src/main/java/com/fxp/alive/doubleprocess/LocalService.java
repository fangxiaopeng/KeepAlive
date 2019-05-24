package com.fxp.alive.doubleprocess;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

/**
 * Title:       LocalService
 * <p>
 * Package:     com.fxp.alive.doubleprocess
 *
 * @Author: fxp
 * <p>
 * Create at:   2019/3/18 10:59 AM
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
public class LocalService extends AliveService{


    @Override
    public void onCreate(){
        super.onCreate();
        Log.e("fxp-LocalService", "onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("fxp-LocalService", "onStartCommand - startId = " + startId + ", Thread ID = " + Thread.currentThread().getId());

        bindRemoteService();

        return super.onStartCommand(intent, flags, startId);
    }

    private void bindRemoteService(){
        try {
            Intent serviceIntent = new Intent();
            ComponentName componentName = new ComponentName("com.fxp.alive.doubleprocess","com.fxp.alive.doubleprocess.RemoteService");
            serviceIntent.setComponent(componentName);
            bindService(serviceIntent, serviceConnection, Service.BIND_IMPORTANT);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void startRemoteService(){
        try {
            Intent serviceIntent = new Intent();
            ComponentName componentName = new ComponentName("com.fxp.alive.doubleprocess","com.fxp.alive.doubleprocess.RemoteService");
            serviceIntent.setComponent(componentName);
            startService(serviceIntent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            startRemoteService();
            bindRemoteService();
        }
    };
}
