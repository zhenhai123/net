package com.levelappro.ofsp.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;


public class SocketClientTest {
	
	
	public static void main(String[] args) throws Exception {
			
			System.out.println("请输入你的姓名：");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String name = br.readLine();
			if(name == "" || name == null){
				return;
			}
		
			Socket socket = new Socket("localhost", 9999);
			new Thread(new ClientSend(socket, name)).start();
			new Thread(new ClientReceive(socket)).start();
	}

}
