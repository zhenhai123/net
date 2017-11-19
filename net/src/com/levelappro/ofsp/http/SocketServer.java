package com.levelappro.ofsp.http;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	
	private static ServerSocket ss;
	
	public static void main(String[] args) throws IOException {
			ss = new ServerSocket(8888);
			while(true){
				new SocketServer().receive();
			}
		
	}
	
	
	public void receive(){
		try {
			
			StringBuilder sb = new StringBuilder();
			
			Socket socket = ss.accept();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg = null;
			
			while((msg = br.readLine()).length() > 0){
				sb.append(msg);
				sb.append("\n");
			}
			
			System.out.println(sb.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public void server(Socket socket) throws IOException{
		MyServer ms = new MyServer(socket);
		new Thread(ms).start();
	}
	
	
	protected class MyServer implements Runnable{
		
		private DataOutputStream dos;
		private DataInputStream dis;
		
		public MyServer(Socket socket){
			try {
				dos = new DataOutputStream(socket.getOutputStream());
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void run() {
			try {
				System.out.println("---------------------------------------------------------------");
				String data = dis.readUTF();
				System.out.println(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	} 

}
