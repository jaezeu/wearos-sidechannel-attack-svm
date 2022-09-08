package com.example.jaz.acc;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.widget.TextView;


import java.io.FileOutputStream;


public class MainActivity extends WearableActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";

    private TextView mTextView;
    private SensorManager sensorManager;
    private Sensor accelerometer, mGyro, mCompass;
    public int count=0;

    TextView xValue,yValue,zValue,xGyroValue,yGyroValue,zGyroValue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xValue = (TextView) findViewById(R.id.xValue);
        yValue = (TextView) findViewById(R.id.yValue);
        zValue = (TextView) findViewById(R.id.zValue);


        xGyroValue = (TextView) findViewById(R.id.xGyroValue);
        yGyroValue = (TextView) findViewById(R.id.yGyroValue);
        zGyroValue = (TextView) findViewById(R.id.zGyroValue);

        Log.d(TAG,"onCreate: Initializing Sensor Services");

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if(accelerometer != null) {

            sensorManager.registerListener(MainActivity.this, accelerometer, sensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered accelerometer Listener");

        }else{
            xValue.setText("Accelerometer Not supported");
            yValue.setText("Accelerometer Not supported");
            zValue.setText("Accelerometer Not supported");
        }

        mGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if(mGyro != null) {

            sensorManager.registerListener(MainActivity.this, mGyro, sensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Gyro Listener");

        }else{

            xGyroValue.setText("Gyro Not supported");
            yGyroValue.setText("Gyro Not supported");
            zGyroValue.setText("Gyro Not supported");
        }


        mTextView = (TextView) findViewById(R.id.text);

        // Enables Always-on
        setAmbientEnabled();

        String FILENAME = "acc2handverticalthumb.csv";
        String header = "xValue, yValue, zValue \n";

        try{
            FileOutputStream out = openFileOutput(FILENAME, Context.MODE_APPEND);
            out.write(header.getBytes());
            out.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        String FILENAME2 = "gyro2handverticalthumb.csv";
        String header2 = "xGyro, yGyro, zGyro \n";

        try{
            FileOutputStream out = openFileOutput(FILENAME2, Context.MODE_APPEND);
            out.write(header2.getBytes());
            out.close();

        }catch (Exception e){
            e.printStackTrace();
        }


    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;

        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            Log.d(TAG, "onSensorChanged: AccX: " + sensorEvent.values[0] + " AccY: " + sensorEvent.values[1] + " AccZ: " + sensorEvent.values[2]);

            xValue.setText("xValue: " + sensorEvent.values[0]);
            yValue.setText("yValue: " + sensorEvent.values[1]);
            zValue.setText("zValue: " + sensorEvent.values[2]);


            String FILENAME = "acc2handverticalthumb.csv";
            String entry = sensorEvent.values[0] + "," +
                             sensorEvent.values[1] + "," +
                             sensorEvent.values[2] + "\n";

            try{
                FileOutputStream out = openFileOutput(FILENAME, Context.MODE_APPEND);
                out.write(entry.getBytes() );
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }if(sensor.getType() == sensor.TYPE_GYROSCOPE)
        {
            Log.d(TAG, "onSensorChanged: GyroX: " + sensorEvent.values[0] + " GyroY: " + sensorEvent.values[1] + " GyroZ: " + sensorEvent.values[2]);

            xGyroValue.setText("xGyroValue: " + sensorEvent.values[0]);
            yGyroValue.setText("yGyroValue: " + sensorEvent.values[1]);
            zGyroValue.setText("zGyroValue: " + sensorEvent.values[2]);


            String FILENAME2 = "gyro2handverticalthumb.csv";
            String entry = sensorEvent.values[0] + "," +
                    sensorEvent.values[1] + "," +
                    sensorEvent.values[2] + "\n";

            try{
                FileOutputStream out = openFileOutput(FILENAME2, Context.MODE_APPEND);
                out.write(entry.getBytes() );
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }

}
