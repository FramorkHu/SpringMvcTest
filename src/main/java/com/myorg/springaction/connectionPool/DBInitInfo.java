package com.myorg.springaction.connectionPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huyan on 2015/8/17.
 * 模拟初始化加载配置文件
 */
public class DBInitInfo {

    public static List<DataSourceProperty> properties = new ArrayList<DataSourceProperty>();

    static {
        DataSourceProperty sourceProperty = new DataSourceProperty();

        sourceProperty.setDriverName("com.mysql.jdbc.Driver");
        sourceProperty.setUrl("jdbc:mysql://192.168.100.17/zhe800-store-datacenter");
        sourceProperty.setUserName("root");
        sourceProperty.setPassword("123456");

        sourceProperty.setMinConnections(1);
        sourceProperty.setMaxConnections(10);

        sourceProperty.setPoolName("testPool");

    }

}
