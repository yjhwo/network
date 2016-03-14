package com.estsoft.network.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

//03.14(1) 
//LocalHost = 현재 돌고있는 프로그램의 컴퓨터

public class LocalHost {

	public static void main(String[] args) {
		
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			String hostAddress = inetAddress.getHostAddress();
			String hostName = inetAddress.getHostName();
			
			System.out.println(hostAddress);
			System.out.println(hostName);
			
			byte[] addresses = inetAddress.getAddress();	//byte단위로
															// |192|168|1|27| 형식으로
			for(int i=0; i<addresses.length; i++){
				int address = addresses[i] & 0x000000ff;	// 이렇게 해줘야 +로
				
				System.out.print(address);
				
				if(i<addresses.length-1)
					System.out.print(".");					// 그냥 출력하면 |-64|-88|1|27| 
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}

}
