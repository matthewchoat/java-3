package concurrency;

class Reasoning extends Thread {
    //set up this class so it can become a valid thread.

    public void distinguish() {

        System.out.println("Thread name: " + Thread.currentThread().getName());
        //print to the console the difference between a thread and a process
        System.out.println("A program in Java are commonly referred to as a process. So when this program is run, the entire program is running as a process. A thread is a part of this process. A process consists of multiple threads. A thread is the smallest part of a process that can be concurrently executed with other threads in the process. A process has its own address space, whereas, a thread uses the process's address space and shares it with the other threads in that process.");
        //print out you think will happen if you invoke the run() method of a thread as opposed to the start() method of a thread.
        System.out.println("When calling the run method of a class, no new class thread is created and the run method is executed in the current thread, main. No multithreading is taking place. We can also call the run method multiple times however, we can only call the start method once (per thread), otherwise it will throw an IllegalStateException. The start method creates a new thread and then runs it, so it is performing two tasks in one command. So, replacing the run() calls with start() in this particular example will create two new threads named 'Thread-0' and 'Thread-1' instead of just running on the main thread.");
    }

    @Override
    public void run() {
        distinguish();
        super.run();
    }

    @Override
    public void start() {
        distinguish();
        super.start();
    }
}

