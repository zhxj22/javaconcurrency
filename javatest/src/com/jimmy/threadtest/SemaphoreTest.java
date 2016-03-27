package com.jimmy.threadtest;

import java.util.concurrent.Semaphore;


/**
In this recipe, you will learn how to use the semaphore mechanism provided by the Java
language. A semaphore is a counter that protects the access to one or more shared resources.
When a thread wants to access one of these shared resources, first, it must acquire the
semaphore. If the internal counter of the semaphore is greater than 0, the semaphore
decrements the counter and allows access to the shared resource. A counter bigger than 0
means there are free resources that can be used, so the thread can access and use one
of them.
Otherwise, if the counter of the semaphore is 0, the semaphore puts the thread to sleep until
the counter is greater than 0. A value of 0 in the counter means all the shared resources are
used by other threads, so the thread that wants to use one of them must wait until one is free.
When the thread has finished the use of the shared resource, it must release the semaphore
so that the other thread can access the shared resource. That operation increases the
internal counter of the semaphore.
In this recipe, you will learn how to use the Semaphore class to implement special kinds of
semaphores called binary semaphores. These kinds of semaphores protect the access to a
unique shared resource, so the internal counter of the semaphore can only take the values
1 or 0. To show how to use it, you are going to implement a print queue that can be used by
concurrent tasks to print their jobs. This print queue will be protected by a binary semaphore,
so only one thread can print at a time.
 */
public class SemaphoreTest {
	public static class PrintQueue{
		private Semaphore semaphore = null;
		public PrintQueue(){
			semaphore = new Semaphore(1);
		}
		public void printJob(Object obj){
			try {
				semaphore.acquire();
				long duration=(long)(Math.random()*10);
				System.out.printf("%s: PrintQueue: Printing a Job during %dseconds\n",
						Thread.currentThread().getName(),duration);
				Thread.sleep(duration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally{
				semaphore.release();
			}
		}
	}
	public static class Job implements Runnable{
		private PrintQueue pq = null;
		public Job(PrintQueue pq) {
			this.pq = pq;
		}
		@Override
		public void run() {
			System.out.printf("%s: Going to print a job\n",Thread.currentThread().getName());
			pq.printJob(new Object());
			System.out.printf("%s: The document has been printed\n",Thread.currentThread().getName());
		}
		
	}
	public static void main(String[] args) {
		PrintQueue pq = new PrintQueue();
		Thread threads[] = new Thread[10];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Job(pq), "Thread - " + i);
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		
	}

}
