package com.estsoft.network.util;

import java.net.InetAddress;
import java.util.Scanner;

//03.14(2) - NSLookUp프로그램 구현

public class NSLookup {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			try {
				System.out.print(">");
				String hostname = sc.nextLine();

				if (hostname.equals("exit")) 					
					break;
				
				InetAddress[] inetAddresses = InetAddress.getAllByName(hostname);

				for (int i = 0; i < inetAddresses.length; i++) {
					System.out.println(hostname + ":" + inetAddresses[i].getHostAddress());
				}

			} catch (Exception e) {
				System.out.println("알 수 없는 도메인입니다.");
			}
		}	// while_end
		
		sc.close();
	}

}
