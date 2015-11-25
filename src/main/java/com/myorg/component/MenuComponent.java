package com.myorg.component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huyan on 15/11/23.
 */
public abstract class MenuComponent {

    public void addMenu(MenuComponent menuComponent){

    }

    public List<MenuComponent> getChildren(){
        return new ArrayList<MenuComponent>();
    }

    public String getId(){
        return "";
    }

    public String getName(){
        return "";
    }

    public String getDisplayName(){
        return "";
    }

    public String getUri(){
        return "";
    }

    public int getOrderNo(){
        return 0;
    }

    public String icon(){
        return "";
    }

}
