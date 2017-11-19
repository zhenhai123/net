package com.levelappro.ofsp.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlTest {
	
	public static void main(String[] args) {
		
		
		try {
			URL url= new URL("http://www.baidu.com:80");
			
			InputStream in = url.openStream();
			
			//byte[] flush = new byte[1024];
//			int len = 0;
//			while ((len = in.read(flush))!=-1){
//				
//				//System.out.println(new String(flush,0,len));
//			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("baidu.html"),"UTF-8"));
			
			String msg = null;
			while ((msg = br.readLine()) != null){
				bw.append(msg);
				bw.newLine();
			}
			bw.flush();
			bw.close();
			in.close();
			br.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
	
}
