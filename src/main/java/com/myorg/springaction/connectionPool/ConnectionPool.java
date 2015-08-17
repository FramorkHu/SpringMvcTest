package com.myorg.springaction.connectionPool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
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

    public ConnectionPool(DataSourceProperty sourceProperty){
        this.sourceProperty = sourceProperty;
        initPool();
        checkPool();
    }

    /**
     * 初始化连接池
     */
    private void initPool(){
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

    private Connection newConnection() throws ClassNotFoundException, SQLException {

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

    /**
     * 从连接池中获取链接
     * @return
     */
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

        Connection connection = threadLocal.get();
        if ( !isValid(connection) ){
            connection = getConnection();
        }
        return connection;
    }

    @Override
    public synchronized void releaseConnection(Connection conn) {

        if (isValid(conn) && !(freeConnection.size()>sourceProperty.getMaxConnections())){

            freeConnection.add(conn);
            activeConnection.remove(conn);
            contActive--;
            threadLocal.remove();
        }
    }

    @Override
    public void destory() {

        List<Connection> allConnection = new CopyOnWriteArrayList<Connection>();
        allConnection.addAll(activeConnection);
        allConnection.addAll(freeConnection);

        for (Connection conn : allConnection){
            try {
                if (isValid(conn)){
                    conn.close();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        isActive = false;
        contActive =0;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void checkPool() {

        if (sourceProperty.isCheakPool()){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("空闲连接数:" + freeConnection.size());
                    System.out.println("活动连接数:" + activeConnection.size());
                    System.out.println("总连接数:" + contActive);
                }
            },sourceProperty.getLazyCheck(), sourceProperty.getPeriodCheck());
        }
    }
}
