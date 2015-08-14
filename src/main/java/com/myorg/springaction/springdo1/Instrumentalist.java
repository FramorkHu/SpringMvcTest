package com.myorg.springaction.springdo1;

/**
 * Created by huyan on 15/6/17.
 */
public class Instrumentalist implements Performer{

    private String song;

    private Instrument instrument;

    public void setSong(String song) {
        this.song = song;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public String getSong() {
        return song;
    }

    @Override
    public void perform() {

        System.out.println("Playing"+song+":");
        instrument.play();
    }
}
