package com.estsoft.network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

//03.14(5)
public class EchoClient {
	private static final String SERVER_IP = "192.168.1.27";
	private static final int SERVER_PORT = 5000;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Socket socket = null;
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));

			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));	// byte를 char로 바꿔줌
			
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));	// char->byte
			
			while (true) {
				System.out.print(">>");
				String msg = sc.nextLine();

				if (msg.equals("exit")) {
					break;
				}
				
				pw.println("message");
//				outputStream.write((message+"\n").getBytes());
				
				String data = null;
				if((data=br.readLine())!=null){
					System.out.println(data);
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			sc.close();
			if(socket != null && socket.isClosed() == false){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
