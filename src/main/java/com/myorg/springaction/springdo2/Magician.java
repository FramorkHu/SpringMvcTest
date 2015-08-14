package com.myorg.springaction.springdo2;

/**
 * Created by huyan on 15/6/24.
 */
public class Magician implements MindReader {

    private String thought;

    @Override
    public void interceptThought(String thought) {

        System.out.println("Intercept volunteer's thought");
        this.thought = thought;
    }

    @Override
    public String getThought() {
        return thought;
    }
}
