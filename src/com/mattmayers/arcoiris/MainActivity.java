package com.mattmayers.arcoiris;

import orbotix.robot.app.StartupActivity;
import orbotix.robot.base.DeviceAsyncData;
import orbotix.robot.base.DeviceMessenger;
import orbotix.robot.base.DeviceSensorsAsyncData;
import orbotix.robot.base.Robot;
import orbotix.robot.base.RobotProvider;
import orbotix.robot.sensor.DeviceSensorsData;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button connectButton;
    Robot robot;
    
    private static int CONNECT_ROBOT = 0;
    
    private final DeviceMessenger.AsyncDataListener mDataListener = new DeviceMessenger.AsyncDataListener() {
        @Override
        public void onDataReceived(DeviceAsyncData data) {
            if (data instanceof DeviceSensorsAsyncData) {
                DeviceSensorsData ballData = ((DeviceSensorsAsyncData)data).getAsyncData().get(0);

                float[] sensorData = new float[3];
                sensorData[0] = (float)ballData.getAttitudeData().getAttitudeSensor().pitch;
                sensorData[1] = (float)ballData.getAttitudeData().getAttitudeSensor().roll;
                sensorData[2] = (float)ballData.getAttitudeData().getAttitudeSensor().yaw;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        connectButton = (Button) findViewById(R.id.button_connect);
        connectButton.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StartupActivity.class);
                startActivityForResult(intent, CONNECT_ROBOT);
            }
            
        });
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == CONNECT_ROBOT && resultCode == RESULT_OK){
            String robotId = intent.getStringExtra(StartupActivity.EXTRA_ROBOT_ID);
            robot = RobotProvider.getDefaultProvider().findRobot(robotId);
            Toast.makeText(this, robot.getName(), Toast.LENGTH_LONG).show();
        }
    }
}