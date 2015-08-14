package com.myorg.springaction.connectionPool;

import java.sql.Connection;

/**
 * Created by huyan on 2015/8/14.
 */
public class PooledConnection {

    private Connection conn = null;
    private boolean isUser = false;

    public PooledConnection(Connection conn){
        this.conn = conn;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean isUser) {
        this.isUser = isUser;
    }
}
