package com.levelappro.ofsp.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 
 * @author zhb
 *
 */
public class ClientReceive implements Runnable{
	
	private DataInputStream dis;
	// 线程运行状态
	private Boolean isRunning = true;

	public ClientReceive(){
		
	}
	
	public ClientReceive(Socket socket) throws IOException{
		dis = new DataInputStream(socket.getInputStream());
	}
	
	public String getMsg() throws IOException{
		return dis.readUTF();
	}
	
	
	public void run() {
		while(isRunning){
			try {
				String msg = this.getMsg();
				System.out.println(msg);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

}
