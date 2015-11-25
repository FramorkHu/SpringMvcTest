package com.myorg.mobile;

import java.util.Map;
import java.util.Set;

/**
 * Created by huyan on 15/11/25.
 */
public class PropertyCriteria {

    private Map<String, PropertyValue> nameValue = new HashMap<>();


    public void addProperty(String propertyName, PropertyValue propertyValue){
        nameValue.put(propertyName, propertyValue);
    }

    public Object getValue(String propertyName){
        return nameValue.get(propertyName).getValue();
    }
    public PropertyValue getPropertyValue(String propertyName){
        return nameValue.get(propertyName);
    }

    public Set<String> getAllProperties(){
        return nameValue.keySet();
    }
}
