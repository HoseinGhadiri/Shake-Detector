package com.example.shakeeventdetector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


//    /* put this into your activity class */
//    private SensorManager mSensorManager;
//    private float mAccel; // acceleration apart from gravity
//    private float mAccelCurrent; // current acceleration including gravity
//    private float mAccelLast; // last acceleration including gravity
//
//    float last_x;
//    float last_y;
//    float last_z;
//
//    long lastUpdate = System.currentTimeMillis();
//
//    private static final int SHAKE_THRESHOLD = 800;

    // The following are used for the shake detection
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Log.w("shake", "shake detector = " + 0);
//        Toast.makeText(this, "shake detected : " + 0, Toast.LENGTH_SHORT).show();

//        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
//        mAccel = 0.00f;
//        mAccelCurrent = SensorManager.GRAVITY_EARTH;
//        mAccelLast = SensorManager.GRAVITY_EARTH;

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                /*
                 * The following method, "handleShakeEvent(count):" is a stub //
                 * method you would use to setup whatever you want done once the
                 * device has been shook.
                 */
                handleShakeEvent(count);
            }
        });

    }

    private void handleShakeEvent(int count){
        Log.w("shake", "shake detected = " + count);
        Toast.makeText(this, "shake detected : " + count, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getBaseContext(), SecondActivity.class);
        startActivity(intent);
    }

//    @Override
//    public void onSensorChanged(SensorEvent sensorEvent) {
//
//        long curTime = System.currentTimeMillis();
//        // only allow one update every 100ms.
////        if ((curTime - lastUpdate) > 100) {
////            long diffTime = (curTime - lastUpdate);
////            lastUpdate = curTime;
////
////            float x = sensorEvent.values[0];
////            float y = sensorEvent.values[1];
////            float z = sensorEvent.values[2];
////            mAccelLast = mAccelCurrent;
////            mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
////            float delta = mAccelCurrent - mAccelLast;
////            mAccel = mAccel * 0.9f + delta; // perform low-cut filter
////
////            float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
////
////            if (speed > SHAKE_THRESHOLD) {
////                Log.d("sensor", "shake detected w/ speed: " + speed);
////                Toast.makeText(this, "shake detected w/ speed: " + speed, Toast.LENGTH_SHORT).show();
////            }
////        }
//
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int i) {
//
//    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}