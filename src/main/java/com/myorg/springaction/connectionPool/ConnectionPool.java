package com.myorg.springaction.connectionPool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by huyan on 2015/8/14.
 */
public class ConnectionPool implements ConnectionPoolBase {

    private DataSourceProperty sourceProperty;
    private boolean isActive = false; // 连接池活动状态
    private int contActive = 0;// 记录创建的总的连接数

    // 空闲连接
    private List<Connection> freeConnection = new CopyOnWriteArrayList<Connection>();
    // 活动连接
    private List<Connection> activeConnection = new CopyOnWriteArrayList<Connection>();
    // 将线程和连接绑定，保证事务能统一执行
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    public ConnectionPool(){

    }

    public void initPool(){
        try {

            Connection conn;
            for (int i=0; i<sourceProperty.getInitConnections(); i++){
                conn = newConnection();
                //初始化最小连接
                if (conn!=null){
                    freeConnection.add(conn);
                    contActive++;
                }
            }
            isActive = true;
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public Connection newConnection() throws ClassNotFoundException, SQLException {

        Connection connection = null;
        if (sourceProperty != null){
            Class.forName(sourceProperty.getDriverName());
            connection = DriverManager.getConnection(sourceProperty.getUrl(),
                    sourceProperty.getUserName(), sourceProperty.getPassword());
        }

        return connection;
    }

    /**
     * 判断连接是否可用
     * @param conn
     * @return
     */
    public boolean isValid(Connection conn){

        try {
            if (conn == null || conn.isClosed()){
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return true;

    }

    @Override
    public Connection getConnection() {
        Connection conn = null;

        try {
            // 判断是否超过最大连接数限制
            if (contActive < sourceProperty.getMaxActiveConnections()){

                if (freeConnection.size()>0){
                    conn = freeConnection.get(0);
                    if ( conn!=null){
                        threadLocal.set(conn);
                    }
                    freeConnection.remove(0);
                } else {
                    conn = newConnection();
                }
            } else {
                wait(sourceProperty.getConnectionTimeOut());
                conn = getConnection();
            }

            if (isValid(conn)){
                activeConnection.add(conn);
                contActive++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return conn;

    }

    @Override
    public Connection getCurrentConnection() {
        return null;
    }

    @Override
    public void releaseConnection(Connection conn) {

    }

    @Override
    public void destory() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void checkPool() {

    }
}
