package com.myorg.component;

import org.omg.CORBA.ORB;

/**
 * Created by huyan on 15/11/23.
 */
public class FunctionMenu extends MenuComponent {

    private String id;
    private String name;
    private String displayName;
    private String uri;
    private String icon;
    private int orderNo;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public int getOrderNo() {
        return orderNo;
    }

    @Override
    public String icon() {
        return icon;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }
}
