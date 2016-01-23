package pl.edu.uksw.prir.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Jakub on 16/01/16.
 */
public class MessangerService extends Service {

    public void onCreate(){
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        return START_NOT_STICKY;
    }

    public void onDestroy(){
        super.onDestroy();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) { return null; }
}
