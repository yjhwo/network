package com.estsoft.network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

// cmd창에서 bin 폴더까지 간 후
// java com.estsoft.network.test.TCPServer
// java com.estsoft.network.echo.EchoServer

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
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);	// char->byte
			// true : PrintWriter버퍼 내부에 값이 들어오면 바로 보내겠다는 뜻(flush할 필요 X) -> autoFlush
			// InputStreamReader: byte-> 문자
			// OutputStreamWriter : 문자 -> byte
			
			// 데이터 읽기에 타임아웃 설정 0.001초
			// local에선 빠르기 때문에 error가 뜨지 않았고, 상대방 컴퓨터로 하면 timeout 걸린다.
			// 소켓에 데이터를 쓰고/읽는 타임아웃을 정한다. -> br.readLine()에서 걸려 있는 것.
			socket.setSoTimeout(1);	
			
			while (true) {
				System.out.print(">> ");
				String msg = sc.nextLine();

				if (msg.equals("exit")) {
					break;
				}
				
				pw.println(msg);
				pw.flush();
//				socket.getOutputStream.write((message+"\n").getBytes());				
				
				String data = null;
				if((data=br.readLine())!=null){
					System.out.println("<< "+data);
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
