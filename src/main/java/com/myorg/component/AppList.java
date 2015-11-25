package com.myorg.component;

import java.util.*;

/**
 * Created by huyan on 15/11/23.
 */
public class AppList {

    public void getApp(){
        Map<String, Map<String, Object>> funcMenuListMap = new HashMap<String, Map<String, Object>>();
        Map<String, Map<String, Object>> usingAppMap = new HashMap<String, Map<String, Object>>();


        MenuComponent menu = new AppMenu();
        for (Map<String, Object> app : usingAppMap.values()){

        }
    }

    public MenuComponent getAppMenu(Map<String, Map<String, Object>> usingAppMap,
                                    Map<String, Object> data){
        AppMenu appMenu = new AppMenu();
        appMenu.setId(String.valueOf(data.get("id")));



        return appMenu;
    }
}
