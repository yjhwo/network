package com.estsoft.network.echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

//03.16(2) 
public class UDPEchoServer {
	private static final int PORT = 5000;
	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) {
		DatagramSocket socket = null;

		try {
			socket = new DatagramSocket(PORT); // 1. UDP 소켓 생성

			while (true) {
				// 2. 수신 대기
				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				socket.receive(receivePacket);
				// 수신 패킷을 생성할 경우에는 수신할 데이터의 버퍼를 미리 잡아준다.(UDP패킷 통신에는 버퍼사이즈가 고정되어 있다)

				String message = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
				System.out.println(message);

				byte[] sendData = message.getBytes("UTF-8");
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
						receivePacket.getSocketAddress());	// 클라이언트가 읽기 대기중인 DatagramSocket의 정보
				socket.send(sendPacket);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (socket != null && socket.isClosed() == false) {
				socket.close();
			}
		}
	}

}
