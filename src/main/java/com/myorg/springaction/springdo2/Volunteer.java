package com.myorg.springaction.springdo2;

/**
 * Created by huyan on 15/6/24.
 */
public class Volunteer implements Thinker {

    private String thought;

    @Override
    public void thinkOfSomething(String thought) {

        this.thought = thought;
    }

    public String getThought(){
        return thought;
    }
}
