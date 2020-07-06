package concurrency;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

class Sync extends Thread {
    List nums = new ArrayList<>();
    CountDownLatch countdown;

    Sync(String name, CountDownLatch countdown, List nums) {
        super(name);
        this.countdown = countdown;
        this.nums = nums;
    }

    @Override 
    public void run() {
        Random rand = new Random();

        for(int i=0; i<100; i++) {
            int randInt = rand.nextInt(100);
            this.nums.add(randInt);
        }
        countdown.countDown();
    }

    public static void main(String[] args )  {

        final CountDownLatch count = new CountDownLatch(5);
        CopyOnWriteArrayList<Integer> nums = new CopyOnWriteArrayList();

        Sync syncThread = new Sync("sync thread 1", count, nums);
        syncThread.start();

        Sync syncThread2 = new Sync("sync thread 2", count, nums);
        syncThread2.start();

        Sync syncThread3 = new Sync("sync thread 3", count, nums);
        syncThread3.start();

        Sync syncThread4 = new Sync("sync thread 4", count, nums);
        syncThread4.start();

        Sync syncThread5 = new Sync("sync thread 5", count, nums);
        syncThread5.start();
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nums);
        System.out.println(nums.size());

        //this prints out an empty list. write some code that will allow the data generated in the syncThread to show up  here.  There is a brute force way and a more sophisticated way.  Either or will work, but strive for sophistication :)

    }
    
}