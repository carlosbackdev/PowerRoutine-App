package com.powerroutine.Thread;

import android.widget.Button;
import android.widget.Chronometer;

import com.powerroutine.R;

public class Cronometro extends Thread{

    private Chronometer chronometer;
    private Button btnStart,btnStop;
    private long maxTime;

    public Cronometro(Chronometer chronometer,Button btnStart,Button btnStop,double maxTime){
        this.chronometer = chronometer;
        this.btnStart=btnStart;
        this.btnStop=btnStop;
        this.maxTime = (long) (maxTime * 60000);
    }

    public void run(){
        try{
            Thread.sleep(maxTime);
            chronometer.stop();
            btnStart.setBackgroundResource(R.drawable.button_background_orange);
            btnStop.setBackgroundResource(R.drawable.button_background_dark);
        }catch (Exception e){
         System.out.println(e);
        }
    }
}
