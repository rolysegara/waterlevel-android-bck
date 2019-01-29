package com.visionet.indigo_waterlevel.basemqtt;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import com.visionet.indigo_waterlevel.MainActivity;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttHelper {

    //String topicStr = "sensor/temp";
    String topicStr = "/reservoirData";
    String topicStr1 = "/notification";

    MqttAndroidClient client;
    MqttConnectOptions options;
    Vibrator vibrator;
    Ringtone myRingtone;

    private MqttListener listener;

    public MqttHelper(final Context context){

        this.listener = null;

        vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        myRingtone = RingtoneManager.getRingtone(context.getApplicationContext(), uri);

        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(
                context.getApplicationContext(),
                Const.MQTTHOST,
                clientId);

        options = new MqttConnectOptions();
        //options.setUserName(Const.USERNAME);
        //options.setPassword(Const.PASSWORD.toCharArray());

        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                    setSubscription();

                    // We are connected
                    Toast.makeText(context, "Connected!", Toast.LENGTH_LONG).show();
                    Log.d("TAG", "onSuccess");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Toast.makeText(context, "Conection fail!", Toast.LENGTH_LONG).show();
                    Log.d("TAG", "onFailure");

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                String textMsg = new String(message.getPayload());
                listener.onMessageReceived(textMsg);
                    //subText.setText(textMsg);
                vibrator.vibrate(500);
                myRingtone.play();
                showNotification(context, "Water Level", textMsg);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }

    public void setMqttListener(MqttListener listener){
        this.listener = listener;
    }

    public void pub(){
        String topic = topicStr;
        String message = "Hello world from mqtt..";

        try {
            client.publish(topic, message.getBytes(), 0, false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void setSubscription(){
        try {
            client.subscribe(topicStr, 0);
            client.subscribe(topicStr1, 0);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void conn(final Context context) {
        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                    setSubscription();

                    // We are connected
                    Toast.makeText(context, "Connected!", Toast.LENGTH_LONG).show();
                    Log.d("TAG", "onSuccess");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Toast.makeText(context, "Conection fail!", Toast.LENGTH_LONG).show();
                    Log.d("TAG", "onFailure");

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void disconn(final Context context){
        try {
            IMqttToken token = client.disconnect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Toast.makeText(context, "Disconnected!", Toast.LENGTH_LONG).show();
                    Log.d("TAG", "onSuccess");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Toast.makeText(context, "Could not disconnect!", Toast.LENGTH_LONG).show();
                    Log.d("TAG", "onFailure");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void showNotification(Context context, String title, String message) {

        Intent notifyIntent = new Intent(context, MainActivity.class);
        notifyIntent.putExtra("Snoozer",1);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivities(context, 0,
                new Intent[] { notifyIntent }, PendingIntent.FLAG_UPDATE_CURRENT);
        android.app.Notification notification = new android.app.Notification.Builder(context)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        //notification.defaults |= android.app.Notification.DEFAULT_SOUND;
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(2, notification);

    }

    public interface MqttListener {
        public void onMessageReceived(String message);
    }
}
