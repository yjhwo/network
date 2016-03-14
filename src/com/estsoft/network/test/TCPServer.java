package com.estsoft.network.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

//03.14(3)

public class TCPServer {
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
			Socket socket = serverSocket.accept(); // ＊ blocking

			// 4. 연결 성공
			InetSocketAddress remoteAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			String remoteHostAddress = remoteAddress.getAddress().getHostAddress();
			int remoteHostPort = remoteAddress.getPort();

			System.out.println("[server] 연결됨 from " + remoteHostAddress + ":" + remoteHostPort);

			// 5. IOStream 받아 오기
			try {
				InputStream inputStream = socket.getInputStream();
				OutputStream outputStream = socket.getOutputStream();

				while (true) {
					// 6. 데이터 읽기
					byte[] buffer = new byte[256];
					int readByteCount = inputStream.read(buffer); // ＊ blocking

					if (readByteCount <= -1) {
						System.out.println("[server] closed by client");
						break;
					}

					String data = new String(buffer, 0, readByteCount, "utf-8");
					// utf-8은 적어도 되고 안 적어도 됨
					System.out.println("[server] received : " + data);

					// 7. 데이터 쓰기
					outputStream.write(data.getBytes("utf-8")); // hello world만
																// byte로
																// 빼내는 것
				}
			} catch (SocketException ex) {
				System.out.println("[server] 비정상적으로 클라이언트가 종료 되었습니다.");
			} catch(IOException ex){
				ex.printStackTrace();
			} finally {
				// 8. 자원 정리
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}
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
