package com.myorg.springaction.connectionPool;

import java.sql.Connection;
import java.util.HashMap;

/**
 * Created by huyan on 2015/8/17.
 * 连接池管理类
 */
public class ConnectionPoolManager {

    private HashMap<String, ConnectionPool> pools = new HashMap<String, ConnectionPool>();

    private ConnectionPoolManager(){

    }

    public static ConnectionPoolManager getInstance(){
        return Singtonle.instance;
    }

    private static class Singtonle{
        private static ConnectionPoolManager instance = new ConnectionPoolManager();
    }

    public void initialPool(){

        for (DataSourceProperty property : DBInitInfo.properties){

            ConnectionPool pool = new ConnectionPool(property);

            pools.put(property.getPoolName(), pool);

            System.out.println("Initial Pool is success:"+property.getPoolName());

        }
    }

    public Connection getConnection(String poolName){
        Connection conn = null;
        if (pools.size()>0 && pools.get(poolName)!= null){
            conn = pools.get(poolName).getConnection();
        } else {

        }
        return conn;
    }

    public void close(String poolName, Connection conn){
        ConnectionPool pool = pools.get(poolName);
        if (pool!=null){
            pool.releaseConnection(conn);
        }
    }

    public void destory(String poolName){
        ConnectionPool pool = pools.get(poolName);
        if (pool!=null){
            pool.destory();
        }
    }

    public ConnectionPool getConnectionPool(String poolName){
        ConnectionPool pool = null;
        if (pools.size()>0){
            pool = pools.get(poolName);
        }
        return pool;
    }
}
