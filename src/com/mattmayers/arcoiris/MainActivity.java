package com.mattmayers.arcoiris;

import java.util.HashMap;
import java.util.Random;

import orbotix.robot.app.StartupActivity;
import orbotix.robot.base.DeviceAsyncData;
import orbotix.robot.base.DeviceMessenger;
import orbotix.robot.base.DeviceSensorsAsyncData;
import orbotix.robot.base.Robot;
import orbotix.robot.base.RobotProvider;
import orbotix.robot.sensor.DeviceSensorsData;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
    MenuItem connect;
    ListView robotList;
    
    private static int CONNECT_ROBOT = 0;
    private static final Random random = new Random();
    
    private static final int[] COLORS = {
        Color.RED,
        Color.BLUE,
        Color.GREEN
    };

    private SparseArray<Robot> robotsByColor = new SparseArray<Robot>(COLORS.length);

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
        
        robotList = (ListView) findViewById(R.id.robot_list);
        
        connect = (MenuItem) findViewById(R.id.item_connect);
        connect.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { 
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(MainActivity.this, StartupActivity.class);
                startActivityForResult(intent, CONNECT_ROBOT);
                return true;
            }
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == CONNECT_ROBOT && resultCode == RESULT_OK){
            String robotId = intent.getStringExtra(StartupActivity.EXTRA_ROBOT_ID);
            Robot robot = RobotProvider.getDefaultProvider().findRobot(robotId);

            for (int color : COLORS) {
                if (!(robotsByColor.get(color) instanceof Robot)) {
                    robotsByColor.put(color, robot);
                }
            }
        }
    }
    
    public static int randomColor() {
        int color = Color.rgb(
                random.nextInt(255),
                random.nextInt(255),
                random.nextInt(255));
        return color;
    }
}