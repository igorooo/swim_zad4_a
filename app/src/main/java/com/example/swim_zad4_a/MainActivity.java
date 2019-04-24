package com.example.swim_zad4_a;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button LightB,AccuB,GpsB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LightB = (Button) findViewById(R.id.LightButton);
        AccuB = (Button) findViewById(R.id.AccelButton);
        GpsB = (Button) findViewById(R.id.GpsButton);

        LightB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAktywnosci(v);
            }
        });
        AccuB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAktywnosci(v);
            }
        });
        GpsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAktywnosci(v);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView tv1 = (TextView)findViewById(R.id.tvLight);
        TextView tv2 = (TextView)findViewById(R.id.tvMove);
        TextView tv3 = (TextView)findViewById(R.id.tvGPS);

        final SensorManager sm = (SensorManager)getSystemService(SENSOR_SERVICE);


        boolean enabled = !sm.getSensorList(Sensor.TYPE_LIGHT).isEmpty();
        tv1.setText(getString(R.string.lightStatus) + " " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tv1.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.LightButton).setEnabled(enabled);

        enabled = !sm.getSensorList(Sensor.TYPE_ACCELEROMETER).isEmpty();
        tv2.setText(getString(R.string.accelStatus) + " " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tv2.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.AccelButton).setEnabled(enabled);

        final LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        tv3.setText(getString(R.string.gpsStatus) + " " + getString(enabled ? R.string.txt_avail : R.string.txt_unavail));
        tv3.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.GpsButton).setEnabled(enabled);


    }

    public final void startAktywnosci(final View v){
        Intent in;

        if(v.getId() == R.id.GpsButton){
            in = new Intent(this,GPS.class);
        }

        else{
            in = new Intent(this, ASensor.class);

            if(v.getId() == R.id.LightButton){
                in.putExtra("sensorType",Sensor.TYPE_LIGHT);
            }

            else if(v.getId() == R.id.AccelButton){
                in.putExtra("sensorType",Sensor.TYPE_ACCELEROMETER);
            }
        }

        startActivity(in);
    }
}
