package com.estsoft.network.thread;
//03.15(2) - Runnable사용

public class UpperCaseAlphabetRunnableImpl extends UpperCaseAlphabet implements Runnable {

	@Override
	public void run() {
		print();
	}

}
