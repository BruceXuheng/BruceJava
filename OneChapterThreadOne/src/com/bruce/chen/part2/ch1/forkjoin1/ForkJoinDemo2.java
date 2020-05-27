package com.bruce.chen.part2.ch1.forkjoin1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Fork/Join的异步方法演示不要求返回值：遍历指定目录（包含子目录）寻找指定类型文件
 */
public class ForkJoinDemo2 extends RecursiveAction {

    private File path;

    public ForkJoinDemo2(File path) {
        this.path = path;
    }

    @Override
    protected void compute() {
        List<ForkJoinDemo2> forkJoinDemo2List = new ArrayList<>();
        File[] files = path.listFiles();
        if(files!=null){
            for (File file : files) {
                if(file.isDirectory()){
                    forkJoinDemo2List.add(new ForkJoinDemo2(file));
                }else{
                    if(file.getAbsolutePath().endsWith("txt")){
                        System.out.println(file.getAbsolutePath());
                    }
                }

            }

            //??
            if(!forkJoinDemo2List.isEmpty()){
                // 在当前的 ForkJoinPool 上调度所有的子任务。
                for (ForkJoinDemo2 forkJoinDemo2 : invokeAll(forkJoinDemo2List)) {
                    forkJoinDemo2.join();
                }
            }

        }


    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinDemo2 demo2 = new ForkJoinDemo2(new File("H:\\陈旭恒JAVA资料\\JAVA架构学习\\01并发编程\\03-2019.04.21-线程的并发工具类（并发编程）等1个文件\\"));

        pool.execute(demo2);
        demo2.join();


    }

}
