package com.jimmy.threadtest;

import java.util.concurrent.locks.ReentrantLock;

/**
 Java provides another mechanism for the synchronization of blocks of code. It's a more
powerful and flexible mechanism than the synchronized keyword. It's based on the Lock
interface and classes that implement it (as ReentrantLock). This mechanism presents
some advantages, which are as follows:
 -> It allows the structuring of synchronized blocks in a more flexible way. With the
synchronized keyword, you have to get and free the control over a synchronized
block of code in a structured way. The Lock interfaces allow you to get more complex
structures to implement your critical section.
 -> The Lock interfaces provide additional functionalities over the synchronized
keyword. One of the new functionalities is implemented by the tryLock() method.
This method tries to get the control of the lock and if it can't, because it's used by
other thread, it returns the lock. With the synchronized keyword, when a thread
(A) tries to execute a synchronized block of code, if there is another thread (B)
executing it, the thread (A) is suspended until the thread (B) finishes the execution
of the synchronized block. With locks, you can execute the tryLock() method. This
method returns a Boolean value indicating if there is another thread running the
code protected by this lock.
 -> The Lock interfaces allow a separation of read and write operations having multiple
readers and only one modifier.
 -> The Lock interfaces offer better performance than the synchronized keyword.
 */
public class ReenterLockTest {

	public static class PrintQueue{
		private final ReentrantLock queLock = new ReentrantLock();
		public void printJob(Object document){
			this.queLock.lock();
			try 
			{
				Long duration=(long)(Math.random()*10000);
				System.out.println(Thread.currentThread().getName()+ ":PrintQueue: Printing a Job during "
						+(duration/1000)+	" seconds");
				Thread.sleep(duration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally{
				this.queLock.unlock();
			}
		}
	}
	public static class Job implements Runnable{
		private PrintQueue pq = null;
		public Job(PrintQueue pq){
			this.pq = pq;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.printf("%s: Going to print a document\n", Thread.currentThread().getName());
			this.pq.printJob(new Object());
			System.out.printf("%s: The document has been printed\n",Thread.currentThread().getName());
		}
		
	}
	public static void main(String[] args) {
		PrintQueue printQueue=new PrintQueue();
		Thread threads[] = new Thread[10];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Job(printQueue), "Thread - " + i);
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}

}
