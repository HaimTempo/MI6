package bgu.spl.mics.application.messages;
import bgu.spl.mics.Broadcast;

public class TickBroadcast implements Broadcast {//TODO fix that shit
    private int currentTime;

    public TickBroadcast(int currentTime) {
        this.currentTime = currentTime;
    }

    public TickBroadcast() {
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }
}