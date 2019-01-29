package com.visionet.indigo_waterlevel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.visionet.indigo_waterlevel.basemqtt.MqttHelper;

public class MainActivity extends AppCompatActivity {

    TextView subText;

    MqttHelper mqttHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subText = (TextView) findViewById(R.id.subText);

        mqttHelper = new MqttHelper(MainActivity.this);
        mqttHelper.setMqttListener(new MqttHelper.MqttListener() {
            @Override
            public void onMessageReceived(String message) {
                subText.setText(message);
            }
        });

        Button buttonPub = (Button) findViewById(R.id.button);
        buttonPub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqttHelper.pub();
            }
        });

        Button buttonConn = (Button) findViewById(R.id.connBtn);
        buttonConn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqttHelper.conn(MainActivity.this);
            }
        });

        Button buttonDisconn = (Button) findViewById(R.id.disconnBtn);
        buttonConn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqttHelper.disconn(MainActivity.this);
            }
        });

    }

}
