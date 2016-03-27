package com.jimmy.threadtest;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
A simlar example with SemaphoreTest.java, but the senario is more than one thread can use the PrintQure.
Thre PrintQueue have 3 printer to use, so when 10 thread come, 3 threads can be admited to enter to use.
 */
public class SemaphoreTest2 {
	public static class PrintQueue{
		private Semaphore semaphore = null;
		private boolean freePrinters[] = null;
		private Lock lockPrinters = null;
		public PrintQueue(){
			semaphore = new Semaphore(3);
			freePrinters = new boolean[3];
			lockPrinters = new ReentrantLock();
			
			//in the beginning, all the printers can be used.
			for (int i = 0; i < freePrinters.length; i++) {
				freePrinters[i] = true;
			}
		}
		public void printJob(Object obj){
			try {
				//when thread come, first get the permission
				semaphore.acquire();
				System.out.printf("%s: Going to print a job\n",Thread.currentThread().getName());
				int assignedPrinter  = getFreePrinter();
				long duration=(long)(Math.random()*10);
				System.out.printf("%s: PrintQueue: Printing a Job in Printer - %d during %d seconds\n",
						Thread.currentThread().getName(),
						assignedPrinter,duration);
				TimeUnit.SECONDS.sleep(duration);
				//after use, set the printer free
				freePrinters[assignedPrinter]=true;
				System.out.printf("%s: The document has been printed\n",Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally{
				semaphore.release();
			}
		}
		//the procedure to get a printer, as more than 1 thread can enter,
		//so also need to be synchronized.
		synchronized private int getFreePrinter() {
			int ret = -1;
			try{
				//if no sync, error would happen
				for (int i=0; i<freePrinters.length; i++) {
					if (freePrinters[i]){
						ret=i;
						freePrinters[i]=false;
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
			return ret;
		}
	}
	public static class Job implements Runnable{
		private PrintQueue pq = null;
		public Job(PrintQueue pq) {
			this.pq = pq;
		}
		@Override
		public void run() {
			
			pq.printJob(new Object());
			
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
