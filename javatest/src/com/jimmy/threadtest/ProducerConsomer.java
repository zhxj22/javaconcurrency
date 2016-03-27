package com.jimmy.threadtest;

import java.util.ArrayList;
import java.util.Queue;

import javax.management.Query;

public class ProducerConsomer {
	static int SIZE = 10;
	ArrayList<Integer> queque = new ArrayList<Integer>();
	synchronized public void printsize(){
		System.out.println("Size is " + queque.size());
	}
	synchronized public void addtoBuffer(){
		if (queque.size() < 10) {
			queque.add(0);
			System.out.println("added new value, and size is " + queque.size());
			this.notify();
		}
		else {
			try {
				System.out.println("addtoBuffer - size is " + queque.size() + " so will wait");
				while(queque.size() >= 10)
					this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	synchronized public void removeFromBuffer(){
		while(queque.size() == 0){
			System.out.println("removeFromBuffer - size is " + queque.size() + " so will wait");
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Will remove a data, current size is " + this.queque.size());
		this.queque.remove(0);
		notify();
	}
	/*
	 * the produer consumer
	 */
	public static class Producer implements Runnable{
		ProducerConsomer pc;
		public Producer(ProducerConsomer pc) {
			this.pc = pc;
		}
		@Override
		public void run() {
			int i = 0;
			while(i<10)
			{
				Util.printmsg("in producer: i = "+String.valueOf(i));
				i ++;
				pc.addtoBuffer();
				Util.sleep(200);
			}
		}
		
	}
	
	public static class Consumer implements Runnable{
		ProducerConsomer pc;
		public Consumer(ProducerConsomer pc){
			this.pc = pc;
		}
		@Override
		public void run() {
			int i = 0;
			while(i < 10){
				i++;
				Util.printmsg("in consumer: i = " +String.valueOf(i));
				pc.removeFromBuffer();
				Util.sleep(100);
			}
		}
	}
	
	public static void main(String[] args) {
		//add two worker to do produce and consum
		ProducerConsomer pc = new ProducerConsomer();
		new Thread(new Consumer(pc)).start();
		Util.sleep(5000);
		new Thread(new Producer(pc)).start();
		
	}

}
