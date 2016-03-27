package com.jimmy.threadtest;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.management.Query;

public class EventStorage {
		private int maxSize;
		private List<Date> storage;
		public EventStorage(){
			maxSize=10;
			storage=new LinkedList<>();
			}
		public  synchronized void testsleep(){
			Util.sleep(10000);
		}
		public  synchronized void testprint(){
			System.out.println("in test1");
		}
		public synchronized void set(){
				while (storage.size()==maxSize){
					try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
					}
				}
				((LinkedList<Date>) storage).offer(new Date());
				System.out.printf("Set: %d",storage.size());
				notifyAll();
			}
		public synchronized void get(){
			while (storage.size()==0){
			try {
			wait();
			} catch (InterruptedException e) {
			e.printStackTrace();
			}
			}
			System.out.printf("Get: %d: %s",storage.
			size(),((LinkedList<?>)storage).poll());
			notifyAll();
			}

	public static class Producer implements Runnable {
		private EventStorage storage;
		public Producer(EventStorage storage){
		this.storage=storage;
		}
		@Override
		public void run() {
			for (int i=0; i<100; i++){
			storage.set();
			}
		}
	}
	public static class Consumer implements Runnable {
		private EventStorage storage;
		public Consumer(EventStorage storage){
		this.storage=storage;
		}
		@Override
		public void run() {
			for (int i=0; i<100; i++){
			storage.get();
			}
		}
	}
	public static class MyRunnable implements Runnable {
		private EventStorage storage;
		public MyRunnable(EventStorage storage){
		this.storage=storage;
		}
		@Override
		public void run() {
			storage.testsleep();;
		}
	}	
	public static void main(String[] args) {
		
		EventStorage storage= new EventStorage();
		MyRunnable runnable = new MyRunnable(storage);
		Thread th1 = new Thread(runnable);
		MyRunnable runnable2 = new MyRunnable(storage);
		Thread th2 = new Thread(runnable2);
		
		th1.start();
		th2.start();
		
		Producer producer=new Producer(storage);
		Thread thread1=new Thread(producer);
		Consumer consumer=new Consumer(storage);
		Thread thread2=new Thread(consumer);
		thread2.start();
		thread1.start();
	}

}
