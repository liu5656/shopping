package com.dadalang.x.util.masterslave;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/7/5 3:13 下午
 * @desc
 */
public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String type = HandleDataSource.getDataSource();
        System.out.println(" ------ determineCurrentLookupKey：" + type );
        return type ;
    }
}
