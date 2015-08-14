package com.myorg.springaction.connectionPool;

import java.sql.Connection;

/**
 * Created by huyan on 2015/8/14.
 */
public interface ConnectionPoolBase {

    public Connection getConnection();

    public Connection getCurrentConnection();

    public void releaseConnection(Connection conn);

    public void destory();

    public boolean isActive();

    public void checkPool();

}
