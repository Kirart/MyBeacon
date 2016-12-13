package com.example.kiril.mybeacon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.BeaconTransmitter;

import java.util.Arrays;
import java.util.EmptyStackException;

public class MainActivity extends AppCompatActivity {
    Beacon beacon;
    BeaconTransmitter beaconTransmitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView identificator = (TextView) findViewById(R.id.identificator);
        final TextView indicator = (TextView) findViewById(R.id.indicator);

        Button on = (Button) findViewById(R.id.on);
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.i("Beacon", identificator.getText().toString());
                    Beacon beacon = new Beacon.Builder()
                            .setId1(identificator.getText().toString())
                            .setId2("1")
                            .setId3("3")
                            .setManufacturer(0x0118)
                            .setTxPower(-59)
                            .setDataFields(Arrays.asList(new Long[] {0l}))
                            .build();
                    BeaconParser beaconParser = new BeaconParser()
                            .setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25");
                    beaconTransmitter = new BeaconTransmitter(getApplicationContext(), beaconParser);
                    beaconTransmitter.startAdvertising(beacon);
                    indicator.setText("TRANSMITING");
                } catch (Exception e) {
                    Log.i("Beacon", e.getMessage());
                }
            }
        });

        Button off = (Button) findViewById(R.id.off);
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    beaconTransmitter.stopAdvertising();
                    indicator.setText("NOT TRANSMITING");
                } catch (Exception e) {
                    Log.i("Beacon", e.getMessage());
                }
            }
        });


    }
}
