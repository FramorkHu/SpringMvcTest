package com.myorg.springaction.connectionPool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by huyan on 2015/8/14.
 */
public class SimpleConnectionPool {

    private String jdbcDriver = "";//数据库驱动
    private String dbUrl = "";//数据库url
    private String dbUsername = "";//数据库用户名
    private String dbPassword = "";//数据库密码
    private int initialConnectionsNum = 10;//连接池初始连接数
    private int maxConnectionsNum = 50;//连接池最大连接数
    private int incrementalConnections = 5;//每次动态添加的连接数
    private List<PooledConnection> connections = null;//向量，存放连接池中的连接，初始为空

    public SimpleConnectionPool(String jdbcDriver, String dbUrl, String dbUsername, String dbPassword){


    }

    public synchronized void initPool() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        if (this.connections != null){
            return;
        }

        //实例化驱动，通过反射构造
        Driver driver = (Driver)Class.forName(this.jdbcDriver).newInstance();
        DriverManager.registerDriver(driver);
        connections = new ArrayList<PooledConnection>();


    }

    private void createConnections(int poolNum){

        for (int i=0; i<poolNum; i++){
            if (connections.size() >= maxConnectionsNum){
                return;
            }

        }
    }

    private Connection newConnection(){

        try {
            Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (connections.size() == 0){

        }

        return null;
    }

}
