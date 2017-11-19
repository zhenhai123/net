package com.levelappro.ofsp.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 客户端输出线程
 * @author zhb
 *
 */
public class ClientSend implements Runnable{

	// 管道输入流
	private BufferedReader consoleBr;
	// 管道输出流
	private DataOutputStream dos;
	// 线程运行状态
	private Boolean isRunning = true;
	
	private String name;
	
	public ClientSend(){
		consoleBr = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public ClientSend(Socket socket, String name) throws IOException{
		this();
		dos = new DataOutputStream(socket.getOutputStream());//new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		System.out.println("欢迎你的加入：" + name);
		this.name = name;
		this.send(name);
	}
	/**
	 * 从控制台读取数据
	 * @return
	 * @throws IOException
	 */
	public String getMsgFromConsole() throws IOException{
		return consoleBr.readLine();
	}
	
	public void send(String msg) throws IOException{
		if(msg != null && msg !=""){
			dos.writeUTF(msg);
			dos.flush();
		}
	}
	
	public void run() {
		
		while(isRunning){
			try {
				String msg = this.getMsgFromConsole();
				this.send(msg);
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
			
	}
	

	
	

}
