package com.estsoft.network.echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

//03.16(4) 
public class UDPTimeClient {
	private static final String SERVER_IP = "192.168.1.27";
	private static final int SERVER_PORT = 5000;
	private static final int BUFFER_SIZE = 1024;
	
	public static void main(String[] args) {
		DatagramSocket socket = null;
		
		try{
			socket = new DatagramSocket();
			
			String msg = "";
			byte[] sendData = msg.getBytes("UTF-8");
			
			DatagramPacket sendPacket= new DatagramPacket(sendData, sendData.length,new InetSocketAddress(SERVER_IP,SERVER_PORT));
			socket.send(sendPacket);
			
			// 수신 ---
			DatagramPacket receivepacket = new DatagramPacket(new byte[BUFFER_SIZE],BUFFER_SIZE);
			socket.receive(receivepacket);
			msg = new String(receivepacket.getData(),0,receivepacket.getLength(),"UTF-8");
			System.out.println(msg);
			
		}catch(IOException ex){
			ex.printStackTrace();
		} finally{
			if(socket!= null && socket.isClosed()==false){
				socket.close();
			}
		}
	}

}
