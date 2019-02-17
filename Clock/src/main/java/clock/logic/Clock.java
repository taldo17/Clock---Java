package clock.logic;

import clock.ui.CyanLight;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Clock extends TimerTask {

    private Time time = new Time(0,0,0);
    private Mode mode = Mode.DEFAULT;
    private int modeIndex;
    private List<Mode> modes = new ArrayList<>();
    private CyanLight cyanLight;

    public Clock(CyanLight cyanLight) {
        this.cyanLight = cyanLight;
        modes.add(Mode.DEFAULT);
        modes.add(Mode.HOURS);
        modes.add(Mode.MINUTES);
        modes.add(Mode.SECONDS);
        modes.add(Mode.LIGHT);
    }

    public Mode getMode() {
        return mode;
    }

    @Override
    public void run() {
        tick();
    }

    private void tick(){
       time.seconds++;
       if(time.seconds == 60){
           time.seconds = 0;
           time.minutes++;
           if(time.minutes == 60){
               time.minutes = 0;
               time.hours = (time.hours + 1) % 24;
           }
       }
    }

    public void start(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(this, 0, 1000);
    }

    @Override
    public String toString() {
        return parseTime(time.hours) + ":" + parseTime(time.minutes)
                + ":" + parseTime(time.seconds);
    }

    private String parseTime(int time) {
        if (time < 10){
            return "0" + time;
        }
        return String.valueOf(time);
    }

    public void changeMode() {
        modeIndex++;
        modeIndex = modeIndex % modes.size();
        mode = modes.get(modeIndex);
    }

    public void action() {
        if(mode.equals(Mode.DEFAULT)){
            return;
        }
        else if(mode.equals(Mode.HOURS)){
            time.hours = (time.hours + 1) % 24;
        }
        else if(mode.equals(Mode.MINUTES)){
            time.minutes = (time.minutes + 1) % 60;
        }
        else if(mode.equals(Mode.SECONDS)){
            time.seconds = (time.seconds + 1) % 60;
        }
        else if(mode.equals(Mode.LIGHT)){
            cyanLight.lightOn();
        }
    }
}
