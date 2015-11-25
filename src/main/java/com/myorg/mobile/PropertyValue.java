package com.myorg.mobile;

/**
 * Created by huyan on 15/11/25.
 */
public class PropertyValue {

    private Object value;

    public PropertyValue(Object value){
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
