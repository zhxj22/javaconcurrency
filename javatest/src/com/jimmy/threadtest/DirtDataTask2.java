package com.jimmy.threadtest;

import java.util.concurrent.TimeUnit;

public class DirtDataTask2 {

	public static void main(String ars[]){
		Data d = new Data();
		new Thread(new OptTask(d)).start();
		new Thread(new OptTask(d)).start();
		new Thread(new OptTask(d)).start();
		new Thread(new OptTask(d)).start();
		new Thread(new OptTask(d)).start();
		new Thread(new OptTask(d)).start();
		
	}
	
	public static class OptTask implements Runnable{
		Data d;
		public OptTask(Data d) {
			// TODO Auto-generated constructor stub
			this.d = d;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			d.operate();
		}
		
	}
	public static class Data{
		private int a = 1;
		Object object = new Object();
		//when ahve the synchronized key word, the thread running 
		//this code would pending till another therad finish this code
		public void operate(){
			synchronized (object) {
				a --;
				System.out.println("I am thread: " + Thread.currentThread());
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("value of a should be 0, actual is " + a);
				a ++;
			}
			
		}
	}
}
