package com.estsoft.network.echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

//03.16(3) 
public class UDPTimeServer {
	private static final int PORT = 5000;
	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) {

		// 수신
		DatagramSocket socket = null;

		try {
			socket = new DatagramSocket(PORT);

			while (true) {
				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);	//수신할 데이터의 버퍼 미리 잡아줌
				socket.receive(receivePacket);

				// 송신
				Date current = new Date();
				SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

				String date = sdr.format(current);

				byte[] sendData = date.getBytes("UTF-8");
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,receivePacket.getSocketAddress());
				socket.send(sendPacket);

			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally{
			if(socket!=null && socket.isClosed()==false){
				socket.close();
			}
		}

	}

}
