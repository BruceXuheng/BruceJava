package com.bruce.chen.part3.ch5.dp;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class ItemVo<T> implements Delayed {

    /*过期时长 毫秒*/
    private long activeTime;
    /*业务数据 */
    private T data;

    public ItemVo(long activeTime, T data) {
        this.activeTime = activeTime * 1000 + System.currentTimeMillis();
        this.data = data;
    }

    public long getActiveTime() {
        return activeTime;
    }

    public T getData() {
        return data;
    }

    /**
     * 返回到激活日期的剩余时间
     *
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        long d = unit.convert(this.activeTime - System.currentTimeMillis()
                , unit);
        return d;
    }

    /**
     * 按照剩余时间排序
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        long d = getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
        if (d == 0) {
            return 0;
        } else {
            if (d < 0) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }
}
