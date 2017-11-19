package com.levelappro.ofsp.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketServierTest {
	
	private List<MyChannel> all = new ArrayList<MyChannel>();
	
	public static void main(String[] args) throws Exception {
		//new SocketServierTest().server();
		new SocketServierTest().creatThread();
		
	}
	
	public void creatThread(){
		NewThread nt = new NewThread("张三", "22");
		new Thread(nt).start();
	}
	
	protected class NewThread implements Runnable{
		private String name;
		private String age;
		
		public NewThread(){}
		public NewThread(String name, String age){
			this.name = name;
			this.age = age;
		}
		public void run() {
			System.out.println(name+"-------"+age);
		}
	}
	
	
	
	public void server(){
		while(true){
			try {
				ServerSocket ss = new ServerSocket(9999);
				Socket socket = ss.accept();
				MyChannel myChannel = new MyChannel(socket);
				String name = myChannel.dis.readUTF();
				myChannel.setName(name);
				myChannel.sendOthers(name+"加入聊天室");
				all.add(myChannel);
				new Thread(myChannel).start();
			} catch (Exception e) {
			}
		}
	}
	
	
	
	

	
	private class MyChannel implements Runnable{
		
		private DataInputStream dis;
		private DataOutputStream dos;
		private Boolean isRunning = true;
		private String name;
		
		public MyChannel(){}
		public MyChannel(Socket socket){
			try {
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 接受客户端发来的信息
		 * @return
		 */
		public String getMsg(){
			String msg="";
			try {
				msg = dis.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return msg;
		}
		/**
		 * 发送给某个人
		 * @param msg
		 */
		public void sendMsg(String msg){
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 发送给其他人
		 */
		private void sendOthers(String msg) {
			for(MyChannel myChannel : all){
				if(myChannel == this){
					continue;
				}
				myChannel.sendMsg(this.getName()+"说:"+msg);
			}
		}
		
		public void run() {
			while(isRunning){
				//this.sendMsg();
				String msg = this.getMsg();
				this.sendOthers(msg);
			}
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	
	
	


}
