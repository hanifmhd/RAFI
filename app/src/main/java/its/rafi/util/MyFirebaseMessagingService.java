package its.rafi.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import its.rafi.MapsActivity;
import its.rafi.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by ASUS on 6/9/2017.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        if(sessionManager.isLogin()){
            if(remoteMessage.getData().get("user_type") != null){
                if( remoteMessage.getData().get("user_type").equals(sessionManager.getUserType())){
                    broadcast();
                    sendNotification( remoteMessage.getData().get("message"));
                }
                }
            else if (remoteMessage.getData().get("user_id") != null ){
                if(remoteMessage.getData().get("user_id").equals(sessionManager.getUid())  ){
                    broadcast();
                    sendNotification( remoteMessage.getData().get("message"));
                }
            }

        }

    }

    private  void broadcast(){
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.setAction("com.rafi.loadData");
        sendBroadcast(intent);
    }

    private void sendNotification(String messageBody) {

        Bitmap bmp =  BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Intent intent = new Intent(this, MapsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(bmp)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Klik untuk Membuka")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }


}


