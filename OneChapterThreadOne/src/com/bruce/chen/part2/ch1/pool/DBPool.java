package com.bruce.chen.part2.ch1.pool;

import java.sql.Connection;
import java.util.LinkedList;

public class DBPool {

    //容器连接池
    private static LinkedList<Connection> pool = new LinkedList<Connection>();

    public DBPool(int initMaxSize) {
        if (initMaxSize > 0) {
            for (int i = 0; i < initMaxSize; i++) {
                pool.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }

    /**
     * 释放连接,通知其他等待线程
     *
     * @param connection
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                //不满足
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * 获取连接
     *
     * @param mills
     * @return
     * @throws InterruptedException
     */
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills < 0) {
                while (pool.isEmpty()) {
                    wait();
                }
                return pool.removeFirst();
            } else {
                /*超时时刻*/
                long futures = System.currentTimeMillis() + mills;
                /* 等待时长 */
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0) {

                    pool.wait(remaining);

                    //现在等待时间，重新计算等待时间
                    remaining = futures - System.currentTimeMillis();
                }
                Connection connection = null;
                if(!pool.isEmpty()){
                    connection = pool.removeFirst();
                }
                return connection;
            }
        }
    }


}
