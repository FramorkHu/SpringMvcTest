package com.myorg.springaction.springdo1;

/**
 * Created by huyan on 15/6/18.
 */
public class TestBvo {

    private String chosenData;

    private String propertyData;

    public String getChosenData() {
        return chosenData;
    }

    public void setChosenData(String chosenData) {
        this.chosenData = chosenData;
    }

    public String getPropertyData() {
        return propertyData;
    }

    public void setPropertyData(String propertyData) {
        this.propertyData = propertyData;
    }

    @Override
    public String toString() {
        return "TestBvo{" +
                "chosenData='" + chosenData + '\'' +
                ", propertyData='" + propertyData + '\'' +
                '}';
    }
}
