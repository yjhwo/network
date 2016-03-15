package com.estsoft.network.thread;

// 03.15(1) - thread 만드는 첫 번째 방법(Thread클래스 상속)

public class ThreadEx01 {

	public static void main(String[] args) {
		Thread thread01 = new DigitThread();		
		Thread thread02 = new AlphabetThread();
		Thread thread03 = new AlphabetThread();
		
		Thread thread04 = new Thread(new UpperCaseAlphabetRunnableImpl());
		
		thread01.start();
		thread02.start();
		thread03.start();
		thread04.start();
	}

}
