package com.example.furnique.fcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.furnique.R;
import com.example.furnique.activities.MainActivity;
import com.example.furnique.activities.NotificationActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessageService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseMsgService";
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        System.out.println("Notification received: " + notification);
        if (notification == null) {
            Log.d(TAG, "Notification is null");
            return;
        }
        System.out.println("Notification title: " + notification.getTitle());
        String title =  notification.getTitle();
        String body = notification.getBody();
        sendNotification(title, body);
    }
    private void sendNotification(String title, String body) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NotificationActivity.CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.logo)
                .setContentIntent(pendingIntent);
        Notification notification = notificationBuilder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        System.out.println("Notification manager: " + notificationManager);
        if(notificationManager != null) {
            notificationManager.notify(2, notification);
        }
    }
}