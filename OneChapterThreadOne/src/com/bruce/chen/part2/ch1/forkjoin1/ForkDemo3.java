package com.bruce.chen.part2.ch1.forkjoin1;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkDemo3 extends RecursiveTask<int[]> {

    int toindex;
    int[] src ;
    int fromIndex;
    private final static int THRESHOLD = 2;

    public ForkDemo3(int[] src) {
        this.toindex = 0;
        this.src = src;
        this.fromIndex = src.length-1;
    }

    @Override
    protected int[] compute() {
        if(fromIndex<=THRESHOLD){
            return InsertionSort.sort(src);
        }else{
            int mid = src.length/2;

            ForkDemo3 left = new ForkDemo3(Arrays.copyOfRange(src, 0, mid));
            ForkDemo3 right = new ForkDemo3(Arrays.copyOfRange(src, mid+1, src.length));
            invokeAll(left,right);
            return MergeSort.merge(left.join(),right.join());
        }

    }

    public static void main(String[] args) {
        ForkDemo3 forkDemo3 = new ForkDemo3(MakeArray.makeArray());
        ForkJoinPool pool = new ForkJoinPool();
        int[] result = pool.invoke(forkDemo3);
        for (int i : result) {
            System.out.print(i+" ");
        }
    }

}
