package com.estsoft.network.echo;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

//03.15(3)
public class EchoServer {
	private final static int PORT = 5000;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();

			// 2. binding(서버의 ip주소와 port번호를)
			InetAddress inetAddress = InetAddress.getLocalHost();
			String localhostAddress = inetAddress.getHostAddress(); // ip주소
			serverSocket.bind(new InetSocketAddress(localhostAddress, PORT)); // ip+port

			System.out.println("[server] binding " + localhostAddress + ":" + PORT);

			// 3. accept 연결 요청 기다림
			while(true){
				Socket socket = serverSocket.accept(); // ＊ blocking
				Thread thread = new EchoServerReceiveThread(socket);
				thread.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 소켓이 제대로 닫혔는지 확인
			// 9. 데이터 socket 닫기
			try {
				if (serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
