package com.example.marking_itus;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.particle.android.sdk.cloud.ParticleCloud;
import io.particle.android.sdk.cloud.ParticleCloudSDK;
import io.particle.android.sdk.cloud.ParticleDevice;
import io.particle.android.sdk.cloud.ParticleEvent;
import io.particle.android.sdk.cloud.ParticleEventHandler;
import io.particle.android.sdk.cloud.exceptions.ParticleCloudException;
import io.particle.android.sdk.utils.Async;

public class MainActivity extends AppCompatActivity {

    private final String TAG="Marking-iTus";

    // MARK: Particle Account Info
    private final String PARTICLE_USERNAME = "hemantpatel564@gmail.com";
    private final String PARTICLE_PASSWORD = "Hemant564";

    // MARK: Particle device-specific info
    private final String DEVICE_ID = "270018001047363333343437";

    // MARK: Particle Publish / Subscribe variables
    private long subscriptionId;

    // MARK: Particle device
    private ParticleDevice mDevice;

    int timer= 0;
    int starttime;

    TextView textview;
    Button startbuttton;

    Handler publicHanlder;
    SeekBar seekbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textview = (TextView) findViewById(R.id.textView);
        startbuttton =  (Button) findViewById(R.id.button);
        seekbar = (SeekBar)findViewById(R.id.seekBar);

        seekbar.setProgress(0);
        seekbar.incrementProgressBy(1);
        seekbar.setMax(20);

        TextView seekBarValue = findViewById(R.id.time);


        startbuttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnlightson();
            }
        });
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / 4;
                progress = progress * 4;
                seekBarValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });





        // 1. Initialize your connection to the Particle API
        ParticleCloudSDK.init(this.getApplicationContext());

        // 2. Setup your device variable
        getDeviceFromCloud();

    }


    /**
     * Custom function to connect to the Particle Cloud and get the device
     */
    public void getDeviceFromCloud() {
        // This function runs in the background
        // It tries to connect to the Particle Cloud and get your device
        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {

            @Override
            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                particleCloud.logIn(PARTICLE_USERNAME, PARTICLE_PASSWORD);
                mDevice = particleCloud.getDevice(DEVICE_ID);
                return -1;

            }

            @Override
            public void onSuccess(Object o) {

                Log.d(TAG, "Successfully got device from Cloud");

                // if you get the device, then go subscribe to events
                subscribeToParticleEvents();
            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                Log.d(TAG, exception.getBestMessage());
            }
        });
    }

    public void turnlightson() {

        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {
            @Override
            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                // put your logic here to talk to the particle
                // --------------------------------------------
                List<String> functionParameters = new ArrayList<String>();
                functionParameters.add("0");
                try {
                    mDevice.callFunction("faces", functionParameters);

                } catch (ParticleDevice.FunctionDoesNotExistException e1) {
                    e1.printStackTrace();
                    Log.d(TAG, "callApi: "+e1.getLocalizedMessage());
                }


                return -1;
            }

            @Override
            public void onSuccess(Object o) {
                // put your success message here
                Log.d(TAG, "Success: Turned light Red!!");
            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                // put your error handling code here
                Log.d(TAG, exception.getBestMessage());
            }
        });
    }


    public void subscribeToParticleEvents() {
        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {
            @Override
            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                subscriptionId = ParticleCloudSDK.getCloud().subscribeToDeviceEvents(
                        "faces",  // the first argument, "eventNamePrefix", is optional
                        DEVICE_ID,
                        new ParticleEventHandler() {
                            public void onEvent(String eventName, ParticleEvent event) {
                                Log.i(TAG, "Received event with payload: " + event.dataPayload);
                                String choice = event.dataPayload;
                                if (choice.contentEquals("0")) {
                                    turnlightson();
                                }
                                else if (choice.contentEquals("4")) {
                                    turnlightson();
                                }
                                else if (choice.contentEquals("8")) {
                                    turnlightson();
                                }
                                else if (choice.contentEquals("12")) {
                                    turnlightson();
                                }
                                else if (choice.contentEquals("16")) {
                                    turnlightson();
                                }
                                else if (choice.contentEquals("20")) {
                                    turnlightson();
                                }

                            }

                            public void onEventError(Exception e) {
                                Log.e(TAG, "Event error: ", e);
                            }
                        });


                return -1;
            }


            @Override
            public void onSuccess(Object o) {
                Log.d(TAG, "Success: Subscribed to events!");
            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                Log.d(TAG, exception.getBestMessage());
            }
        });
    }



}
