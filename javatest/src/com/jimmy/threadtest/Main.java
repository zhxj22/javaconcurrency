package com.jimmy.threadtest;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Main {
	
	public static void main(String[] args) {
		Thread threads[] = new Thread[10];
		Thread.State stats[] = new Thread.State[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Calculator(i));
			threads[i].setName("Thread --" + i);
			if (i%2 == 0) {
				threads[i].setPriority(Thread.MAX_PRIORITY);
			}
			else{
				threads[i].setPriority(Thread.MIN_PRIORITY);
			}
		}
		try (FileWriter fw = new FileWriter(".\\log.txt");PrintWriter pw = new PrintWriter(fw)){
			for (int i=0; i<10; i++){
				pw.println("Main : Status of Thread "+i+" : " + threads[i].getState());
				stats[i]=threads[i].getState();
			}
			for (int i=0; i<10; i++){
				threads[i].start();
			}
			boolean finish=false;
			while (!finish) {
			for (int i=0; i<10; i++){
				if (threads[i].getState()!=stats[i]) {
					writeThreadInfo(pw, threads[i],stats[i]);
					stats[i]=threads[i].getState();
				}
			}
			finish=true;
			for (int i=0; i<10; i++){
				finish=finish &&(threads[i].getState()==Thread.State.TERMINATED);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void writeThreadInfo(PrintWriter pw, Thread
		thread, Thread.State state) {
		pw.printf("Main : Id %d - %s\n",thread.getId(),thread.getName());
		pw.printf("Main : Priority: %d\n",thread.getPriority());
		pw.printf("Main : Old State: %s\n",state);
		pw.printf("Main : New State: %s\n",thread.getState());
		pw.printf("Main : ************************************\n");
	}
}
