package com.bruce.chen.part2.ch1.forkjoin1;

import com.bruce.chen.part2.ch1.utils.SleepTools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 演示 ForkJoin 同步执行完成归并执行
 */
public class ForkJoinDemo1 {

    static class MySumTask extends RecursiveTask<Integer> {

        /*阈值*/
        private final static int THRESHOLD = MakeArray.ARRAY_LENGTH/10;
        /*数据源*/
        private int[] src;
        /*起始index*/
        private int fromIndex;
        /*末尾index*/
        private int toIndex;

        public MySumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected Integer compute() {
            if (toIndex - fromIndex < THRESHOLD){
                System.out.println(" from index = "+fromIndex
                        +" toIndex="+toIndex);
                int count = 0;
                for(int i= fromIndex;i<=toIndex;i++){
                    SleepTools.ms(1);
                    count = count + src[i];
                }
                return count;
            }else{
                int mid = (fromIndex+toIndex)/2;
                MySumTask left = new MySumTask(src,fromIndex,mid);
                MySumTask right = new MySumTask(src,mid+1,toIndex);
                invokeAll(left,right);
                return left.join()+right.join();
            }
        }
    }

    public static void main(String[] args) {

        int[] arr = MakeArray.makeArray();
        MySumTask mySumTask = new MySumTask(arr,0,arr.length-1);

        ForkJoinPool pool = new ForkJoinPool();
        long start = System.currentTimeMillis();
        pool.invoke(mySumTask);

        System.out.println("The count is "+mySumTask.join()
                +" spend time:"+(System.currentTimeMillis()-start)+"ms");



    }

}
